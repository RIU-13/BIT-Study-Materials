## 第1章 引言 1

### 1.1 使用计算机网络 2

1.1.1 商业应用 2
1.1.2 家庭应用 4
1.1.3 移动用户 8
1.1.4 社会问题 10

### 1.2 网络硬件 13

1.2.1 个域网 14
1.2.2 局域网 15
1.2.3 城域网 18
1.2.4 广域网 18
1.2.5 互联网络 21

### 1.3 网络软件 22

1.3.1 协议层次结构 22
1.3.2 层次设计问题 26
1.3.3 面向连接与无连接服务 27
1.3.4 服务原语 29
1.3.5 服务与协议的关系 31

### 1.4 参考模型 32

1.4.1 OSI参考模型 32
1.4.2 TCP/IP参考模型 35
1.4.3 本书使用的模型 37
1.4.4 OSI参考模型与TCP/IP参考模型的比较 38
1.4.5 OSI模型和协议的评判 39
1.4.6 TCP/IP参考模型的评判 41

### 1.5 网络实例 42

1.5.1 因特网 42
1.5.2 第三代移动电话网络 50
1.5.3 无线局域网：802.11 54
1.5.4 RFID和传感器网络 57

### 1.6 网络标准化 59

1.6.1 电信领域有影响力的组织 60
1.6.2 国际标准领域有影响力的组织 61
1.6.3 Internet标准领域有影响力的组织 62
1.7 度量单位 64

## 第2章 物理层 70

### 2.1 数据通信的理论基础 70

2.1.1 傅里叶分析 70
2.1.2 带宽有限的信号 71
2.1.3 信道的最大数据速率 73

### 2.2 引导性传输介质 74

2.2.1 磁介质 74
2.2.2 双绞线 75
2.2.3 同轴电缆 76
2.2.4 电力线 77
2.2.5 光纤 77

### 2.3 无线传输 82

2.3.1 电磁频谱 82
2.3.2 无线电传输 85
2.3.3 微波传输 86
2.3.4 红外传输 89
2.3.5 光通信 89

### 2.4 通信卫星 90

2.4.1 地球同步卫星 91
2.4.2 中地球轨道卫星 94
2.4.3 低地球轨道卫星 94
2.4.4 卫星与光纤 96

### 2.5 数字调制与多路复用 97

2.5.1 基带传输 98
2.5.2 通带传输 101
2.5.3 频分复用 103
2.5.4 时分复用 105
2.5.5 码分复用 106

### 2.6 公共电话交换网络 108

2.6.1 电话系统结构 109
2.6.2 电话政治化 111
2.6.3 本地回路：调制解调器、ADSL和光纤 112
2.6.4 中继线和多路复用 119
2.6.5 交换 125

## 第3章 数据链路层 151

### 3.1 数据链路层的设计问题 151

3.1.1 提供给网络层的服务 152
3.1.2 成帧 153
3.1.3 差错控制 156
3.1.4 流量控制 157

### 3.2 差错检测和纠正 158

3.2.1 纠错码 159
3.2.2 检错码 163

### 3.3 基本数据链路层协议 167

3.3.1 一个乌托邦式的单工协议 171
3.3.2 无错信道上的单工停-等式协议 172
3.3.3 有错信道上的单工停-等式协议 173

### 3.4 滑动窗口协议 176

3.4.1 1位滑动窗口协议 178
3.4.2 回退N协议 180
3.4.3 选择重传协议 185

### 3.5 数据链路协议实例 189

3.5.1 SONET上的数据包 189
3.5.2 对称数字用户线 192

## 第4章 介质访问控制子层 199

### 4.1 信道分配问题 199

4.1.1 静态信道分配 199
4.1.2 动态信道分配的假设 201

### 4.2 多路访问协议 202

4.2.1 ALOHA 202
4.2.2 载波侦听多路访问协议 206
4.2.3 无冲突协议 208
4.2.4 有限竞争协议 211
4.2.5 无线局域网协议 214

### 4.3 以太网 216

4.3.1 经典以太网物理层 217
4.3.2 经典以太网的MAC子层协议 218
4.3.3 以太网性能 221
4.3.4 交换式以太网 222
4.3.5 快速以太网 224
4.3.6 千兆以太网 226
4.3.7 万兆以太网 229
4.3.8 以太网回顾 230

### 4.4 无线局域网 231

4.4.1 802.11体系结构和协议栈 231
4.4.2 802.11物理层 232
4.4.3 802.11 MAC子层协议 234
4.4.4 802.11帧结构 239
4.4.5 服务 240

### 4.8 数据链路层交换 256

4.8.1 网桥的使用 257
4.8.2 学习网桥 258
4.8.3 生成树网桥 260
4.8.4 中继器/集线器/网桥/交换机/路由器和网关 263
4.8.5 虚拟局域网 265

## 第5章 网络层 274

### 5.1 网络层的设计问题 274

5.1.1 存储转发数据包交换 274
5.1.2 提供给传输层的服务 275
5.1.3 无连接服务的实现 276
5.1.4 面向连接服务的实现 277
5.1.5 虚电路与数据报网络的比较 278

### 5.2 路由算法 279

5.2.1 优化原则 281
5.2.2 最短路径算法 281
5.2.3 泛洪算法 283
5.2.4 距离矢量算法 285
5.2.5 链路状态路由 288
5.2.6 层次路由 292

### 5.3 拥塞控制算法 302

5.3.1 拥塞控制的途径 304
5.3.2 流量感知路由 305
5.3.3 准入控制 306
5.3.4 流量调节 307
5.3.5 负载脱落 310

### 5.5 网络互联 326

5.5.1 网络如何不同 327
5.5.2 何以连接网络 328
5.5.3 隧道 330
5.5.4 互联网路由 331
5.5.5 数据包分段 332

### 5.6 Internet的网络层 335

5.6.1 IPv4协议 337
5.6.2 IP地址 340
5.6.3 IPv6协议 350
5.6.4 Internet控制协议 357
5.6.5 标签交换和MPLS 362
5.6.6 OSPF——内部网关路由协议 364
5.6.7 BGP——外部网关路由协议 368

## 第6章 传输层 382

### 6.1 传输服务 382

6.1.1 提供给上层的服务 382
6.1.2 传输服务原语 383
6.1.3 Berkeley套接字 386
6.1.4 套接字编程实例：Internet文件服务器 388

### 6.2 传输协议的要素 392

6.2.1 寻址 393
6.2.2 连接建立 395
6.2.3 连接释放 400

### 6.4 Internet传输协议：UDP 417

6.4.1 UDP概述 417
6.4.2 远程过程调用 419
6.4.3 实时传输协议 421

### 6.5 Internet传输协议：TCP 425

6.5.1 TCP概述 425
6.5.2 TCP服务模型 426
6.5.3 TCP协议 428
6.5.4 TCP段的头 429
6.5.5 TCP连接建立 432
6.5.6 TCP连接释放 433
6.5.7 TCP连接管理模型 434
6.5.8 TCP滑动窗口 435
6.5.9 TCP计时器管理 438
6.5.10 TCP拥塞控制 440

## 第7章 应用层 471

### 7.1 DNS——域名系统 471

7.1.1 DNS名字空间 472
7.1.2 域名资源记录 474
7.1.3 名字服务器 477

### 7.2 电子邮件 480

7.2.1 体系结构和服务 481
7.2.2 用户代理 482
7.2.3 邮件格式 486
7.2.4 邮件传送 492
7.2.5 最后传递 497

### 7.3 万维网 499

7.3.1 体系结构概述 500
7.3.2 静态Web页面 512
7.3.3 动态Web页面和Web应用 519
7.3.4 HTTP——超文本传输协议 529