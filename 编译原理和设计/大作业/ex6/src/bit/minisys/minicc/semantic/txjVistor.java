package bit.minisys.minicc.semantic;
import bit.minisys.minicc.parser.ast.*;


import java.util.*;

//符号表
class Symbol{
    public String name;
    public String type;
    public String numType;
    public List<Symbol> param = new ArrayList<Symbol>(); //函数定义的参数表

    public int length;

    //public boolean f ;//标记是函数声明还是非函数声明
    //方便测试
    public void print()
    {
        System.out.printf("{name:%s;type:%s;numType:%s;lenth:%d}\n",name,type,numType,length);
    }
}

public class txjVistor implements ASTVisitor{
    //局部表作为一个链，每个局部表里面又有对应的name和符号
    //public List<> LocalSymbolTables = new ArrayList<>();
    public Stack<Map<String,Symbol>> LocalSymbolTables = new Stack<Map<String,Symbol>>();
    //映射关系方便查找
    public Map<String,Symbol> GlobalSymbolTable = new HashMap<>();
    //goto 语句待检查的标记
    public Stack<List<String>> ToCheckGoto = new Stack<>();
    public Stack<List<String>> ToChecklabel = new Stack<>();

    boolean flag = false;
    public int fIter = 0;//对循环语句标记，看是否在循环语句里面
    public int freturn = 0;

    void printError(String errorstring,int errorid)
    {
        if(!flag)
        {
            System.out.println("errors:");
            System.out.println("------------------------------------");
            flag = true;
        }
        System.out.println("ES0"+errorid+" >> "+errorstring);
    }
    void printErrorEnd()
    {
        if(flag)
            System.out.println("------------------------------------");
    }

    //有无重复定义变量
    boolean checkRepeat(Symbol sb,Map<String,Symbol> sts)
    {
        if(sts.containsKey(sb.name))
        {
            if(sb.type == "FunctionDefine")
            //如果包含，则表示重复
                printError(sb.type+" "+sb.name+" is defined.",2);
            else
                printError(sb.type+" "+ sb.name + " has been declarated.",2);
            return false;
        }
        else
        {
            sts.put(sb.name,sb);
            return true;
        }
    }
    //判断参数类型是否一致
    boolean checkMatch(Symbol A,Symbol B)
    {

        if(!A.numType.equals(B.numType))
        {
//            System.out.println(A.numType);
//            System.out.println(B.numType);
            return false;
        }

        else
            return true;
    }
    //看函数调用和变量有无定义
    boolean checkIn(Symbol sb,Map<String,Symbol> localTable,boolean fprint)
    {
        if(!localTable.containsKey(sb.name))
        {
            if(fprint)
            {
                if(sb.type == "FunctionCall")
                    printError(sb.type+" "+sb.name+" is not declarated.",1);
                else
                    printError(sb.type+" "+sb.name+" is not defined.",1);
            }

            return false;
        }
        else if(sb.type == "FunctionCall")
        {

            Symbol temp = localTable.get(sb.name);
            //先判断参数长度
            if(temp.param.size()!=sb.param.size())
            {
                if(fprint)
                    printError(sb.type+":"+sb.name+"'s"+" param num is not matched.",4);
                return false;
            }
            //System.out.println(temp.param.size());
            for(int i=0;i<temp.param.size();i++)
            {
                //判断参数类型是否相符
                if(!checkMatch(temp.param.get(i),sb.param.get(i)))
                {
                    //System.out.println(temp.param.get(i).numType);
                    if(fprint)
                        printError(sb.type+":"+sb.name+"'s"+" param type is not matched.",4);
                    return false;
                }

            }
            return true;
        }
        else
            return true;
    }

    @Override
    public void visit(ASTCompilationUnit program) throws Exception {
        //.out.println("visit program");
        //访问每一个子节点

        //program:translationUnit?EOF;
        //translationUnit: externalDeclaration+;
        //externalDeclaration:functionDefine
        //                    |declaration ';';
        for(int i=0;i<program.items.size();i++)
        {
            ASTNode child = program.items.get(i);
            if(child instanceof ASTFunctionDefine)
            {
                visit((ASTFunctionDefine) child);
            }
            else if(child instanceof ASTDeclaration)
            {
                visit((ASTDeclaration) child);
            }

        }
        printErrorEnd();
        //test();
    }
//    public void test()
//    {
        //压栈压的是值
//        Stack<String> t = new Stack<String>();
//        String temp = "123";
//        t.push(temp);
//        //temp = t.pop();
//        temp = "abcd";
//        //t.push(temp);
//        System.out.println(t.pop());
//    }
    @Override
    public void visit(ASTDeclaration declaration) throws Exception {

    }
    public void visit(ASTDeclaration declaration, Map<String,Symbol> localTable) throws Exception {

        Symbol sb = new Symbol();
        sb.type = declaration.getType();
        sb.numType = declaration.specifiers.get(0).value;
        for(int i=0;i<declaration.initLists.size();i++)
        {
            visit(declaration.initLists.get(i),localTable,sb.numType);
        }
    }
    @Override
    public void visit(ASTArrayDeclarator arrayDeclarator) throws Exception {

    }

    public void visit(ASTArrayDeclarator arrayDeclarator,Symbol sb) throws Exception {

    }

    @Override
    public void visit(ASTVariableDeclarator variableDeclarator) throws Exception {

    }
    public void visit(ASTVariableDeclarator variableDeclarator,Symbol sb) throws Exception {
        sb.name = variableDeclarator.identifier.value;
    }
    @Override
    public void visit(ASTFunctionDeclarator functionDeclarator) throws Exception {

    }
    public void visit(ASTFunctionDeclarator functionDeclarator,Symbol sb) throws Exception {

    }
    @Override
    public void visit(ASTParamsDeclarator paramsDeclarator) throws Exception {

    }

    public Symbol visitParams(ASTParamsDeclarator paramsDeclarator) throws Exception {
        //得到参数，存在符号中，以及局部变量表中
        //name 表示存放在哪个函数的局部变量表中
        Symbol param = new Symbol();
        String type = paramsDeclarator.declarator.getType();
        String numType = paramsDeclarator.specfiers.get(0).value;
        String name = paramsDeclarator.declarator.getName();
        param.type = type;
        param.name = name;
        param.numType = numType;
        //param.print();
        return param;
    }

    @Override
    public void visit(ASTArrayAccess arrayAccess) throws Exception {

    }

    @Override
    public void visit(ASTBinaryExpression binaryExpression) throws Exception {

    }
    public void visit(ASTBinaryExpression binaryExpression,Map<String,Symbol> localTable) throws Exception {
        String numType1="";
        if(binaryExpression.expr1 instanceof ASTIdentifier)
        {
            //取出表达式左值类型
            numType1 = visitId((ASTIdentifier) binaryExpression.expr1,localTable);
            if(numType1==null)
                return ;
        }
        else if(binaryExpression.expr1 instanceof ASTIntegerConstant)
        {
            numType1 = "int";
        }
        else if(binaryExpression.expr1 instanceof ASTFloatConstant)
        {
            numType1 = "float";
        }
        String numType2 ="";
        if(binaryExpression.expr2 instanceof ASTIdentifier)
        {
            //取出表达式左值类型
            numType2 = visitId((ASTIdentifier) binaryExpression.expr2,localTable);
            if(numType2==null)
                return ;
        }
        else if(binaryExpression.expr2 instanceof ASTIntegerConstant)
        {
            numType2 = "int";
        }
        else if(binaryExpression.expr2 instanceof ASTFloatConstant)
        {

            numType2 = "float";
        }
        String op = binaryExpression.op.value;
        switch (op)
        {
            case "|":
            case "&":
            case "^":
            case "<<":
            case ">>":
                if(numType1.equals("float")||numType2.equals("float")||numType1.equals("double")||numType2.equals("double"))
                {
                    printError("BinaryExpression:(<< >> & | ^)expression's should be int.",5);
                }
                break;
            default:
        }
    }

    @Override
    public void visit(ASTBreakStatement breakStat) throws Exception {
        if(fIter<=0)
        {
            //说明break没有在循环体里面
            printError("BreakStatement:must be in a LoopStatement.",3);
        }

    }

    @Override
    public void visit(ASTContinueStatement continueStatement) throws Exception {
        if(fIter<=0)
        {
            //说明break没有在循环体里面
            printError("BreakStatement:must be in a LoopStatement.",3);
        }
    }


    @Override
    public void visit(ASTCastExpression castExpression) throws Exception {

    }
    public void visit(ASTCastExpression castExpression, Map<String,Symbol> localTable) throws Exception {
        visit(castExpression.expr,localTable);
    }
    @Override
    public void visit(ASTCharConstant charConst) throws Exception {

    }

    @Override
    public void visit(ASTCompoundStatement compoundStat) throws Exception {
        //如果没有函数参数传过来
        //System.out.println("visit compoundstatement");
        Map<String,Symbol> localTable = new HashMap<>();
        for(int i=0;i<compoundStat.blockItems.size();i++)
        {
            ASTNode child = compoundStat.blockItems.get(i);
            if(child instanceof ASTDeclaration)
            {
                visit((ASTDeclaration) child,localTable);
            }
            //注意复合语句里面还有可能是复合语句
            else if(child instanceof ASTStatement)
            {
                //当前的符号表
                visit((ASTStatement) child,localTable);
            }

        }
    }


    public void visit(ASTCompoundStatement compoundStat,Map<String,Symbol> localTable) throws Exception {
        //System.out.println("visit compoundstatement");
        for(int i=0;i<compoundStat.blockItems.size();i++)
        {
            ASTNode child = compoundStat.blockItems.get(i);
            if(child instanceof ASTDeclaration)
            {
                visit((ASTDeclaration) child,localTable);
            }
            //注意复合语句里面还有可能是复合语句
            else if(child instanceof ASTStatement)
            {
                visit((ASTStatement) child,localTable);
            }

        }


    }
    @Override
    public void visit(ASTConditionExpression conditionExpression) throws Exception {

    }
    public void visit(ASTConditionExpression conditionExpression,Map<String,Symbol> localTable) throws Exception {
        visit(conditionExpression.condExpr,localTable);
        for(int i=0;i<conditionExpression.trueExpr.size();i++)
            visit(conditionExpression.trueExpr.get(i),localTable);
        visit(conditionExpression.falseExpr,localTable);

    }

    @Override
    public void visit(ASTExpression expression) throws Exception {

    }

    //表达式运算符不匹配
    //1. %不能是float，两边都不能是float
    //2. []里面不能是float
    //3. <<和>>不能是float，两边都不能是float
    //4.^和|和&不能是float

    public void visit(ASTExpression expression,Map<String,Symbol> localTable) throws Exception {
        if(expression instanceof ASTBinaryExpression) {
            visit((ASTBinaryExpression) expression,localTable);
        }
        else if(expression instanceof ASTCastExpression){
            visit((ASTCastExpression)expression,localTable);
        }
        else if(expression instanceof ASTUnaryExpression){
            visit((ASTUnaryExpression) expression ,localTable);
        }
        else if(expression instanceof ASTConditionExpression){
            visit((ASTConditionExpression) expression,localTable);
        }
        else if(expression instanceof ASTPostfixExpression){
            visit((ASTPostfixExpression) expression,localTable);
        }
        else if(expression instanceof ASTIdentifier){

            visit((ASTIdentifier) expression,localTable);
        }
        else if(expression instanceof ASTFunctionCall){
            visit((ASTFunctionCall) expression,localTable);
        }

    }
//    public void visit(ASTExpression expression,Symbol sb) throws Exception {
//
//    }
    @Override
    public void visit(ASTExpressionStatement expressionStat) throws Exception {

    }

    public void visit(ASTExpressionStatement expressionStat,Map<String,Symbol> localTable) throws Exception {
        for(int i=0;i<expressionStat.exprs.size();i++)
        {
            visit(expressionStat.exprs.get(i),localTable);
        }
    }

    @Override
    public void visit(ASTFloatConstant floatConst) throws Exception {

    }

    @Override
    public void visit(ASTFunctionCall funcCall) throws Exception {

    }

    public void visit(ASTFunctionCall funcCall,Map<String,Symbol> localTable) throws Exception {
        //检查函数是否定义
        String type = funcCall.getType();
        ASTIdentifier id = (ASTIdentifier)funcCall.funcname;
        String name = id.value;
        Symbol sb = new Symbol();
        sb.name = name;
        sb.type = type;

        for(int i=0;i<funcCall.argList.size();i++)
        {
            Symbol t = new Symbol();
            //这里只考虑常量和变量，不考虑其他复杂表达式
            t.numType = funcCall.argList.get(i).getType();
            if(t.numType == "IntegerConstant")
                t.numType = "int";
            else if(t.numType == "FloatConstant")
                t.numType = "float";
            else if(t.numType == "Identifier")
            {
                ASTIdentifier identifier = (ASTIdentifier) funcCall.argList.get(i);
                if(localTable.containsKey(identifier.value))
                {
                    //如果存在,看类型是否匹配
                    Symbol tp = localTable.get(identifier.value);
                    t.numType = tp.numType;

                }
                else
                {
                    printError(identifier.getType()+" "+identifier.value+" is not defined.",1);
                    //由于这里找不到identifier,所以特殊处理,赋值要赋相等类型的
                    String numType = GlobalSymbolTable.get(sb.name).param.get(i).numType;

                    t.numType = numType;
                    //System.out.println(numType);
                }

            }
            sb.param.add(t);
            //System.out.println(t.numType);
        }

        checkIn(sb,GlobalSymbolTable,true);
       // sb.param.get(1).print();
    }
    @Override
    public void visit(ASTGotoStatement gotoStat) throws Exception {
        List<String> gt = ToCheckGoto.pop();
        gt.add(gotoStat.label.value);
        ToCheckGoto.push(gt);
    }

    @Override
    public void visit(ASTIdentifier identifier) throws Exception {

    }
    public void visit(ASTIdentifier identifier,Map<String,Symbol> localTable) throws Exception {
        Symbol sb = new Symbol();
        sb.type = identifier.getType();
        sb.name = identifier.value;
        boolean t = false;
        if(LocalSymbolTables.size()==0)
        {
            t = checkIn(sb,localTable,true);
        }
        else
        {
            t = checkIn(sb,localTable,false);
            //当前没找到
            if(!t)
            {
                for(int i=0;i<LocalSymbolTables.size()-1;i++)
                {
                    t = checkIn(sb,LocalSymbolTables.get(i),false);
                    if(t)
                        break;

                }
                if(!t&&LocalSymbolTables.size()>=1)
                    checkIn(sb,LocalSymbolTables.get(LocalSymbolTables.size()-1),true);
            }

        }

    }
    public String visitId(ASTIdentifier identifier,Map<String,Symbol> localTable) throws Exception {
        Symbol sb = new Symbol();
        sb.type = identifier.getType();
        sb.name = identifier.value;
        boolean t = false;
        int find = -1; //记录在哪里找到的
        if(LocalSymbolTables.size()==0)
        {
            t = checkIn(sb,localTable,true);
        }
        else
        {
            t = checkIn(sb,localTable,false);
            //当前没找到
            if(!t)
            {
                for(int i=0;i<LocalSymbolTables.size()-1;i++)
                {
                    t = checkIn(sb,LocalSymbolTables.get(i),false);
                    if(t)
                    {
                        find = i;
                        break;
                    }


                }
                if(!t&&LocalSymbolTables.size()>=1)
                    checkIn(sb,LocalSymbolTables.get(LocalSymbolTables.size()-1),true);
                if(t)
                    find = LocalSymbolTables.size()-1;
            }

        }
        if(t)
        {
            Symbol sbt = new Symbol();
            if(find == -1)
            {
                sbt = localTable.get(identifier.value);
            }
            else{
                sbt = LocalSymbolTables.get(find).get(identifier.value);
            }
            //说明找到了，那么我需要找到哪一个类型
            //System.out.println(sbt.numType);
            return sbt.numType;
        }
        else
            return null;

    }

    @Override
    public void visit(ASTInitList initList) throws Exception {

    }
    public void visit(ASTInitList initList,Map<String,Symbol> localTable,String numType) throws Exception {
        Symbol sb = new Symbol();
        sb.numType = numType;
        sb.type = initList.declarator.getType();
        visit(initList.declarator,sb);
        for(int i=0;i<initList.exprs.size();i++)
        {
            visit(initList.exprs.get(i),localTable);
        }
        checkRepeat(sb,localTable);
        //sb.print();
    }
    @Override
    public void visit(ASTIntegerConstant intConst) throws Exception {

    }

    @Override
    public void visit(ASTIterationDeclaredStatement iterationDeclaredStat) throws Exception {

    }
    public void visit(ASTIterationDeclaredStatement iterationDeclaredStat,Map<String,Symbol> localTable) throws Exception {
        fIter ++;
        Map<String,Symbol> lt = new HashMap<>();
        if(iterationDeclaredStat.init!=null)
        {
            visit(iterationDeclaredStat.init,lt);
        }
        if(iterationDeclaredStat.cond!=null)
        {
            for(int i=0;i<iterationDeclaredStat.cond.size();i++)
                visit(iterationDeclaredStat.cond.get(i),lt);
        }
        if(iterationDeclaredStat.step!=null)
        {
            for(int i=0;i<iterationDeclaredStat.step.size();i++)
                visit(iterationDeclaredStat.step.get(i),lt);
        }
        if(iterationDeclaredStat.stat instanceof ASTCompoundStatement)
        {
            visit((ASTCompoundStatement) iterationDeclaredStat.stat,lt);
        }
        else
        {
            visit(iterationDeclaredStat.stat,lt);
        }

        fIter --;
    }
    @Override
    public void visit(ASTIterationStatement iterationStat) throws Exception {

    }
    public void visit(ASTIterationStatement iterationStat,Map<String,Symbol> localTable) throws Exception {
        fIter ++;
        Map<String,Symbol> lt = new HashMap<>();
        if(iterationStat.init!=null)
        {
            for(int i=0;i<iterationStat.init.size();i++)
                visit(iterationStat.init.get(i),lt);
        }
        if(iterationStat.cond!=null)
        {
            for(int i=0;i<iterationStat.cond.size();i++)
                visit(iterationStat.cond.get(i),lt);
        }
        if(iterationStat.step!=null)
        {
            for(int i=0;i<iterationStat.step.size();i++)
                visit(iterationStat.step.get(i),lt);
        }

        visit(iterationStat.stat,lt);
        fIter --;
    }

    @Override
    public void visit(ASTLabeledStatement labeledStat) throws Exception {
        List<String> lables = ToChecklabel.pop();
        String lb = labeledStat.label.value;
        //System.out.println(lb);
        for(int i=0;i<lables.size();i++)
        {
            if(lables.get(i).equals(lb))
            {
                //说明已经定义了该label，不能重复使用
                printError("Label: "+lb+" has been defined",1);
            }
        }
        lables.add(lb);
        ToChecklabel.push(lables);
    }

    @Override
    public void visit(ASTMemberAccess memberAccess) throws Exception {

    }

    @Override
    public void visit(ASTPostfixExpression postfixExpression) throws Exception {

    }
    public void visit(ASTPostfixExpression postfixExpression,Map<String,Symbol> localTable) throws Exception {
        visit(postfixExpression.expr,localTable);
    }
    @Override
    public void visit(ASTReturnStatement returnStat) throws Exception {
        freturn ++;
    }

    @Override
    public void visit(ASTSelectionStatement selectionStat) throws Exception {

    }

    @Override
    public void visit(ASTStringConstant stringConst) throws Exception {

    }

    @Override
    public void visit(ASTTypename typename) throws Exception {

    }

    @Override
    public void visit(ASTUnaryExpression unaryExpression) throws Exception {

    }
    public void visit(ASTUnaryExpression unaryExpression,Map<String,Symbol> localTable) throws Exception {
        visit(unaryExpression.expr,localTable);
    }

    @Override
    public void visit(ASTUnaryTypename unaryTypename) throws Exception {

    }


    @Override
    public void visit(ASTFunctionDefine functionDefine) throws Exception {
        //functionDefine:Type declarator '(' arguments? ')' body=compoundStatement;
        //System.out.println("visit FunctionDefine");

        List<String> ls = new ArrayList<>();
        List<String> gs = new ArrayList<>();
        ToChecklabel.push(ls);
        ToCheckGoto.push(gs);

        //函数名创建符号，添加到全局符号表中
        Symbol fundefsymbol = new Symbol();

        //局部符号表
        Map<String,Symbol> fundefSymbolTable = new HashMap<>();

        //填写相关信息
        fundefsymbol.numType = functionDefine.specifiers.get(0).value;
        fundefsymbol.type = functionDefine.getType();
        ASTFunctionDeclarator declarator = (ASTFunctionDeclarator) functionDefine.declarator;
        fundefsymbol.name = declarator.getName();
        fundefsymbol.length = declarator.params.size();
        //fundefsymbol.print();

        for(int i=0;i<declarator.params.size();i++) {

            Symbol sb = visitParams(declarator.params.get(i));
            if(checkRepeat(sb,fundefSymbolTable))
                fundefsymbol.param.add(sb);

        }
        //检查一下有无在全局符号表中
        checkRepeat(fundefsymbol,GlobalSymbolTable);

        //加入该函数的局部符号表中
        //开始访问主题部分了
        visit(functionDefine.body,fundefSymbolTable);
        //接下来检查一下goto语句的有无存在label
        //goto语句同处于一个函数中

        List<String> lst = ToChecklabel.pop();
        List<String> gst = ToCheckGoto.pop();

        for(int i=0;i<gst.size();i++)
        {
            boolean bt = false;
            String gotoString = gst.get(i);
            for(int j=0;j<lst.size();j++)
            {
                if(gotoString.equals(lst.get(j)))
                {
                    bt = true;
                    break;
                }
            }
            if(!bt)
            {
                printError("Label: "+gotoString+" is not defined.",7);
            }


        }
        if(freturn<=0)
        {
            printError("Function: "+fundefsymbol.name+" must have a return in the end.",8);
        }
    }

    @Override
    public void visit(ASTDeclarator declarator) throws Exception {

    }

    public void visit(ASTDeclarator declarator,Symbol sb) throws Exception {
        if(declarator instanceof ASTFunctionDeclarator)
        {
            visit((ASTFunctionDeclarator) declarator,sb);
        }
        else if(declarator instanceof ASTArrayDeclarator)
        {
            visit((ASTArrayDeclarator) declarator,sb);
        }
        else if(declarator instanceof ASTVariableDeclarator)
        {
            visit((ASTVariableDeclarator) declarator,sb);
        }


    }

    @Override
    public void visit(ASTStatement statement) throws Exception {

    }


    public void visit(ASTStatement statement,Map<String,Symbol> localTable) throws Exception {
        if(statement instanceof ASTExpressionStatement) {
            visit((ASTExpressionStatement) statement,localTable);
        }
        else if(statement instanceof ASTSelectionStatement) {
            visit((ASTSelectionStatement) statement);
        }
        else if(statement instanceof ASTContinueStatement) {
            visit((ASTContinueStatement)statement,localTable);
        }
        else if(statement instanceof ASTIterationStatement){
            LocalSymbolTables.push(localTable);
            visit((ASTIterationStatement) statement,localTable);
            LocalSymbolTables.pop();
        }
        else if(statement instanceof ASTCompoundStatement){
            //这里需要注意，复合语句里面的和外面的可以重复定义。
            // 除此之外，如果函数定义的话，要把参数传进去才对，但是循环语句就不用了。
            //这里是没有函数参数的复合语句
            LocalSymbolTables.push(localTable);
            visit((ASTCompoundStatement) statement);
            LocalSymbolTables.pop();
        }
        else if(statement instanceof ASTLabeledStatement){
            visit((ASTLabeledStatement) statement);
        }
        else if(statement instanceof ASTGotoStatement){
            visit((ASTGotoStatement) statement);
        }
        else if(statement instanceof ASTBreakStatement){
            visit((ASTBreakStatement) statement);
        }
        else if(statement instanceof ASTReturnStatement){
            visit((ASTReturnStatement) statement);
        }
        else if(statement instanceof ASTIterationDeclaredStatement){
            LocalSymbolTables.push(localTable);
            visit((ASTIterationDeclaredStatement) statement,localTable);
            LocalSymbolTables.pop();
        }

    }
    @Override
    public void visit(ASTToken token) throws Exception {

    }
}
