'''
    author: TangXiaojuan
    date: 2021-04-14
    content: Reliable file transfer using Go-Back-N
'''
# 1. 定义结构帧{data(1KB)、checksum(CRC-CCITT)、seq、ack}
# 2. 3bits进行帧编号
# 3. 窗口大小是4

import socket
import re
import time,threading
import CRC16CCITT
import threading
import gl
import random
from Frame import *

filelen = 0
UDPPort = 40207     # sender_port
FileLen = 0
ackTimes = 0

def cut_text(text,lenth):
    textArr = re.findall(r'[\s\S]{'+str(lenth)+'}', text)
    textArr.append(text[(len(textArr)*lenth):])
    return textArr


def recv_process(s):

    if(getattr(s, '_closed') == True):
        return

    r_data = s.recv(gl.DataSize)

    r_frame = Frame.ToFrame(r_data)

    Ack_num = r_frame.ack
    print("接收到确认包"+str(Ack_num))
    if(gl.Ack[Ack_num]):
        # 之前已经被确认了
        gl.cnt = Ack_num + 1
    else:
        gl.Ack[Ack_num] = True
        print(r_frame)
        gl.wd_left += 1
        gl.wd_right  += 1


# 设置计时器，如果超时，那么重传之后所有的包
def is_timeout(n):
    # print(str(n)+"包开始计时")
    time.sleep(gl.Timeout)
    # 未接收到
    if gl.Ack[n] == False:
        print("接收Ack"+str(n)+"包超时")
        if gl.cnt > n-1 :
            gl.cnt = n-1
        print("开始重传: ",gl.cnt)


if __name__ == '__main__':

    f = open(gl.txt_path,'r')
    content = f.read()
    f.close()

    # 分批次2KB发送数据
    data = cut_text(content,9)
    k = 0
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    s.bind(("",UDPPort))
    FileLen = len(data)
    print("文件总长度:",len(data))
    while True:
        # print("k :",k)
        if gl.cnt == len(data):
            if gl.Ack[(gl.cnt-1)%gl.MaxSeq] == True:
                print("文件发送完毕")
                #s.close()
                #exit(0)
                break
            else:
                continue
        if gl.cnt > gl.wd_right:
            print("数据窗口已经满了")
            continue

        # 发送数据

        frame = Frame(gl.cnt % gl.MaxSeq,0, data[gl.cnt])
        send_data = frame.AddChecksum()
        # 如果发生丢包
        if k % gl.LostRate == 0 and k != 0:
            print("开始丢包: ",gl.cnt%gl.MaxSeq)

        # 如果发生错误
        else:
            if k % gl.ErrorRate == 0 and k != 0:
                print(str(gl.cnt) + "包出现错误")
                array = bytearray(send_data,encoding='utf-8')
                for i in range(10):
                    array[i] = random.randint(0,255)
                send_data = str(bytes(array))

            # print("wd_right:",gl.wd_right)
            # print("正在发送" + str(gl.cnt) + "包...")
            s.sendto(send_data.encode(), ("127.0.0.1", 30207))
            print("发送" + str(gl.cnt) + "包成功")

        gl.cnt += 1
        k += 1

        # 设置计时器
        time_t = threading.Thread(target=is_timeout, args=(gl.cnt%gl.MaxSeq,))
        time_t.start()
        # 接收Ack包
        Ack_t = threading.Thread(target=recv_process,args=(s,))
        Ack_t.start()


        # break



    # send_data(load_data("123",1,1))
    # load_data("123",1,2)