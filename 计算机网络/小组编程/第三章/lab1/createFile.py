'''
    author: Tangxiaojuan
    date: 2021-04-16
    content: 生成随机内容文件
'''

import os,sys,string,random,gl




rand_str = string.ascii_letters

with open(gl.ReadFile,'w') as f:
    for i in range(gl.FILESIZE):
        f.write(random.choice(rand_str))