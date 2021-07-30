package bit.minisys.minicc.ncgen;


import bit.minisys.minicc.pp.internal.L;

import java.io.*;
import java.lang.module.ResolvedModule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

enum Register{
    eax,ebx,ecx,edx,esi,edi
};
enum JmpLabel{
    jl,jle,jg,jge,je,jne
}
class ICCode{
    public String op;
    public String res;
    public String opnd1;
    public String opnd2;
    public ICCode(String op, String opnd1, String opnd2,String res) {
        this.op = op;
        this.res = res;
        this.opnd1 = opnd1;
        this.opnd2 = opnd2;
    }

}

//局部变量符号表
class LocalVar{
    String name;
    int id; //分配的内存序号
    LocalVar(String n,int i){
        name = n;
        id = i;
    }
}
class TempVar{
    int id;
    String name;
    boolean IsArray;
    boolean scanf_f;

    TempVar(String n,int i) {
        name = n;
        id = i;
        IsArray = false;
        scanf_f = false;
    }
}


public class TXJCodeGen {

        List<String> ics = new ArrayList<>();
        StringBuilder ncs_init = new StringBuilder();
        StringBuilder ncs = new StringBuilder();
        StringBuilder ncs_data = new StringBuilder();
        int stringId = 1;
        boolean scanf_flag = false;

        String[] jmp_name = new String[]{
                "jl","jle","jg","jge","je","jne"
        };
        String[] reg_name = new String[]{
            "eax","ebx","ecx","edx","esi","edi"
        };
        int pop_var = 0; //传递参数
        int in_var = 0; //局部变量

        boolean reg_used[] = new boolean[]{false,false,false,false,false,false};
        HashMap<String,LocalVar> hashMapLocal = new HashMap<>();
        HashMap<String,TempVar> hashMapTemp = new HashMap<>();
        JmpLabel jmp_id ;

        public boolean isNumeric(String str){

            Pattern pattern = Pattern.compile("[0-9]*");

            return pattern.matcher(str).matches();

        }
        public int RegAlloc(){
            for(int i=0;i<6;i++)
            {
                if(!reg_used[i]){
                    reg_used[i] = true;
                    return i;
                }
            }
            return -1;
        }
        public void RegFree(int id){
            if(id>=0&&id<6){
                reg_used[id] = false;
            }
        }

        public void run(String iFile, String oFile) throws Exception{
            //读取文件
            File file = new File(iFile);
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(file));
            String temp;
            while((temp=reader.readLine())!=null){
                //System.out.println(temp);
                ics.add(temp);
            }
            reader.close();
            //遍历ic
            ncs_init.append(".386\n" +
                    ".model flat,stdcall\n" +
                    "option casemap:none\n" +
                    "includelib msvcrt.lib\n"+
                    "printf   PROTO  C:dword,:vararg\n"+
                    "scanf    PROTO  C:dword,:vararg\n"
                    );
            ncs_data.append("\n.data\nInMsg  db   '%d',0\nIntPrinMsg   db   '%d',0ah,0\n");
            ncs.append("\n.code\n");
            for(String ic:ics){
                //匹配正则表达式
                ic = ic.replace("(","");
                ic = ic.replace(")","");
                String[] as = ic.split(",");

                ICCode icCode = new ICCode(as[0],as[1],as[2],as[3]);
                //System.out.println(icCode.op);
                if(icCode.op.equals("proc")) {
                    ncs.append("\n"+icCode.res+" proc C\n");
                    ncs.append("push ebp"+"\n");
                    ncs.append("mov ebp,esp"+"\n");
                    //分配固定栈大小
                    ncs.append("sub esp,160\n" +"push ebx\n" +
                                    "push ecx\n" +
                                    "push edx\n" +
                                    "push esi\n" +
                                    "push edi\n"+
                            "\nlea edi,[ebp-160]\nmov ecx,40\nmov eax,0cccccccch\nrep stos dword ptr[edi]\n\n");
                    in_var = 4;
                    pop_var = 8;
                }
                else if(icCode.op.equals("endp")){
                    ncs.append(icCode.res+" endp \n");
                }
                //弹出参数，也就是形式参数赋值
                else if(icCode.op.equals("pop")){
                    //如果该变量已经分配内存，那么直接可以使用
                    if(hashMapLocal.containsKey(icCode.res)){

                    }
                    else{
                        //分配内存
                        LocalVar localVar = new LocalVar(icCode.res,in_var);
                        hashMapLocal.put(icCode.res,localVar);
                        //把参数传入
                        int reg = RegAlloc();
                        reg_used[reg] = false;
                        ncs.append("mov "+reg_name[reg]+", dword ptr[ebp+"+pop_var+"]\n");
                        ncs.append("mov dword ptr[ebp-"+in_var+"],"+reg_name[reg]+"\n");
                        in_var += 4;

                    }
                }
                else if(icCode.op.equals("=")){
                    //看res
                    String res = "";
                    int reg1 = -1;
                    int reg2 = -1;
                    if(icCode.res.contains("%")){
                         TempVar tv = hashMapTemp.get(icCode.res);
                         reg1 = tv.id;
                        if(tv.IsArray){
                            res = "dword ptr["+reg_name[tv.id]+"]";
                        }
                        else{
                            res = reg_name[tv.id];
                        }

                    }else{
                        //那么就是标识符
                        if(hashMapLocal.get(icCode.res)!=null){
                            LocalVar lv = hashMapLocal.get(icCode.res);
                            res = "dword ptr[ebp-"+lv.id+"]";

                        }else{
                            LocalVar lv = new LocalVar(icCode.res,in_var);
                            in_var+=4;
                            hashMapLocal.put(lv.name,lv);
                            res = "dword ptr[ebp-"+lv.id+"]";
                        }
                    }

                    //现在看opnd1
                    String opnd1 = icCode.opnd1;
                    int regArray2 = -1;
                    int reg = -1;
                    int reg3 = -1;
                    if(icCode.opnd1.contains("%")){
                        TempVar tv = hashMapTemp.get(opnd1);
                        reg2 = tv.id;
                        if(tv.IsArray){
                            reg = RegAlloc();
                            ncs.append("mov "+reg_name[reg]+", dword ptr["+reg_name[tv.id]+"]\n");
                            opnd1 = reg_name[reg];
                        }
                        else{
                            if(tv.scanf_f){
                                reg3 = RegAlloc();

                                ncs.append("lea "+reg_name[reg3]+", dword ptr[ebp-"+tv.id+"]\n");
                                opnd1 = reg_name[reg3];
                            }else {
                                opnd1 = reg_name[tv.id];
                            }

                        }
                    }else if(!isNumeric(opnd1)){
                        //那么就是标识符
                        if(hashMapLocal.get(icCode.opnd1)!=null){
                            LocalVar lv = hashMapLocal.get(icCode.opnd1);
                            reg3 = RegAlloc();
                            reg_used[reg3] = false;
                            ncs.append("mov "+reg_name[reg3]+", dword ptr[ebp-"+lv.id+"]\n");
                            opnd1 = reg_name[reg3];
                        }else{
                            LocalVar lv = new LocalVar(icCode.opnd1,in_var);
                            in_var+=4;
                            hashMapLocal.put(lv.name,lv);
                            reg3 = RegAlloc();
                            reg_used[reg3] = false;
                            ncs.append("mov "+reg_name[reg3]+", dword ptr[ebp-"+lv.id+"]\n");
                            opnd1 = reg_name[reg3];
                        }
                    }
                    int reg4 = -1;
                    if(scanf_flag){
                        reg4 = RegAlloc();
                        reg_used[reg4] = false;
                        ncs.append("mov "+reg_name[reg3]+", ["+opnd1+"]\n");
                        opnd1 = reg_name[reg3];
                    }
                    ncs.append("mov "+res+", "+opnd1+"\n");
                    RegFree(reg);
                    RegFree(reg1);
                    RegFree(reg2);
                    RegFree(reg3);
                    RegFree(reg4);

                }

                else if(icCode.op.equals("label")){
                    String l_id = icCode.res.replace("%","");
                    String lable = "L" + l_id;
                    ncs.append(lable+": \n");
                }
                else if(icCode.op.equals("<=")){
                    //先看opnd1
                    int reg1 = -1;
                    jmp_id = JmpLabel.jg;
                    String var1 = icCode.opnd1;
                    int reg = -1;
                    if(icCode.opnd1.contains("%")){
                        //是否是数组
                        TempVar tv = hashMapTemp.get(icCode.opnd1);
                        if(tv.IsArray){
                            //是数组
                            reg1 = RegAlloc();
                            reg_used[tv.id] = false;
                            hashMapTemp.remove(icCode.opnd1);
                            ncs.append("mov "+reg_name[reg1]+", ["+reg_name[tv.id]+"]\n");
                            var1 = reg_name[reg1];
                        }else{
                            reg_used[tv.id] = false;
                            hashMapTemp.remove(icCode.opnd1);
                            var1 = reg_name[tv.id];
                        }

                    }
                    else if(!isNumeric(icCode.opnd1)){
                        LocalVar lv = hashMapLocal.get(icCode.opnd1);
                        reg1 = RegAlloc();
                        var1 = reg_name[reg1];
                        ncs.append("mov "+var1+", dword ptr[ebp-"+lv.id+"]\n");

                    }

                    //看opnd2
                    String var2 = icCode.opnd2;
                    int reg2 = -1;
                    if(icCode.opnd2.contains("%")){
                        TempVar tv = hashMapTemp.get(icCode.opnd2);
                        reg_used[tv.id] = false;
                        hashMapTemp.remove(icCode.opnd2);
                        if(tv.IsArray){
                            reg2 = RegAlloc();
                            reg_used[tv.id] = false;
                            hashMapTemp.remove(icCode.opnd2);
                            ncs.append("mov "+reg_name[reg2]+", ["+reg_name[tv.id]+"]\n");
                            var2 = reg_name[reg2];
                        }else{
                            var2 = reg_name[tv.id];
                        }

                    }
                    else if(!isNumeric(icCode.opnd2)){
                        LocalVar lv = hashMapLocal.get(icCode.opnd2);
                        reg2 = RegAlloc();
                        var2 = reg_name[reg2];
                        ncs.append("mov "+var2+", dword ptr[ebp-"+lv.id+"]\n");

                    }
                    ncs.append("cmp "+var1+", "+var2+"\n");
                    RegFree(reg1);
                    RegFree(reg2);
                }
                else if(icCode.op.equals("<")){
                    //先看opnd1
                    int reg1 = -1;
                    jmp_id = JmpLabel.jge;
                    String var1 = icCode.opnd1;
                    int reg = -1;
                    if(icCode.opnd1.contains("%")){
                        //是否是数组
                        TempVar tv = hashMapTemp.get(icCode.opnd1);
                        if(tv.IsArray){
                            //是数组
                            reg1 = RegAlloc();
                            reg_used[tv.id] = false;
                            hashMapTemp.remove(icCode.opnd1);
                            ncs.append("mov "+reg_name[reg1]+", ["+reg_name[tv.id]+"]\n");
                            var1 = reg_name[reg1];
                        }else{
                            reg_used[tv.id] = false;
                            hashMapTemp.remove(icCode.opnd1);
                            var1 = reg_name[tv.id];
                        }

                    }
                    else if(!isNumeric(icCode.opnd1)){
                        LocalVar lv = hashMapLocal.get(icCode.opnd1);
                        reg1 = RegAlloc();
                        var1 = reg_name[reg1];
                        ncs.append("mov "+var1+", dword ptr[ebp-"+lv.id+"]\n");

                    }

                    //看opnd2
                    String var2 = icCode.opnd2;
                    int reg2 = -1;
                    if(icCode.opnd2.contains("%")){
                        TempVar tv = hashMapTemp.get(icCode.opnd2);
                        reg_used[tv.id] = false;
                        hashMapTemp.remove(icCode.opnd2);
                        if(tv.IsArray){
                            reg2 = RegAlloc();
                            reg_used[tv.id] = false;
                            hashMapTemp.remove(icCode.opnd2);
                            ncs.append("mov "+reg_name[reg2]+", ["+reg_name[tv.id]+"]\n");
                            var2 = reg_name[reg2];
                        }else{
                            var2 = reg_name[tv.id];
                        }

                    }
                    else if(!isNumeric(icCode.opnd2)){
                        LocalVar lv = hashMapLocal.get(icCode.opnd2);
                        reg2 = RegAlloc();
                        var2 = reg_name[reg2];
                        ncs.append("mov "+var2+", dword ptr[ebp-"+lv.id+"]\n");

                    }
                    ncs.append("cmp "+var1+", "+var2+"\n");
                    RegFree(reg1);
                    RegFree(reg2);

                }
                else if(icCode.op.equals(">")){
                    //先看opnd1
                    int reg1 = -1;
                    jmp_id = JmpLabel.jle;
                    String var1 = icCode.opnd1;
                    int reg = -1;
                    if(icCode.opnd1.contains("%")){
                        //是否是数组
                        TempVar tv = hashMapTemp.get(icCode.opnd1);
                        if(tv.IsArray){
                            //是数组
                            reg1 = RegAlloc();
                            reg_used[tv.id] = false;
                            hashMapTemp.remove(icCode.opnd1);
                            ncs.append("mov "+reg_name[reg1]+", ["+reg_name[tv.id]+"]\n");
                            var1 = reg_name[reg1];
                        }else{
                            reg_used[tv.id] = false;
                            hashMapTemp.remove(icCode.opnd1);
                            var1 = reg_name[tv.id];
                        }

                    }
                    else if(!isNumeric(icCode.opnd1)){
                        LocalVar lv = hashMapLocal.get(icCode.opnd1);
                        reg1 = RegAlloc();
                        var1 = reg_name[reg1];
                        ncs.append("mov "+var1+", dword ptr[ebp-"+lv.id+"]\n");

                    }

                    //看opnd2
                    String var2 = icCode.opnd2;
                    int reg2 = -1;
                    if(icCode.opnd2.contains("%")){
                        TempVar tv = hashMapTemp.get(icCode.opnd2);
                        reg_used[tv.id] = false;
                        hashMapTemp.remove(icCode.opnd2);
                        if(tv.IsArray){
                            reg2 = RegAlloc();
                            reg_used[tv.id] = false;
                            hashMapTemp.remove(icCode.opnd2);
                            ncs.append("mov "+reg_name[reg2]+", ["+reg_name[tv.id]+"]\n");
                            var2 = reg_name[reg2];
                        }else{
                            var2 = reg_name[tv.id];
                        }

                    }
                    else if(!isNumeric(icCode.opnd2)){
                        LocalVar lv = hashMapLocal.get(icCode.opnd2);
                        reg2 = RegAlloc();
                        var2 = reg_name[reg2];
                        ncs.append("mov "+var2+", dword ptr[ebp-"+lv.id+"]\n");

                    }
                    ncs.append("cmp "+var1+", "+var2+"\n");
                    RegFree(reg1);
                    RegFree(reg2);

                }
                else if(icCode.op.equals(">=")){
                    //先看opnd1
                    int reg1 = -1;
                    jmp_id = JmpLabel.jl;
                    String var1 = icCode.opnd1;
                    int reg = -1;
                    if(icCode.opnd1.contains("%")){
                        //是否是数组
                        TempVar tv = hashMapTemp.get(icCode.opnd1);
                        if(tv.IsArray){
                            //是数组
                            reg1 = RegAlloc();
                            reg_used[tv.id] = false;
                            hashMapTemp.remove(icCode.opnd1);
                            ncs.append("mov "+reg_name[reg1]+", ["+reg_name[tv.id]+"]\n");
                            var1 = reg_name[reg1];
                        }else{
                            reg_used[tv.id] = false;
                            hashMapTemp.remove(icCode.opnd1);
                            var1 = reg_name[tv.id];
                        }

                    }
                    else if(!isNumeric(icCode.opnd1)){
                        LocalVar lv = hashMapLocal.get(icCode.opnd1);
                        reg1 = RegAlloc();
                        var1 = reg_name[reg1];
                        ncs.append("mov "+var1+", dword ptr[ebp-"+lv.id+"]\n");

                    }

                    //看opnd2
                    String var2 = icCode.opnd2;
                    int reg2 = -1;
                    if(icCode.opnd2.contains("%")){
                        TempVar tv = hashMapTemp.get(icCode.opnd2);
                        reg_used[tv.id] = false;
                        hashMapTemp.remove(icCode.opnd2);
                        if(tv.IsArray){
                            reg2 = RegAlloc();
                            reg_used[tv.id] = false;
                            hashMapTemp.remove(icCode.opnd2);
                            ncs.append("mov "+reg_name[reg2]+", ["+reg_name[tv.id]+"]\n");
                            var2 = reg_name[reg2];
                        }else{
                            var2 = reg_name[tv.id];
                        }

                    }
                    else if(!isNumeric(icCode.opnd2)){
                        LocalVar lv = hashMapLocal.get(icCode.opnd2);
                        reg2 = RegAlloc();
                        var2 = reg_name[reg2];
                        ncs.append("mov "+var2+", dword ptr[ebp-"+lv.id+"]\n");

                    }
                    ncs.append("cmp "+var1+", "+var2+"\n");
                    RegFree(reg1);
                    RegFree(reg2);

                }
                else if(icCode.op.equals("jf")){
                    String l_id = icCode.res.replace("%","");
                    String lable = "L" + l_id;
                    ncs.append(jmp_id.name()+" "+lable+"\n");
                }

                else if(icCode.op.equals("*")){

                    String var1 = icCode.opnd1;
                    int reg1 = -1;
                    int reg2 = -1;
                    if(icCode.opnd1.contains("%")){
                        TempVar tv = hashMapTemp.get(icCode.opnd1);
                        reg_used[tv.id] = false;
                        hashMapTemp.remove(icCode.opnd1);
                        var1 = reg_name[tv.id];
                    }
                    else if(!isNumeric(icCode.opnd1)){
                        LocalVar lv = hashMapLocal.get(icCode.opnd1);
                        reg1 = RegAlloc();
                        var1 = reg_name[reg1];
                        ncs.append("mov "+var1+", dword ptr[ebp-"+lv.id+"]\n");
                    }
                    else{
                        reg1 = RegAlloc();
                        ncs.append("mov "+reg_name[reg1]+", "+var1);
                    }
                    String var2 = icCode.opnd2;
                    if(icCode.opnd2.contains("%")){
                        TempVar tv = hashMapTemp.get(icCode.opnd2);
                        reg_used[tv.id] = false;
                        hashMapTemp.remove(icCode.opnd2);
                        var2 = reg_name[tv.id];
                    }
                    else if(!isNumeric(icCode.opnd2)){
                        LocalVar lv = hashMapLocal.get(icCode.opnd2);
                        reg2 = RegAlloc();
                        var2 = reg_name[reg2];
                        ncs.append("mov "+var2+", dword ptr[ebp-"+lv.id+"]\n");
                    }else{
                        reg2 = RegAlloc();
                        ncs.append("mov "+reg_name[reg2]+", "+var2);
                    }
                    //看res
                    ncs.append("imul "+var1+", "+var2+"\n");
                    if(icCode.res.contains("%"))
                    {
                        int reg3 = RegAlloc();
                        TempVar tv = new TempVar(icCode.res,reg3);
                        hashMapTemp.put(icCode.res,tv);
                        ncs.append("mov "+reg_name[reg3]+", "+ var1+"\n");
                    }
                    else{
                        LocalVar lv = hashMapLocal.get(icCode.res);
                        ncs.append("mov dword ptr[ebp-"+ lv.id+"], "+var1+"\n");
                    }

                    RegFree(reg1);
                    RegFree(reg2);
                }
                //除法运算
                else if(icCode.op.equals("/")){
                    String var1 = icCode.opnd1;
                    int reg1 = -1;
                    int reg2 = -1;
                    ncs.append("xor edx, edx\n");
                    if(icCode.opnd1.contains("%")){
                        TempVar tv = hashMapTemp.get(icCode.opnd1);
                        reg_used[tv.id] = false;
                        hashMapTemp.remove(icCode.opnd1);
                        if(reg_used[0]){
                            ncs.append("push eax\n");
                        }
                        if(tv.id!=0){
                            ncs.append("mov eax, "+reg_name[tv.id]+"\n");
                        }

                    }
                    else{
                        if(reg_used[0]){
                            ncs.append("push eax\n");
                        }
                        reg_used[0] = true;
                        if(!isNumeric(icCode.opnd1)){
                            LocalVar lv = hashMapLocal.get(icCode.opnd1);
                            ncs.append("mov eax, "+" dword ptr[ebp-"+lv.id+"]\n");
                        }
                        else{
                            ncs.append("mov eax, "+var1+"\n");
                        }
                    }
                    //看opnd2
                    String var2 = icCode.opnd2;
                    if(icCode.opnd2.contains("%")){
                        TempVar tv = hashMapTemp.get(icCode.opnd1);
                        reg_used[tv.id] = false;
                        hashMapTemp.remove(icCode.opnd1);
                        var1 = reg_name[tv.id];
                    }
                    else if(!isNumeric(icCode.opnd2)){
                        LocalVar lv = hashMapLocal.get(icCode.opnd2);
                        reg2 = RegAlloc();
                        var2 = reg_name[reg2];
                        ncs.append("mov "+var2+", dword ptr[ebp-"+lv.id+"]\n");
                    }else{
                        reg2 = RegAlloc();
                        ncs.append("mov "+reg_name[reg2]+", "+var2);
                    }
                    //看res
                    ncs.append("idiv "+var2+"\n");
                    reg_used[0] = false;

                    if(icCode.res.contains("%"))
                    {
                        int reg3 = RegAlloc();
                        TempVar tv = new TempVar(icCode.res,reg3);
                        hashMapTemp.put(icCode.res,tv);
                        ncs.append("mov "+reg_name[reg3]+", eax"+"\n");
                    }
                    else{
                        LocalVar lv = hashMapLocal.get(icCode.res);
                        ncs.append("mov dword ptr[ebp-"+ lv.id+"], eax\n");
                    }
                    RegFree(reg1);
                    RegFree(reg2);


                }
                else if(icCode.op.equals("-")){
                    String var1 = icCode.opnd1;
                    int reg1 = -1;
                    int reg2 = -1;
                    if(icCode.opnd1.contains("%")){
                        TempVar tv = hashMapTemp.get(icCode.opnd1);
                        reg_used[tv.id] = false;
                        hashMapTemp.remove(icCode.opnd1);
                        var1 = reg_name[tv.id];
                    }
                    else if(!isNumeric(icCode.opnd1)){
                        LocalVar lv = hashMapLocal.get(icCode.opnd1);
                        reg1 = RegAlloc();
                        var1 = reg_name[reg1];
                        ncs.append("mov "+var1+", dword ptr[ebp-"+lv.id+"]\n");
                    }else{
                        int reg = RegAlloc();

                        ncs.append("mov "+reg_name[reg]+", "+icCode.opnd1+"\n");
                        var1 = reg_name[reg];
                    }
                    String var2 = icCode.opnd2;
                    if(icCode.opnd2.contains("%")){
                        TempVar tv = hashMapTemp.get(icCode.opnd2);
                        reg_used[tv.id] = false;
                        hashMapTemp.remove(icCode.opnd2);
                        var2 = reg_name[tv.id];
                    }
                    else if(!isNumeric(icCode.opnd2)){
                        LocalVar lv = hashMapLocal.get(icCode.opnd2);

                        reg2 = RegAlloc();
                        var2 = reg_name[reg2];
                        ncs.append("mov "+var2+", dword ptr[ebp-"+lv.id+"]\n");
                    }
                    //看res
                    ncs.append("sub "+var1+", "+var2+"\n");
                    if(icCode.res.contains("%"))
                    {
                        int reg3 = RegAlloc();

                        TempVar tv = new TempVar(icCode.res,reg3);
                        hashMapTemp.put(tv.name, tv);
                        ncs.append("mov "+reg_name[reg3]+", "+ var1+"\n");
                    }
                    else{
                        LocalVar lv = hashMapLocal.get(icCode.res);
                        ncs.append("mov dword ptr[ebp-"+ lv.id+"], "+var1+"\n");
                    }

                    RegFree(reg1);
                    RegFree(reg2);
                }
                else if(icCode.op.equals("+")){
                    String var1 = icCode.opnd1;
                    int reg1 = -1;
                    int reg2 = -1;
                    if(icCode.opnd1.contains("%")){
                        TempVar tv = hashMapTemp.get(icCode.opnd1);
                        reg_used[tv.id] = false;
                        hashMapTemp.remove(icCode.opnd1);
                        var1 = reg_name[tv.id];
                    }
                    else if(!isNumeric(icCode.opnd1)){
                        LocalVar lv = hashMapLocal.get(icCode.opnd1);
                        reg1 = RegAlloc();
                        var1 = reg_name[reg1];
                        ncs.append("mov "+var1+", dword ptr[ebp-"+lv.id+"]\n");
                    }
                    String var2 = icCode.opnd2;
                    if(icCode.opnd2.contains("%")){
                        TempVar tv = hashMapTemp.get(icCode.opnd2);
                        reg_used[tv.id] = false;
                        hashMapTemp.remove(icCode.opnd2);
                        var2 = reg_name[tv.id];
                    }
                    else if(!isNumeric(icCode.opnd2)){
                        LocalVar lv = hashMapLocal.get(icCode.opnd2);
                        reg2 = RegAlloc();
                        var2 = reg_name[reg2];
                        ncs.append("mov "+var2+", dword ptr[ebp-"+lv.id+"]\n");
                    }
                    //看res
                    ncs.append("add "+var1+", "+var2+"\n");
                    if(icCode.res.contains("%"))
                    {
                        int reg3 = RegAlloc();
                        TempVar tv = new TempVar(icCode.res,reg3);

                        hashMapTemp.put(tv.name, tv);
                        ncs.append("mov "+reg_name[reg3]+", "+ var1+"\n");
                    }
                    else{
                        LocalVar lv = hashMapLocal.get(icCode.res);
                        ncs.append("mov dword ptr[ebp-"+ lv.id+"], "+var1+"\n");
                    }

                    RegFree(reg1);
                    RegFree(reg2);
                }
                else if(icCode.op.equals("%")){
                    String var1 = icCode.opnd1;
                    int reg1 = -1;
                    int reg2 = -1;
                    ncs.append("xor edx, edx\n");
                    //看opnd1
                    if(icCode.opnd1.contains("%")){
                        TempVar tv = hashMapTemp.get(icCode.opnd1);
                        reg_used[tv.id] = false;
                        hashMapTemp.remove(icCode.opnd1);
                        if(reg_used[0]){
                            ncs.append("push eax\n");
                        }
                        if(tv.id!=0){
                            ncs.append("mov eax, "+reg_name[tv.id]+"\n");
                        }

                    }
                    else{
                        if(reg_used[0]){
                            ncs.append("push eax\n");
                        }
                        reg_used[0] = true;
                        if(!isNumeric(icCode.opnd1)){
                            LocalVar lv = hashMapLocal.get(icCode.opnd1);
                            ncs.append("mov eax, "+" dword ptr[ebp-"+lv.id+"]\n");
                        }
                        else{
                            ncs.append("mov eax, "+var1+"\n");
                        }
                    }
                    //看opnd2
                    String var2 = icCode.opnd2;
                    if(icCode.opnd2.contains("%")){
                        TempVar tv = hashMapTemp.get(icCode.opnd1);
                        reg_used[tv.id] = false;
                        hashMapTemp.remove(icCode.opnd1);
                        var1 = reg_name[tv.id];
                    }
                    else if(!isNumeric(icCode.opnd2)){
                        LocalVar lv = hashMapLocal.get(icCode.opnd2);
                        reg2 = RegAlloc();
                        var2 = reg_name[reg2];
                        ncs.append("mov "+var2+", dword ptr[ebp-"+lv.id+"]\n");
                    }else{
                        reg2 = RegAlloc();
                        ncs.append("mov "+reg_name[reg2]+", "+var2+"\n");
                    }
                    //看res
                    ncs.append("idiv "+var2+"\n");
                    reg_used[0] = false;

                    if(icCode.res.contains("%"))
                    {
                        int reg3 = RegAlloc();
                        TempVar tv = new TempVar(icCode.res,reg3);
                        hashMapTemp.put(icCode.res,tv);
                        ncs.append("mov "+reg_name[reg3]+", edx"+"\n");
                    }
                    else{
                        LocalVar lv = hashMapLocal.get(icCode.res);
                        ncs.append("mov dword ptr[ebp-"+ lv.id+"], edx\n");
                    }
                    RegFree(reg1);
                    RegFree(reg2);

                }
                else if(icCode.op.equals("++")){
                    //System.out.println(icCode.opnd1);
                    String res = "";
                    if(icCode.res.contains("%")){
                        int reg = RegAlloc();
                        TempVar tv = new TempVar(icCode.res,reg);
                        hashMapTemp.put(tv.name,tv);
                        res = reg_name[reg];
                        //判断有无赋值
                    }
                    else{
                        LocalVar lv = hashMapLocal.get(icCode.res);
                        res = "dword ptr [ebp-"+lv.id+"]";
                    }
                    //如果是左增++a
                    if(!icCode.opnd2.contains("_")){
                        //System.out.println(icCode.opnd2);
                        LocalVar lv = hashMapLocal.get(icCode.opnd2);

                        ncs.append("add dword ptr [ebp-"+lv.id+"],1\n");
                        int r = RegAlloc();
                        reg_used[r] = false;
                        ncs.append("mov "+reg_name[r]+", dword ptr[ebp-"+lv.id+"]\n");
                        ncs.append("mov "+res+", "+reg_name[r]+"\n");
                    }
                    else {
                        LocalVar lv = hashMapLocal.get(icCode.opnd1);
                        int r = RegAlloc();
                        reg_used[r] = false;
                        ncs.append("mov "+reg_name[r]+", dword ptr[ebp-"+lv.id+"]\n");
                        ncs.append("mov "+res+", "+reg_name[r]+"\n");
                        ncs.append("add dword ptr [ebp-"+lv.id+"],1\n");
                    }

                }
                else if(icCode.op.equals("==")){
                    //先看opnd1
                    int reg1 = -1;
                    jmp_id = JmpLabel.jne;
                    String var1 = icCode.opnd1;
                    if(icCode.opnd1.contains("%")){
                        TempVar tv = hashMapTemp.get(icCode.opnd1);
                        reg_used[tv.id] = false;
                        hashMapTemp.remove(icCode.opnd1);
                        var1 = reg_name[tv.id];
                    }
                    else if(!isNumeric(icCode.opnd1)){
                        LocalVar lv = hashMapLocal.get(icCode.opnd1);
                        reg1 = RegAlloc();
                        var1 = reg_name[reg1];
                        ncs.append("mov "+var1+", dword ptr[ebp-"+lv.id+"]\n");

                    }

                    //看opnd2
                    String var2 = icCode.opnd2;
                    int reg2 = -1;
                    if(icCode.opnd2.contains("%")){
                        TempVar tv = hashMapTemp.get(icCode.opnd2);
                        reg_used[tv.id] = false;
                        hashMapTemp.remove(icCode.opnd2);
                        var2 = reg_name[tv.id];
                    }
                    else if(!isNumeric(icCode.opnd2)){
                        LocalVar lv = hashMapLocal.get(icCode.opnd2);
                        reg2 = RegAlloc();
                        var2 = reg_name[reg2];
                        ncs.append("mov "+var2+", dword ptr[ebp-"+lv.id+"]\n");

                    }
                    ncs.append("cmp "+var1+", "+var2+"\n");
                    RegFree(reg1);
                    RegFree(reg2);
                }
                else if(icCode.op.equals("push")){

                    if(icCode.res.charAt(0)=='"'){
                        //说明压入是字符串e
                        //创建一部分数据区
                        pop_var += 4;
                        String name = "_string"+stringId;
                        if(icCode.res.contains("\\n")){

                            icCode.res = icCode.res.replace("\\n","");
                            if(!icCode.res.equals("\"\""))
                                ncs_data.append(name+"    db     "+icCode.res+",0ah,0\n");
                            else{
                                ncs_data.append(name+"   db   0ah,0\n");
                            }
                        }
                        else{
                            ncs_data.append(name+"    db     "+icCode.res+",0\n");
                        }
                        stringId ++;
                        ncs.append("push offset "+name+"\n");

                    }
                    else if(isNumeric(icCode.res)){
                        //压入数字
                        ncs.append("push "+icCode.res+"\n");
                        pop_var += 4;
                    }
                    else if(!icCode.res.contains("%")){

                        LocalVar lv = hashMapLocal.get(icCode.res);
                        //hashMapLocal.put(lv.name,lv);
                        ncs.append("push dword ptr [ebp-"+lv.id+"]\n");
                        pop_var += 4;
                    }else{

                        TempVar tv = hashMapTemp.get(icCode.res);
                        reg_used[tv.id] = false;
                        if(tv.IsArray){
                            ncs.append("push dword ptr["+reg_name[tv.id]+"]\n");
                        }else{
                            ncs.append("push "+reg_name[tv.id]+"\n");
                        }

                        pop_var += 4;
                    }
                }
                else if(icCode.op.equals("call")){
                    scanf_flag =false;

                    if(icCode.opnd1.equals("Mars_PrintStr")) {
                        //没有返回值

                        ncs.append("call printf \n");
                    }
                    else if(icCode.opnd1.equals("Mars_GetInt")){
                        scanf_flag = true;

                        int reg=RegAlloc();
                        reg_used[reg] = false;
//                        reg_used[0] = false;
                        TempVar tv = new TempVar(icCode.res,in_var);
                        tv.scanf_f = true;
                        hashMapTemp.put(icCode.res,tv);
                        ncs.append("lea "+reg_name[reg]+", dword ptr[ebp-"+in_var+"]\n");

                        ncs.append("pushad \npush "+reg_name[reg]+"\n");
                        ncs.append("push offset InMsg\n");
                        ncs.append("call scanf\n");
                        ncs.append("add esp,8\npopad\n");
                        in_var+=4;
                        continue;
                    }
                    else if(icCode.opnd1.equals("Mars_PrintInt")){
                        ncs.append("push offset IntPrinMsg\n");
                        ncs.append("call printf\n");
                        pop_var +=4;

                    }
                    else{
                        ncs.append("call "+icCode.opnd1+'\n');
                        reg_used[0] = true;
                        int reg = RegAlloc();
                        TempVar tv = new TempVar(icCode.res,reg);
                        hashMapTemp.put(tv.name,tv);
                        reg_used[0] = false;
                        ncs.append("mov "+reg_name[reg]+", eax\n");
                        ncs.append("add esp, "+(pop_var-8)+"\n");
                        pop_var = 8;
                        continue;
                    }
                    ncs.append("add esp, "+(pop_var-8)+"\n");
                    pop_var = 8;
                }
                else if(icCode.op.equals("ret")){

                    if(!icCode.opnd1.equals('_')){
                        if(isNumeric(icCode.opnd1)){
                            ncs.append("mov eax," + icCode.opnd1+"\n");
                        }
                        else{
                            LocalVar lv = hashMapLocal.get(icCode.opnd1);
                            ncs.append("mov eax, dword ptr[ebp-"+lv.id+"]\n");
                        }
                    }
                    ncs.append("\n" +
                            "pop edi\n" +
                            "pop esi\n" +
                            "pop edx\n" +
                            "pop ecx\n" +
                            "pop ebx\nmov esp,ebp\n" +
                            "pop ebp\n"+"ret\n");
                }
                else if(icCode.op.equals("jmp")){
                    String l_id = icCode.res.replace("%","");
                    String lable = "L" + l_id;
                    ncs.append("jmp "+lable+"\n");
                }
                else if(icCode.op.equals("[]")){

                    int reg = RegAlloc();
                    TempVar tv = new TempVar(icCode.res,reg);

                    //取出数组元素
                    LocalVar lv = hashMapLocal.get(icCode.opnd1);
                    int baseadd = lv.id;//取出基址
                    int addr = 0;
                    String name = "";
                    //判断下标是否是数字
                    if(isNumeric(icCode.opnd2)){
                        addr = baseadd + 4*Integer.parseInt(icCode.opnd2);
                        name = "dword ptr[ebp-"+Integer.toString(addr)+"]";

                    }
                    else if(!icCode.opnd2.contains("%")){
                        LocalVar lv2 = hashMapLocal.get(icCode.opnd2);
                        int reg2 = RegAlloc();
                        reg_used[reg2]=false;
                        ncs.append("mov "+reg_name[reg2]+", dword ptr[ebp-"+lv2.id+"]\n");
                        ncs.append("imul "+reg_name[reg2]+", 4\n");
                        name = "dword ptr[ebp-"+baseadd+"]["+reg_name[reg2]+"]";
                    }else{
                        TempVar tv2 = hashMapTemp.get(icCode.opnd2);
                        reg_used[tv2.id] = false;
                        ncs.append("imul "+reg_name[tv2.id]+", 4\n");
                        name = "dword ptr[ebp-"+baseadd+"]["+reg_name[tv2.id]+"]";
                    }
                    ncs.append("lea "+reg_name[reg]+", "+name+" \n");
                    tv.IsArray = true;

                    hashMapTemp.put(tv.name,tv);
                }
                else if(icCode.op.equals("arr[]")){
                    //声明数组，分配空间
                    int num = Integer.parseInt(icCode.opnd2);
                    in_var += num * 4-4;
                    LocalVar lv = new LocalVar(icCode.opnd1,in_var);

                    hashMapLocal.put(lv.name,lv);
                    in_var += 4;
                }
                //break;

            }
            ncs.append("end main\n");
            try{
                System.out.println(oFile);
                FileWriter fileWriter = new FileWriter(new File(oFile));

                fileWriter.write(ncs_init.toString());
                fileWriter.write(ncs_data.toString());
                fileWriter.write(ncs.toString());
                fileWriter.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }


}
