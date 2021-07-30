# 声明全局变量

SendSize = 2*1024

DataSize = 4*1024     # 4KB
ErrorRate = 9
LostRate = 7

SWsize = 4
Timeout = 2      # 10s

NextSeqNo = 0
MaxSeq = 7

data = ""
cnt = 0 # 表示第几帧
Ack = [False for v in range(1000)]
status = [0 for _ in range(1000)]

wd_left = 0 #窗口的左边界
wd_right = 3 #窗口的右边界

for i in range(0,1):
    print(i)