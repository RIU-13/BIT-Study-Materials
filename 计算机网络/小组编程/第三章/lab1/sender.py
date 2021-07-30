'''
    author: Tangxiaojuan
    date: 2021-04-16
    content: 发送端发送文件，模拟客户端
'''
import socket
import re
import time,threading
import CRC16CCITT
import threading
import gl
import random
from Frame import *
import sys
# coding=utf-8


UDPPort = 40207     # sender_port

STATUS = ['New','TO','RT']
NEW = 0
TO = 1
RT = 2

def cut_text(text,lenth):
    textArr = re.findall(r'[\s\S]{'+str(lenth)+'}', text)
    textArr.append(text[(len(textArr)*lenth):])
    return textArr

def recv_process(s):
    global status
    try:
        r_data = s.recv(gl.DataSize)
        r_frame = Frame.ToFrame(r_data)
        Ack_num = r_frame.ack
        if(Ack_num == -1):
            # 说明第一个包就出错
            gl.cnt = 0
            return
        # print("接收到确认包" + str(Ack_num))
        gl.Ack[r_frame.id] = True
        gl.wd_left = r_frame.id + 1
        gl.wd_right = gl.wd_left + gl.SWsize - 1
        # 接收端的包是错误的，需要重发
        gl.cnt = r_frame.id+1
        # 是重传，不是超时重传
    except:
        gl.status[frame.id] = TO
        # print("接收Ack" + str(frame.id) + "包超时")
        # if gl.cnt > frame.id:
        #     gl.cnt = frame.id



if __name__ == '__main__':
    f = open(gl.ReadFile, 'r',encoding='utf-8')
    content = f.read()
    f.close()

    data = cut_text(content, gl.SendSize)

    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    s.bind(("", UDPPort))
    s.settimeout(gl.Timeout)
    k = 0
    print("共有%d包传输,你准备好了吗？它将会花费你很长时间！"%(len(data)))
    while True:
        if gl.cnt == len(data):
            if gl.Ack[len(data)-1] == True:
                s_frame = Frame(-2,-1,b'over')
                s_data = s_frame.ToBytes()
                s_data += (CRC16CCITT.AddCRC(s_data))
                # print("s_data:",s_data)
                s.sendto(s_data,("127.0.0.1",30207))
                print("文件传送完毕")
                s.close()
                sys.exit(0)
            else:
                continue
        if gl.cnt > gl.wd_right:
            print("等待窗口腾出来")
            continue

        frame = Frame(gl.cnt % gl.MaxSeq, 0, bytes(data[gl.cnt],encoding='utf-8'),gl.cnt)
        send_data = frame.ToBytes()
        send_data += CRC16CCITT.AddCRC(send_data)
        # print("send_data:",send_data)
        randnum = round(random.uniform(0, 1), 1) * 10
        # randnum = 9
        # print(randnum)
        k += 1
        if  randnum > gl.ErrorRate:
            print("%d包出错"%(gl.cnt%gl.MaxSeq))
            # print(send_data)
            array = bytearray(send_data)
            for _ in range(3):
                i = random.randint(0,len(array)-1)
                array[i] = random.randint(0,255)
            send_data = bytes(array)
            # print("出错的包",send_data)
            s.sendto(send_data, ("127.0.0.1", 30207))
            # print("发送"+str(gl.cnt)+"包成功")
        elif  randnum > gl.LostRate:
            print("%d丢包"%(gl.cnt%gl.MaxSeq))

        else:
            s.sendto(send_data, ("127.0.0.1", 30207))
            # print("发送" + str(gl.cnt) + "包成功")


        if gl.wd_left == 0:
            ackedno = -1
        else:
            ackedno = (gl.wd_left - 1)%gl.MaxSeq
        print("%d,pdu_send=%d,status=%s,ackedNo=%d,id=%d" % (k, gl.cnt % gl.MaxSeq, STATUS[gl.status[gl.cnt]],ackedno,gl.cnt))

        gl.status[gl.cnt] = RT
        gl.cnt += 1

        # 接收Ack包
        Ack_t = threading.Thread(target=recv_process, args=(s,))
        Ack_t.start()
        # time.sleep(0.001)   #这里只是为了观察效果明显，不然你会发送，一个一个传和收，输出格式超级无敌乱

