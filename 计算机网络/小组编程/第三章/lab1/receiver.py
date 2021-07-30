'''
    author: TangXiaojuan
    date: 2021-04-14
    content: Reliable file transfer using Go-Back-N
'''
# 1. 定义结构帧{data(1KB)、checksum(CRC-CCITT)、seq、ack}
# 2.
import gl
import socket
import CRC16CCITT
from Frame import *
import sys

UDPPort = 30207
Recv_nr_expected = 0

Cur_Acked = -1
STATUS = ['OK','DataError','NoError']
OK = 0
DATAERROR = 1
NOERROR = 2

k = 0

def recv_data(s,wf):
    global Cur_Acked

    global k,Recv_nr_expected
    k += 1
    temp = Recv_nr_expected

    r_data = s.recv(gl.DataSize)  # 一次接收4*1024字节
    r_frame = Frame.ToFrame(r_data[:-6])
    if CRC16CCITT.CheckCRC(r_data):
        if r_frame.seq == -2:
            print("文件接收完毕")
            s.close()
            wf.close()
            sys.exit(0)
        # 校验成功
        if r_frame.seq == Recv_nr_expected:
            # print("收到期待的包,发送确认包")
            status = OK
            Cur_Acked = r_frame.id
            wf.write(r_frame.data.decode('utf-8'))
            # 发送确认包
            s_frame = Frame(-1,r_frame.seq,id=r_frame.id)
            Recv_nr_expected = (Recv_nr_expected + 1) % gl.MaxSeq
        else:
            status = NOERROR
            s_frame = Frame(-1,Cur_Acked%gl.MaxSeq,id=Cur_Acked)
    else:
        status = DATAERROR
        # 再发一次当前的确认包
        s_frame = Frame(-1,Cur_Acked%gl.MaxSeq,id=Cur_Acked)

    s_data = s_frame.ToBytes()
    s.sendto(s_data, ("127.0.0.1", 40207))

    print("%d,pdu_exp=%d,pdu_recv=%d,status=%s,id=%d" % (k, temp, r_frame.seq, STATUS[status],r_frame.id))

if __name__ == '__main__':

    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    s.bind(("", UDPPort))  # 绑定服务器的ip和端口
    wf = open(gl.WFileName, 'w')
    while True:
        recv_data(s,wf)

    # load_data("123",1,2)