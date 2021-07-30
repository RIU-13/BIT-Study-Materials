'''
name: TangXiaojuan
content: generating random matrix
'''
import numpy as np

if __name__ == '__main__':
    SCALE = [10, 50, 100,500,1000]
    for t in SCALE:
        matrix = np.random.randint(-100, 100, [t, t])
        np.savetxt("matrix%d.txt"%t,matrix,"%d")
        # print(matrix)

