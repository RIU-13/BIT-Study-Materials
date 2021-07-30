#include <iostream>
#include <fstream>
#include <vector>
#include <io.h>
#include <string>
#include <sstream>
#include <time.h>
using namespace std;
int ans[1000][1000];
int mat[1000][1000];


void mul(int n)
{
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            ans[i][j] = 0;
            for (int k = 0; k < n; k++)
            {
                ans[i][j] += mat[i][k] * mat[k][j];
            }
        }
    }
}

int main()
{
    //文件句柄
    long hFile = 0;

    //文件信息
    struct _finddata_t file_info;
    string in_path = "../../data";
    string in_name;
    string curr = in_path + string("/*.txt");
    //cout << curr << endl;
    long handle = _findfirst(curr.c_str(), &file_info);
    if (handle == -1L)
    {
        cout << "not find matrix.txt files" << endl;
        return 0;
    }
    do
    {
        in_name = in_path + "/" + string(file_info.name);

        ifstream infile;
        infile.open(in_name, ios::in);
        if (!infile.is_open())
        {
            cout << "read " << in_name << " file error!" << endl;
            continue;
        }
        //开始处理
        cout << in_name << endl;

        string s;
        vector<vector<int>> v;
        while (getline(infile, s))
        {
            vector<int> numbers;
            stringstream srow;
            srow << s;

            int val;
            while (srow >> val)
                numbers.push_back(val);

            v.push_back(numbers);
        }

        int exec_times = 10;
        double total_time = 0;

        int n = v.size();

        //将vector类型转化为二位数组形式
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                mat[i][j] = v[i][j];

        for (int i = 0; i < exec_times; i++)
        {

            clock_t st = clock();

            mul(n);

            clock_t ed = clock();
            double interval = (double)(ed - st) / CLOCKS_PER_SEC;
            printf("%.5fs ", interval);
            total_time += interval;
        }

        printf(" | average_time : %.5fs\n", total_time / exec_times);
    } while (!(_findnext(handle, &file_info)));
    system("pause");
    return 0;
}