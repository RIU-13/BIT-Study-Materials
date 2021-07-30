//#include "head.h"
#include<iostream>
#include<SDL.h>
#include<string>

using namespace std;

const int LIVE = 1;
const int MAXSPEED=4;
const int MAXBOMB=9;
const int MAXPOW=9;

class Object {
private:
	int x,y;
public:
	const int Getx();
	const int Gety();
	void Setx(int);
	void Sety(int);
	Object();
	Object( int , int );
};

class Buff : public Object {

};

class Obstacle : public Object {
public:
	Buff Die();			//�ϰ���ݻ٣�����buff��
};

class Bomb : public Object {
private:
	bool flag;			//�Ƿ���ڣ��Ƿ�ը���ˣ�
	int id;				//�����ĸ�ը����
	int pow;			//ը����Χ
	int time;			//ը������ʱ��
public:
	Bomb();
	Bomb(int,int,int);
	const bool isLive();
	const int GetPow();
	const int GetTime();
	const int GetId();
};

class Bomberman : public Object {
private:
	int id;				//ը���˱��
//	int sta;			//��ǰ״̬
	int Bombrest;		//ʣ��ը����
	int Bombsum;
	int pow;			//ը����Χ
	int speed;			//�ƶ��ٶ�
	int life;			//������
public:
	Bomberman();
	Bomberman(int,int,int);
	void Die();
	void GetBuff(int);
	const int GetSpeed();		//��ѯ�ٶ�
	const int Life();			//��ѯ�Ƿ�����
	const int GetBombrest();	//��ѯʣ��ը����
	
	const int GetPow();
	void SetBombrest(int);
};

//--------------------Object---------------------

const int Object::Getx(){return x;}

const int Object::Gety(){return y;}

void Object::Setx(int _x){x=_x;}

void Object::Sety(int _y){y=_y;}

Object::Object(){}

Object::Object( int _x , int _y ):x(_x),y(_y) {}

//--------------------Obstacle-------------------

//////////////
Buff Obstacle::Die(){
	Buff tmp;
	return tmp;
}

//--------------------Bomb-----------------------

Bomb::Bomb(){}

Bomb::Bomb(int _id,int _pow,int _time):flag(1),id(_id),pow(_pow),time(_time){}

const int Bomb::GetPow(){
	return pow;
}

const bool Bomb::isLive(){
	return flag;
}

const int Bomb::GetTime(){
	return time;
}

const int Bomb::GetId(){
	return id;
}

//-------------------Bomberman-------------------

Bomberman::Bomberman(){}

Bomberman::Bomberman(int _x,int _y,int _id):Object(_x,_y),id(_id),Bombsum(1),Bombrest(1),pow(1),speed(1),life(1){}

const int Bomberman::GetSpeed(){
	return speed;
}

void Bomberman::GetBuff(int type){
	if(type==0)if(speed<MAXSPEED)speed++;
	if(type==1)if(Bombsum<MAXBOMB){
		Bombsum++;
		Bombrest++;
	}
	if(type==2)if(pow<MAXPOW)pow++;
	if(type==3)if(life==1)life++;
}

const int Bomberman::Life(){
	return life;
}

const int Bomberman::GetPow(){
	return pow;
}

const int Bomberman::GetBombrest(){
	return Bombrest;
}

void Bomberman::SetBombrest(int _rest){
	Bombrest=_rest;
}

void Bomberman::Die(){
	life--;
	if(life<0)life=0;
}