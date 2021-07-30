import cv2
import os

import numpy as np
def readimg(filename, mode):
	raw_data = np.fromfile(filename, dtype=np.uint8)  #先用numpy把图片文件存入内存：raw_data，把图片数据看做是纯字节数据
	img = cv2.imdecode(raw_data, mode)  #从内存数据读入图片
	return img
# read image



# define a threshold, 128 is the middle of black and white in grey scale
thresh = 254
fileList = os.listdir("pic/障碍物")
print(fileList)
i = 0
for file in fileList:
    filename = "pic/障碍物/"+file

    img_grey = readimg(filename, cv2.IMREAD_GRAYSCALE)
    # assign blue channel to zeros
    _,img_binary = cv2.threshold(img_grey, thresh, 255, cv2.THRESH_BINARY)

    # save image
    resname = "barrier" + str(i) + ".png"
    cv2.imwrite(resname,img_binary)
    i += 1
