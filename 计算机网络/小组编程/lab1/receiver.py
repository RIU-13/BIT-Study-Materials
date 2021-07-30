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

UDPPort = 30207
Recv_nr_expected = 0
WFileName = "recv_file.txt"




def recv_data(s):
    Curacked = -1
    global Recv_nr_expected
    f = open(WFileName, 'a')

    r_data = s.recv(gl.DataSize).decode()  # 一次接收1024字节


    r_frame = Frame.ToFrame(r_data)
    print("接收到发送端的包：", r_frame.seq)
    # # 转化为十六进制
    # message = ""
    # message = str(r_data['seq']) + str(r_data['ack']) + r_data['data']
    # by = bytes(message, "utf-8")
    r_frame_checksum = r_frame.AddChecksum()
    if Recv_nr_expected == r_frame.seq:
        if r_data == r_frame_checksum:
            # 校验成功，发送Ack包
            print("校验成功，发送Ack"+str(Recv_nr_expected)+"包，写入文件")
            print(r_frame.data)
            Curacked = r_frame.seq
            f.write(r_frame.data)
            # # 并发送Ack
            s_frame = Frame(-1,r_frame.seq)
            s_data = s_frame.ToBytes()
            s.sendto(s_data.encode(),("127.0.0.1", 40207))
            print("发送Ack"+str(Recv_nr_expected)+"包成功")
            Recv_nr_expected = (Recv_nr_expected+1)%gl.MaxSeq
    else:
        print("接收到的包不是我想要的包，丢弃")
        print("重新发送确认包")
        s_frame = Frame(-1,Curacked)
    f.close()


if __name__ == '__main__':

    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    s.bind(("", UDPPort))  # 绑定服务器的ip和端口
    while True:
        recv_data(s)

    # load_data("123",1,2)