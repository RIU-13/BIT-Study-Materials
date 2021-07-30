'''
name: Tangxiaojuan
date: 2021-02-26
content: matrix multiplication
'''
import time
import os


# A * B = ans
# A(a,b) B(b,c) ans(a,c)
def mul(A,B):
    a = len(A)
    b = len(B)
    c = len(B[0])
    ans = [[0 for _ in range(c)] for _ in range(a)]
    for i in range(a):
        for j in range(c):
            for k in range(b):
                ans[i][j] += A[i][k] * B[k][j]

    return ans

if __name__ == '__main__':

    # get filepath
    dir = '../../data'
    file_paths = [dir+'/'+file for file in os.listdir(dir) if os.path.splitext(file)[1] == ".txt"]
    # print(file_paths)


    for path in file_paths:
        print(path)
        with open(path,'r') as f:
            lines = f.readlines()

            # get matrix
            mat = []

            for line in lines:
                list = line.strip('\n').split(' ')
                numbers = [int(item) for item in list]
                mat.append(numbers)

            # print(mat)

            exec_times = 10
            total_time = 0
            for _ in range(exec_times):
                st = time.time()

                ans = mul(mat,mat)

                ed = time.time()
                cost_time = ed - st
                total_time += cost_time
                print("%.5fs"%cost_time,end=' ')

            print(" | average_time : %.5fs"%(total_time/exec_times))
