

# lab3. 计网实验（python）

#建议对照代码一起食用

#先运行createFile，生成文件

#运行sender.py

#运行receiver.py

## 一、文件脉络解释

1、frame.py：

①协议的格式。

```
    def __init__(self,seq=-1,ack = -1,data=b'ok',id=-1):
        self.seq = seq		#帧的序列号（相对于maxseq而言的）
        self.ack = ack		#帧的确认号（相对于maxseq而言的）
        self.data = data
        self.length = len(data)
        self.id = id          #全局id
```

②协议的打包成字节（pack函数可以自己查询用法）

```
    def ToBytes(self):
        # print("len_data:",len(self.data))
        message = pack(FORMAT,self.seq,self.ack,self.id,self.length,self.data)
        return message
```

③字节解包成协议的格式（unpack和pack联合起来用）

```
@classmethod（可以自行查询）
    def ToFrame(cls,message):
        seq,ack,id,length,data = unpack(FORMAT,message)
        data = data[:length]
        return cls(seq,ack,data,id)
```

2、creafile.py

​		生成特定大小的随机内容的文件（为了方便实验）

3、gl.py

​		全局变量，具体释义看代码。

4、CRC16CCITT.py

​		加CRC校验码（返回的是6位），同时判断校验码是否正确。

```
def AddCRC(message):
	//这是在网上扒的代码，可以直接用。
	//但是这里有个小小坑，因为这个代码刚开始返回的是不一定是6位，所以要判断一下，要补全六位。
   ......
 
```

5、 sender.py

​		包括了切割文件函数（cut_text)、recv_process、main

6、receiver.py

​		包括了recv_data、main函数

## 二、主要实验思路

### 2.1 sender

  1. cut_text ：将读取的文件content分成FILESIZE大小的数据块存到textArr，这里注意，切割用到了正则表达式（自己查询）、由于可能切割不是整数，那么需要将后面余下的数据也要添加到textArr中`textArr.append(text[(len(textArr)*lenth):])`。

  2. 创建套接字，绑定本地端口（40207），注意这里为了给接收文件定时，采取了settimeout的函数，表示接收数据如果超过一定的时间，会出错处理。

  3. 循环一直发送数据：

     ①判断是否传送完毕了。体现在判断gl.cnt == len(data)。传送完毕当然要给receiver一个通知（我已经传完啦，你可以休息啦），那么这个通知是什么呢？就是序列号为-2的一个帧，这里可以看看receiver.py那刚好有一个判断r_frame.seq == -2。

     ②接下来就是判断一下窗口的大小还有没有空闲也就是`if gl.cnt > gl.wd_right:`其实这里的cnt和wd_right都是相对于全局而言的，所以也就是可以直接判断啦

     ③接下来就可以打包啦，记得添上CRC校验码哦

     ④然后相当于网络层把数据扔给链路层了，那么链路层可以根据概率进行丢包、出错和正常处理啦。这里我用的技巧是给一个小的随机数，判断随机数的大小来进行相应的错误处理（丢包、出错和正常）

     - 出错处理，就是给随即下标对应的内容一个随即内容（超级随机了）
     - 丢包，那就不发了嘛
     - 正常，就好好发送呗

     *注意：接下来你可能会看到一个不大理解的代码：为什么要单独判断gl.wd_left呢，这是因为，如果sender我第一个包（也就是序号seq为0）一直没有接收到确认包，也就是我的接收端没有正确收到我发的第0(seq)个序号的包，那么观察一个receiver这时候会怎么样呢，看cur_acked=-1，这是代表receiver当前所能接收到的最大序号的包（注意这里的序号是指全局的哦），-1取模是等于好大好大的数的，所以这里特殊处理，单独判断给ack_no为-1，这里只是为了输出格式正确（强迫症orz，不改也没关系的~）*

     ⑤`gl.status[gl.cnt] = RT`这行代码主要是为了输出RT、TO、NEW的格式，因为已经传过一次啦，那么接下来你传肯定是重传嘛，不过你可能要问，万一是超时重传呢，别急，你会看到在recv_process里面对这个值进行重新赋值为TO（我的逻辑可能有点绕，可以慢慢读，如果不懂，可以来问我><）

4. 循环一直接收数据，这里开了个线程，为了同时进行。try...except就是为了如果出错也就是前文提到的         （settimeout)的效果。

   - 看try：收到确认包，不代表万事大吉。

     （出现两种情况：）

     **①确认号为-1**：也就是这里的一个小小细节，要慢慢读（我的逻辑太乱了）：因为接收端没有收到序号（seq)为0的包，根据代码，它不管有没有收到包，都会给sender发送当前已确认的最大序号（seq）的包，那么此时它发送的就是-1acked的包，acked=-1，那么frame_id=-1（注意看receiver的代码，frame_id和acked_id是什么关系)，那gl.Ack[r_frame.id] = True这个代码就会出错了嘛，所以我要直接修改gl.cnt=0（表示我要传送下一个包的序号（这里是指全局，gl.py已经有解释了）后返回。

     **②确认号为正常的：**那就开开心心接收，给Ack数组赋值true，这里是frame_id下标，因为是全局的序号嘛。然后wd_left和wd_right也要改变，下一个要发送的帧的序号（gl.cnt也要+1）

   - 看except：这里就直接代表我接收的相应序列号的包超时了，这里记得给status赋值TO（因为已经超时重传了）

### 2.2 receiver

​		接收端的代码这里不是很难，我简单说一下：

​		还是先创建套接字，绑定端口，然后循环接收数据：

​	    接收之后，解包，检查校验码对不对：

1. 正确：①判断是不是接收到发送完毕的“通知包”前文有提到的。

​			       ②看是不是自己期待接收序号的包，如果是的话，那么我就给status设置成OK，也要更改当前  cur_acked=r_frame_id    并且开心写文件，之后打包我的确认包（发送放在if else的外面了），期待的确认包也要+1了。如果不是，说明可能路上丢包了，那我还是要发送确认包（不过是当前已经收到的确认序号（cur_acked）最大的包。

2. 错误：那status设置成DATAERROR，然后和丢包情况一样，发送确认包（不过是当前已经收到的确认序号（cur_acked）最大的包。

## 三. 作者有话说

​		这个实验我大致捣鼓了3天左右，主要是一些细节，以及刚开始的逻辑问题，主要脉络清晰了，其他的小bug都可以慢慢解决。最后就是希望这份带着碎碎念的代码解释文档能够帮助到你，亲，给个好评呗~



​		注：如有错误或者解释不到位的地方或者有更好想法的，请联系1582713605@qq.com。