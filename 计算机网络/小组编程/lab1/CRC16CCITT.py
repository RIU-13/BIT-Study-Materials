def AddCRC(message):
    message = message.hex()
    #CRC-16-CITT poly, the CRC sheme used by ymodem protocol
    poly = 0x1021
    #16bit operation register, initialized to zeros
    reg = 0x0000
    #pad the end of the message with the size of the poly
    message += '\x00\x00'
    #for each bit in the message
    for byte in message:
        mask = 0x80
        while(mask > 0):
            #left shift by one
            reg<<=1
            #input the next bit from the message into the right hand side of the op reg
            if ord(byte) & mask:
                reg += 1
            mask>>=1
            #if a one popped out the left of the reg, xor reg w/poly
            if reg > 0xffff:
                #eliminate any one that popped out the left
                reg &= 0xffff
                #xor with the poly, this is the remainder
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

