import gl
import socket
import CRC16CCITT
from Frame import *

UDPPort = 30207
Recv_nr_expected = 0
WriteFile = "recv_file.txt"
STATUS = ['OK','DataError','NoError']
OK = 0
DATAERROR = 1
NOERROR = 2

if __name__ == '__main__':
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    s.bind(("", UDPPort))  # 绑定服务器的ip和端口

    k = 0
    while True:

        k += 1
        # 接收数据帧
        recv_bytes = s.recv(gl.DataSize)
        recv_frame = Frame.ToFrame(recv_bytes)
        temp = Recv_nr_expected
        if recv_frame.seq == Recv_nr_expected:
            check_bytes = recv_frame.AddChecksum()
            if check_bytes == recv_bytes:
                status = OK
                wf = open(WriteFile, 'a')
                wf.write(recv_frame.data)
                wf.close()

                Recv_nr_expected = (Recv_nr_expected + 1)%gl.MaxSeq
            else:
                status = DATAERROR
        else:
            status = NOERROR

        # print(recv_frame.data)
        print("%d,pdu_exp=%d,pdu_recv=%d,status=%s" % (k, temp,recv_frame.seq,  STATUS[status]))