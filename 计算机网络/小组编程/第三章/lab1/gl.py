# 声明全局变量
'''
    author: Tangxiaojuan
    date: 2021-04-16
    content: 声明全局变量
'''
SendSize = 2*1024       # 帧中所含数据的大小
FILESIZE = 3*1024*1024       # 文件的大小
DataSize = 4*1024     # 发送帧的大小
ErrorRate = 9         # 帧的错误率：0.1
LostRate = 7          # 帧的丢失率：0.3
ReadFile = 'sender.txt'    #sender读取的文件
WFileName = "receiver.txt"  #receiver写入的文件
SWsize = 7              # 窗口大小
Timeout = 2             # 计时器超时时间


MaxSeq = 8              # 最大的可编码序号，也就是有3位


cnt = 0                 # 表示第几帧（针对数据而言），正在传送包的序号（争对全局）
Ack = [False for v in range(FILESIZE//SendSize+2)]      # 有无确认
status = [0 for _ in range(FILESIZE//SendSize+2)]

wd_left = 0      #窗口的左边界（全局）
wd_right = 3        #窗口的右边界（全局）

