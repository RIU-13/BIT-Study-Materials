'''
    author: Tangxiaojuan
    date: 2021-04-16
    content: generate random file of fixed size

'''

import os,sys,string,random


sm = {'b':1,'k':1024,'m':1024*1024,'g':1024*1024*1024,'t':1024*1024*1024*1024}
FILENAME = 'sender.txt'
size = 3 * sm['m']
rand_str = string.ascii_letters

with open(FILENAME,'w') as f:
    for i in range(size):
        f.write(random.choice(rand_str))