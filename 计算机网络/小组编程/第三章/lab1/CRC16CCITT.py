'''
    author: Tangxiaojuan
    date: 2021-04-21
    content: CRC检验

'''


def AddCRC(message):
    message = message.hex()
    poly = 0x1021
    reg = 0x0000
    message += '\x00\x00'
    for byte in message:
        mask = 0x80
        while(mask > 0):
            reg<<=1
            if ord(byte) & mask:
                reg += 1
            mask>>=1
            if reg > 0xffff:
                reg &= 0xffff
                reg ^= poly
    ans = hex(reg).encode('utf-8')
    for i in range(len(ans),6):
        ans += b'0'
    return ans

def CheckCRC(message=b''):
    # print(type(message))
    crc = message[-6:]
    data = message[:-6]
    # print(crc)
    # print(data)
    mycrc = AddCRC(data)
    # print(mycrc)
    if mycrc == crc:
        return True
    else:
        return False

