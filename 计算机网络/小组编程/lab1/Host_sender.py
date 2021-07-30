import socket
import re
import time,threading
import CRC16CCITT
import threading
import gl
import random
from Frame import *

UDPPort = 40207
ReadFile = 'hello.txt'
STATUS = ['New','TO','RT']
NEW = 0
TO = 1
RT = 2
status = 0
def cut_text(text,length):
    textArr = re.findall(r'[\s\S]{'+str(length)+'}', text)
    textArr.append(text[(len(textArr)*length):])
    return textArr

def is_timeout(n):
    global status
    # print(str(n)+"包开始计时")
    time.sleep(gl.Timeout)
    # 未接收到
    if gl.Ack[n] == False:
        status = TO #超时重传
        # print("接收Ack"+str(n)+"包超时")
        if gl.cnt > n :
            gl.cnt = n
        print("开始重传: ",gl.cnt)

if __name__ == '__main__':
    rf = open(ReadFile,'r')
    content = rf.read()
    rf.close()

    # 按发送大小切割文件
    data = cut_text(content,gl.SendSize)
    # print(data)
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    s.bind(("", UDPPort))
    s.settimeout(gl.Timeout)
    k = 0
    ackedNo = -1
    while True:
        if gl.cnt == len(data):
            break
        if gl.cnt > gl.wd_right:
            continue
        # 构造帧
        send_frame = Frame(gl.cnt%gl.MaxSeq,0,data[gl.cnt])
        # 添加checksum
        send_bytes = send_frame.AddChecksum()

        # 发送
        k += 1
        # 丢包
        if k % gl.LostRate == 0 :
            print("第%d包丢失"%(send_frame.seq))
        else:
            # 出错
            if k % gl.ErrorRate == 0:
                print("第%d包出错%s"%(send_frame.seq,send_bytes))
                array = bytearray(send_bytes)
                for i in range(10):
                    array[i] = random.randint(0,255)
                send_data = bytes(array).decode('utf-8')
                print(send_data)
        s.sendto(send_bytes,("127.0.0.1",30207))
        gl.Ack[gl.cnt%gl.MaxSeq] = False
        print("%d,pdu_send=%d,status=%s,ackedNo=%d"%(k,gl.cnt%gl.MaxSeq,STATUS[status],ackedNo))



        # 接收


        gl.cnt += 1