import fnmatch
import os
import numpy as np
import cv2


def ReadSaveAddr(rootDir,dir,Strb):

    imgPath = rootDir+dir  #原始图片位置
    imgStorePath = imgPath + '_BMP/'  # 存储路径

    a_list = fnmatch.filter(os.listdir(imgPath),Strb)

    if (not os.path.exists(imgStorePath)):
        os.makedirs(imgStorePath) #创建目录

        for i in range(len(a_list)):
            path = imgPath+'/'+a_list[i]
            # 开始读取
            img = cv2.imdecode(np.fromfile(path, dtype=np.uint8), -1)
			# 直接调用.tofile(_path),我这里显示权限拒绝，所用系统自带的文件写入
            img_encode = cv2.imencode('.bmp',img)[1]

            t = a_list[i]
            t = t[:-4] #拿到图片名

            with open(imgStorePath + t + '.bmp', 'wb') as f: #写入
                f.write(img_encode)

rootDir = 'picres/'
# 读取该目录下所有的文件夹(png图片)
dirlist = os.listdir(rootDir)
print(dirlist)
for dir in dirlist:
    ReadSaveAddr(rootDir,dir,"*.png")#传入根目录，以及根目录下某文件夹名