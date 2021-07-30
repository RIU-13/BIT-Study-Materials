from struct import *
import CRC16CCITT
import gl
FORMAT = '=iiii%ds' % (gl.SendSize)
class Frame:
    def __init__(self,seq=-1,ack = -1,data=b'ok',id=-1):
        self.seq = seq
        self.ack = ack
        self.data = data
        self.length = len(data)
        self.id = id

    def ToBytes(self):
        # print("len_data:",len(self.data))

        message = pack(FORMAT,self.seq,self.ack,self.id,self.length,self.data)

        return message

    @classmethod
    def ToFrame(cls,message):

        seq,ack,id,length,data = unpack(FORMAT,message)
        data = data[:length]

        return cls(seq,ack,data,id)


#
# frame = Frame(0,0,b"gggggggg\n8hhhhh",10)
# message = frame.ToBytes()
# message = message + CRC16CCITT.AddCRC(message)
# message = b'\xff\xff\xff\xfe\xff\xff\xff\xff\xff\xff\xff\xff\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x000x732'
# print(message)
# Frame.ToFrame(message[:-5])

