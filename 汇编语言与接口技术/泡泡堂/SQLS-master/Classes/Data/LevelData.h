/*****************************************************************************
*  Copyright (C) 2017 李坤 1061152718@qq.com
*
*  此文件属于软件学院2017c++大项目泡泡堂选题的项目文件.
*
*  此项目是开源项目, 在期末答辩之后, 我们可能会在假期里对一些因时间不够未完成的功能进
*  行补充, 以及适配windows平台, 在未来如果技术允许的情况下, 会酌情开发ios版本和anroid
*  版本, 期待您能够为这个开源项目提供宝贵意见, 帮助我们做得更好, 如果能够贡献宝贵的代
*  码那就更令人开心了.
*
*  本项目遵守MIT开源协议, 这也就是说, 您需要遵守的唯一条件就是在修改后的代码或者发行
*  包包含原作者的许可信息. 除非获得原作者的特殊许可, 在任何项目(包括商业项目)里使用
*  本项目的文件都需要包含作者的许可.
*
*  如果对项目有疑问或者其他建议, 欢迎联系13167211978@163.com, 1061152718@qq.com,
*  我们期待能和您互相交流合作, 学习更多的知识.
*
*  另外注意: 此项目需要您自行配置cocos环境,安装boost库, 如果遇到相关问题的话, 欢迎将
*  错误日志发给我们, 您的帮助将有助于改善游戏的体验.
*
*  @file     LevelData.h
*  @brief    存储开始游戏时需要传入游戏场景的数据
*  
*
*  @author   李坤
*  @email    1061152718@qq.com
*  @version  4.0.1.5(版本号)
*  @date     2017/05/13
*  @license  Massachusetts Institute of Technology License (MIT)
*
*----------------------------------------------------------------------------
*  Remark         : Description
*----------------------------------------------------------------------------
*  Change History :
*  <Date>     | <Version> | <Author>       | <Description>
*----------------------------------------------------------------------------
*  2017/05/13 | 3.0.0.1   | 李坤            | Create file
*----------------------------------------------------------------------------
*
*****************************************************************************/

#ifndef _LevelData_h_
#define _LevelData_h_

#include "cocos2d.h"
#include <iostream>
#include "ExternData.h"

USING_NS_CC;

/**
    * @brief 存储开始游戏的时候需要传入的数据 \n
    * 包括地图索引、背景图片名称、人物图片名称、房间内参与游戏的人物信息
*/

class LevelData : public Node
{
protected:

	int _mapIndex; 			/// 地图对应的索引

	std::string 	_bg; 	/// 地图缩略图名称
	std::string 	_data; 	/// 地图对应.data文件的名称
	std::string 	_role; 	/// 人物对应的图片名称

public:

    std::vector<PlayerData> player_list;	///房间内现存的玩家数据

	/** 
    * @brief 			单例对象创建函数
    * 
    * @param number    	地图索引
    * @param bg 		地图对应缩略图的名称
    * @param data		人物缩略图的名称
    * @param role 		人物角色名称
    *
    * @return 			指向LevelData对象的指针
	*/
	static LevelData* create(int number, std::string bg, std::string data, std::string role)
	{
		LevelData *sprite = new LevelData();
		if (sprite)
		{
			sprite->autorelease();
			sprite->setmapIndex(number);
			sprite->setBg(bg);
			sprite->setData(data);
			sprite->setRole(role);

			return sprite;
		}
		CC_SAFE_DELETE(sprite);
		return nullptr;
	}

	/** 
    * @brief 			设置地图对应的序号
    * @param number    	地图索引序号
	*/
	void setmapIndex(int number) { _mapIndex = number; }

	/** 
    * @brief            获得地图对应的序号
    * @return           地图索引序号
	*/
	int getmapIndex() { return _mapIndex; }

	/** 
    * @brief 			设置背景图片对应的名称
    * @param bg 	   	背景图片对应的名称
	*/
	void setBg(std::string bg) { _bg = bg; }

	/** 
    * @brief            获得背景图片对应的名称
    * @return           背景图片对应的名称
	*/
	std::string getBg() { return _bg; }
    
	/** 
    * @brief 			设置人物缩略图的名称
    * @param data    	背景图片对应的名称
	*/
	void setData(std::string data) { _data = data; }

	/** 
    * @brief            获得人物缩略图的名称
    * @return           背景图片对应的名称
	*/
	std::string getData() { return _data; }

	/** 
    * @brief 			设置人物角色名称
    * @param role   	人物角色名称
	*/
	void setRole(std::string role) { _role = role; }

	/** 
    * @brief            获得人物角色名称
    * @return           人物角色名称
	*/
	std::string getRole() { return _role; }

};

#endif /* _LevelData_h_ */
