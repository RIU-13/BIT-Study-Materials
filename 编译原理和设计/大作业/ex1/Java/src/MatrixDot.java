
import java.io.*;
import java.util.*;

public class MatrixDot {
    public static void main(String[] args)
    {
        String path = "../../data";
        File file = new File(path);
        File[] fs = file.listFiles();

        for(File f : fs)
        {
            if(f.getName().endsWith(".txt"))
            {
                try{
                    //读取文件
                    System.out.println(f);
                    BufferedReader r_file = new BufferedReader(new FileReader(f));
                    String str;
                    ArrayList<ArrayList<Integer>> rows = new ArrayList<ArrayList<Integer>>();
                    while((str = r_file.readLine())!=null)
                    {
                        String[] row_str = str.split(" ");
                        ArrayList<Integer> row = new ArrayList<Integer>();
                        for(int i=0;i<row_str.length;i++)
                        {
                            int tmp = Integer.parseInt(row_str[i]);
                            row.add(tmp);
                        }
                        rows.add(row);
                    
                    }
                    r_file.close();

                    //根据txt文件的内容构造矩阵
                    myMatrix A = new myMatrix(rows);


                    //开始计算矩阵城际
                    int times = 10;
                    double total_time = 0.0;
                    for(int i=0;i<times;i++)
                    {
                        //开始时间
                        long st = System.currentTimeMillis();
                        //计算
                        A.dot(A);
                        //结束时间
                        long ed = System.currentTimeMillis();

                        double cost_time = (ed - st)/1000.0;
                        total_time += cost_time;
                        System.out.printf("%.5fs ",cost_time);

                    
                    }
                    
                    System.out.printf(" | average_time: %.5fs\n",total_time/times);
                   

                }catch(IOException e){}
                

            }
            
    }
        
    }

}

class myMatrix {

    private int[][] mat;
    private int n;  //列号
    private int m;  //行号

    //构造函数
    public myMatrix(ArrayList<ArrayList<Integer>> matArrays) {
        m = matArrays.size();
        n = matArrays.get(0).size();

        mat = new int[m][n];

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                mat[i][j] = matArrays.get(i).get(j);
    }
    //构造函数（参数为行列值）
    public myMatrix(int k,int t)
    {
        m = k;
        n = t;
        mat = new int[m][n];
    }
    //测试输出函数
    public void print()
    {
        System.out.println(Arrays.deepToString(mat));
    }

    //矩阵相乘this*B
    public void dot(myMatrix B)
    {
        //this.mat * a.mat
        int a = this.m;
        int b = B.m;
        int c = B.n;
        myMatrix res = new myMatrix(a,c);

        for(int i=0;i<a;i++)
        {
            for(int j=0;j<c;j++)
            {
                res.mat[i][j] = 0;
                for(int k=0;k<b;k++)
                {
                    res.mat[i][j] += this.mat[i][k] * B.mat[k][j];
                }
            }
        }

        //res.print();

    }

}
