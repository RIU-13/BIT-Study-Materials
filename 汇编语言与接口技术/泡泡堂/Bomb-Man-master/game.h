#include<stdio.h>
#include"Object.h"
#include<time.h>
#include<queue>
#include<stdlib.h>
#include<fstream>
#include <SDL.h>
#include <SDL_image.h>
#include<vector>

extern int Width ;
extern int Height ;
const int Grid_width = 40;
const int Grid_height = 40;

const int INF=10000000;			//极大值
const int Grid_Len = 40;			//格子像素
const int Grid_N = 13;				//地图尺寸x
const int Grid_M = 15;				//地图尺寸y
const int Bomb_Time = 1500;			//炸弹爆炸时间   1500
const int Move_Stop = 0;			//移动状态
const int Move_Left = 1;
const int Move_Right = 2;
const int Move_Up = 3;
const int Move_Down = 4;
const int Move_Time = 50;			//移动基础时间		50
const int Decide_Time = 200;		//决策间隔时间	200
const int Line_Time = 400;			//爆炸路径持续时间	400
const int Defend_Time = 2000;		//被炸保护时间		2000
const int Decide_Escape = 60;		//处于危险决定逃跑	60
const int Decide_PutBomb = 5;		//决定放置炸弹		5
const int Decide_Move = 30;			//决定移动			30
const int Buff_Speed = 10;			//掉落加速Buff概率	10
const int Buff_Bombrest = 10;		//掉落加雷Buff概率	10
const int Buff_Power = 10;			//掉落威力Buff概率	10
const int Buff_Life = 5;			//掉落护盾Buff概率	5

const SDL_Rect rect_background = {0, 0, 100, 520};
const SDL_Rect rect_center = {340, 160, 80, 120};
const SDL_Rect rect_icon = {0, 0, 100, 60};
const SDL_Rect rect_start_or_pause = {0, 460, 100, 30};
const SDL_Rect rect_music = {0, 490, 100, 30};

extern SDL_Window *win;
extern SDL_Renderer *render;

SDL_Texture *texture_Background;
SDL_Texture *texture_Background2;
SDL_Texture *texture_Wall;
SDL_Texture *texture_Tree0;
SDL_Texture *texture_Tree1;
SDL_Texture *texture_Tree2;
SDL_Texture *texture_Bomb;
SDL_Texture *texture_Center;
SDL_Texture *texture_Win;
SDL_Texture *texture_Lose;
SDL_Texture *texture_Icon;
SDL_Texture *texture_Start;
SDL_Texture *texture_Pause;
SDL_Texture *texture_Music_on;
SDL_Texture *texture_Music_off;
SDL_Texture *texture_Buff[4];
SDL_Texture *texture_boom_path[9];
SDL_Texture *texture_bomberman[4][5];
SDL_Texture *texture_number[10];

Mix_Music *music_background;
Mix_Music *music_boom;

//移动方向向量
int Move_x[5]={0,-1,1,0,0};
int Move_y[5]={0,0,0,-1,1};

//地图信息
int Map[Grid_N][Grid_M];
//0表示空，2表示障碍物，1表示墙
//3表示炸弹，4表示人物

//危险格子信息
int DangerMap[Grid_N][Grid_M];
int DangerTmp[Grid_N][Grid_M];

//Buff信息
int BuffMap[Grid_N][Grid_M];
//0表示鞋子、1表示炸药数量、2表示威力、3表示护盾

//炸弹分布信息
Bomb BombMap[Grid_N][Grid_M];

//炸弹效果
int LineMap[Grid_N][Grid_M];
int LineTime[Grid_N][Grid_M];

//树的样式
int Tree[Grid_N][Grid_M];

//玩家
Bomberman Player[4];

//被炸保护时间
int Defend[4];

//用于暂停计时的变量
int DelayTime;
int PreTime;
bool IsPause;

//用于监控音效的变量
bool Music_on;

int Move[4];				//移动状态
int IsMoving[4];			//移动半格状态
int MoveTime[4];			//上次移动时间
int MoveStop[4];			//静止时的面相
int PutBomb[4];				//放置炸弹状态
int DecideTime;				//电脑决策时间

//用于BFS时保证不会重复搜索
int vis[Grid_N][Grid_M];

//用于AI寻找最近安全格子的node结构体
struct node{
	int x,y,move;
	node(){}
	node(int _x,int _y,int _mo = 0 ):x(_x),y(_y),move(_mo){}
};

//单人游戏
void Run1(string);

//多人游戏
void Run2(string);

//从文件中得到地图信息
void GetMap(string);

//搜索安全格子行走路线
int BFS(int,int);

//检查在某个格子放置炸弹是否会是自己无法逃脱
int CheckBomb(int,int,int);

//查看一个坐标是否出界
bool notOut(int,int);

//更新地图上炸弹信息
void UpdateMap(int);

//计算每个格子是否危险
void GetDanger();

//判定是否游戏结束
bool GetResult(int);

void Run_Init();

bool SDL_init();
void map_init();
void load_picture();
void load_music();
void stop_music();
void listen();
void destroy();
void Start1();
void Start2();

void Start1() {
	srand(time(NULL));
	//SDL_init();

	Run1("map.txt");

	destroy();
	return;
}

void Start2() {
	srand(time(NULL));
	//SDL_init();

	Run2("map.txt");

	destroy();
	return;
}

void destroy()
{
	SDL_DestroyTexture(texture_Background);
	SDL_DestroyTexture(texture_Wall);
	SDL_DestroyTexture(texture_Tree0);
	SDL_DestroyTexture(texture_Tree1);
	SDL_DestroyTexture(texture_Tree2);
	SDL_DestroyTexture(texture_Bomb);
	SDL_DestroyTexture(texture_Center);
	SDL_DestroyTexture(texture_Win);
	SDL_DestroyTexture(texture_Lose);
	for( int i = 0 ; i < 4 ; ++i )SDL_DestroyTexture(texture_Buff[i]);
	for( int i = 0 ; i < 9 ; ++i )SDL_DestroyTexture(texture_boom_path[i]);
	for( int i = 0 ; i < 4 ; ++i ){
		for( int j = 1 ; j <= 4 ; ++j ){
			SDL_DestroyTexture(texture_bomberman[i][j]);
		}
	}
	SDL_DestroyRenderer(render);
	SDL_DestroyWindow(win);
	SDL_Quit();
}

void load_picture()
{
	texture_Background = IMG_LoadTexture(render, "picture/background.png");
	texture_Background2 = IMG_LoadTexture(render, "picture/background2.png");
	texture_Wall = IMG_LoadTexture(render, "picture/wall.png");
	texture_Tree0 = IMG_LoadTexture(render, "picture/tree0.png");
	texture_Tree1 = IMG_LoadTexture(render, "picture/tree1.png");
	texture_Tree2 = IMG_LoadTexture(render, "picture/tree2.png");
	texture_Bomb = IMG_LoadTexture(render, "picture/bomb.png");
	texture_Center = IMG_LoadTexture(render, "picture/center.png");
	texture_Win = IMG_LoadTexture(render, "picture/win.png");
	texture_Lose = IMG_LoadTexture(render, "picture/lose.png");
	texture_Icon = IMG_LoadTexture(render, "picture/icon.png");
	texture_Start = IMG_LoadTexture(render, "picture/start.png");
	texture_Pause = IMG_LoadTexture(render, "picture/pause.png");
	texture_Music_on = IMG_LoadTexture(render, "picture/music_on.png");
	texture_Music_off = IMG_LoadTexture(render, "picture/music_off.png");
	texture_Buff[0] = IMG_LoadTexture(render, "picture/shoes.png");
	texture_Buff[1] = IMG_LoadTexture(render, "picture/bombnum.png");
	texture_Buff[2] = IMG_LoadTexture(render, "picture/power.png");
	texture_Buff[3] = IMG_LoadTexture(render, "picture/shield.png");
	texture_boom_path[0] = IMG_LoadTexture(render, "picture/boom_path0.png");
	texture_boom_path[1] = IMG_LoadTexture(render, "picture/boom_path1.png");
	texture_boom_path[2] = IMG_LoadTexture(render, "picture/boom_path1.png");
	texture_boom_path[3] = IMG_LoadTexture(render, "picture/boom_path3.png");
	texture_boom_path[4] = IMG_LoadTexture(render, "picture/boom_path3.png");
	texture_boom_path[5] = IMG_LoadTexture(render, "picture/boom_path5.png");
	texture_boom_path[6] = IMG_LoadTexture(render, "picture/boom_path6.png");
	texture_boom_path[7] = IMG_LoadTexture(render, "picture/boom_path7.png");
	texture_boom_path[8] = IMG_LoadTexture(render, "picture/boom_path8.png");
	texture_bomberman[0][1] = IMG_LoadTexture(render, "picture/001.png");
	texture_bomberman[0][2] = IMG_LoadTexture(render, "picture/002.png");
	texture_bomberman[0][3] = IMG_LoadTexture(render, "picture/003.png");
	texture_bomberman[0][4] = IMG_LoadTexture(render, "picture/004.png");
	texture_bomberman[1][1] = IMG_LoadTexture(render, "picture/101.png");
	texture_bomberman[1][2] = IMG_LoadTexture(render, "picture/102.png");
	texture_bomberman[1][3] = IMG_LoadTexture(render, "picture/103.png");
	texture_bomberman[1][4] = IMG_LoadTexture(render, "picture/104.png");
	texture_bomberman[2][1] = IMG_LoadTexture(render, "picture/201.png");
	texture_bomberman[2][2] = IMG_LoadTexture(render, "picture/202.png");
	texture_bomberman[2][3] = IMG_LoadTexture(render, "picture/203.png");
	texture_bomberman[2][4] = IMG_LoadTexture(render, "picture/204.png");
	texture_bomberman[3][1] = IMG_LoadTexture(render, "picture/301.png");
	texture_bomberman[3][2] = IMG_LoadTexture(render, "picture/302.png");
	texture_bomberman[3][3] = IMG_LoadTexture(render, "picture/303.png");
	texture_bomberman[3][4] = IMG_LoadTexture(render, "picture/304.png");
	texture_number[0] = IMG_LoadTexture(render, "picture/0.png");
	texture_number[1] = IMG_LoadTexture(render, "picture/1.png");
	texture_number[2] = IMG_LoadTexture(render, "picture/2.png");
	texture_number[3] = IMG_LoadTexture(render, "picture/3.png");
	texture_number[4] = IMG_LoadTexture(render, "picture/4.png");
	texture_number[5] = IMG_LoadTexture(render, "picture/5.png");
	texture_number[6] = IMG_LoadTexture(render, "picture/6.png");
	texture_number[7] = IMG_LoadTexture(render, "picture/7.png");
	texture_number[8] = IMG_LoadTexture(render, "picture/8.png");
	texture_number[9] = IMG_LoadTexture(render, "picture/9.png");
}

void load_music()
{
	
	music_background=Mix_LoadMUS("sound.mp3");  
	//music_boom=Mix_LoadMUS("boom.mp3"); 
    Mix_PlayMusic(music_background,-1);
	//Mix_PlayMusic(music_boom,1);
	//SDL_Delay(4000);
}

void stop_music(){
	Mix_HaltMusic();
}

void map_init()
{
	SDL_RenderClear(render);
	SDL_SetRenderDrawColor(render, 255, 255, 255, 255);
	SDL_RenderCopy(render, texture_Background, NULL, NULL);
	SDL_RenderCopy(render, texture_Background2, NULL, &rect_background);
	SDL_RenderCopy(render, texture_Icon, NULL, &rect_icon);

	SDL_Rect rect0 = { 0, 60, 60,  80};
	for(int i = 0; i < 4; i++ )
	{
		SDL_RenderCopy(render, texture_bomberman[i][4], NULL, &rect0);
		SDL_Rect rect1 = {rect0.x + 60 , rect0.y, 20,20};
		for(int j = 0; j < 4; j++)
		{
			if(j<3)SDL_RenderCopy(render, texture_Buff[j], NULL, &rect1);
			else if(Player[i].Life()==2)SDL_RenderCopy(render, texture_Buff[j], NULL, &rect1);
			rect1.y += 20;
		}

		SDL_Rect rect2  = {rect0.x + 80 , rect0.y, 20,20};
		for(int j = 0; j < 3; j++)
		{
			if(j==0)SDL_RenderCopy(render, texture_number[Player[i].GetSpeed()], NULL, &rect2);
			if(j==1)SDL_RenderCopy(render, texture_number[Player[i].GetBombrest()], NULL, &rect2);
			if(j==2)SDL_RenderCopy(render, texture_number[Player[i].GetPow()], NULL, &rect2);
			rect2.y += 20;
		}

		rect0.y += 100;
	}
	if( IsPause )SDL_RenderCopy(render, texture_Start , NULL, &rect_start_or_pause);
	else SDL_RenderCopy(render, texture_Pause, NULL, &rect_start_or_pause);
	if( Music_on )SDL_RenderCopy(render, texture_Music_on, NULL, &rect_music);
	else SDL_RenderCopy(render, texture_Music_off, NULL, &rect_music);


	SDL_Rect rect = { 100 , 0, Grid_width, Grid_height };
	for( int i = 0 ; i < Grid_N ; ++i ){
		rect.x = 100 ;
		for( int j = 0 ; j < Grid_M ; ++j ){
			if( LineMap[i][j] != -1 ){
			/*	if( LineMap[i][j] == 1 || LineMap[i][j] == 2 || LineMap[i][j] == 5 || LineMap[i][j] == 6 ){
					SDL_Rect rect1 = { rect.x , rect.y+5 , Grid_width, Grid_height - 10 };
					SDL_RenderCopy(render, texture_boom_path[ LineMap[i][j] ], NULL, &rect1);
				}
				else if( LineMap[i][j] == 3 || LineMap[i][j] == 4 || LineMap[i][j] == 7 || LineMap[i][j] == 8){
					SDL_Rect rect1 = { rect.x+5 , rect.y , Grid_width - 10, Grid_height };
					SDL_RenderCopy(render, texture_boom_path[ LineMap[i][j] ], NULL, &rect1);
				}
				else */
				SDL_RenderCopy(render, texture_boom_path[ LineMap[i][j] ], NULL, &rect);
			}
			if ( Map[i][j] == 1){
				if( i ==  Grid_N/2 && j >= Grid_M/2-1 && j<= Grid_M/2 )
				{
					rect.x += Grid_width;
					continue;
				}
				SDL_RenderCopy(render, texture_Wall, NULL, &rect);
			}
			else if (Map[i][j] == 2){
				if(Tree[i][j] == 0){
					SDL_RenderCopy(render, texture_Tree0, NULL, &rect);
				}
				else if(Tree[i][j] == 1){
					SDL_RenderCopy(render, texture_Tree1, NULL, &rect);	
				}
				else{
					SDL_RenderCopy(render, texture_Tree2, NULL, &rect);
				}
			}
			else if (Map[i][j] == 3){
				SDL_RenderCopy(render, texture_Bomb, NULL, &rect);
			}
			if (BuffMap[i][j] != -1){
				SDL_RenderCopy(render, texture_Buff[ BuffMap[i][j] ], NULL, &rect);
			}
			
			rect.x += Grid_width;
		}
		rect.y += Grid_height;
	}
	for( int i = 0 ; i < 4 ; ++i ){
		if( Player[i].Life() == 0 )continue;
		int nowx = Player[i].Getx();
		int nowy = Player[i].Gety();
		rect.x = nowx * Grid_width + 100;
		rect.y = nowy * Grid_height ;

		if( clock() - DelayTime - Defend[i] < Defend_Time && ( clock() - DelayTime - Defend[i] ) / 100 % 2 )continue;
		if( IsMoving[i] == Move_Stop ){
			if( Move[i] == Move_Stop ){
				SDL_Rect rect1 = { rect.x, rect.y-15, Grid_width, Grid_height+15 };
				SDL_RenderCopy(render, texture_bomberman[i][MoveStop[i]], NULL, &rect1);
			}
			else{
				SDL_Rect rect1 = { rect.x, rect.y-15, Grid_width, Grid_height+15 };
				SDL_RenderCopy(render, texture_bomberman[i][Move[i]], NULL, &rect1);
			}
		}
		else{
			rect.x += Grid_width * Move_x[IsMoving[i]] / 2 ;
			rect.y += Grid_height * Move_y[IsMoving[i]] / 2 ;
			SDL_Rect rect1 = { rect.x, rect.y-15, Grid_width, Grid_height+15 };
			SDL_RenderCopy(render, texture_bomberman[i][IsMoving[i]], NULL, &rect1);
		}
	}
	SDL_RenderCopy(render, texture_Center, NULL, &rect_center);
	for( int i = 0 ; i < 4 ; ++i ){
		if( Player[i].Life() == 0 )continue;
		int nowx = Player[i].Getx();
		int nowy = Player[i].Gety();
		rect.x = nowx * Grid_width + 100;
		rect.y = nowy * Grid_height ;
		if( nowx >= 5 && nowx <= 8 && nowy==7 ){
			if( clock() - DelayTime - Defend[i] < Defend_Time && ( clock() - DelayTime - Defend[i] ) / 100 % 2 )continue;
			if( IsMoving[i] == Move_Stop ){
				if( Move[i] == Move_Stop ){
					SDL_Rect rect1 = { rect.x, rect.y-15, Grid_width, Grid_height+15 };
					SDL_RenderCopy(render, texture_bomberman[i][MoveStop[i]], NULL, &rect1);
				}
				else{
					SDL_Rect rect1 = { rect.x, rect.y-15, Grid_width, Grid_height+15 };
					SDL_RenderCopy(render, texture_bomberman[i][Move[i]], NULL, &rect1);
				}
			}
			else{
				rect.x += Grid_width * Move_x[IsMoving[i]] / 2 ;
				rect.y += Grid_height * Move_y[IsMoving[i]] / 2 ;
				SDL_Rect rect1 = { rect.x, rect.y-15, Grid_width, Grid_height+15 };
				SDL_RenderCopy(render, texture_bomberman[i][IsMoving[i]], NULL, &rect1);
			}
		}
	}
	SDL_RenderPresent(render);
}

void GetMap(string Filename){
	srand(time(NULL));
	freopen(Filename.c_str(),"r",stdin);
	int id=0;
	for( int i = 0 ; i < Grid_N ; ++i ){
		for( int j = 0 ; j < Grid_M ; ++j ){
			scanf("%d",&Map[i][j]);
			if (Map[i][j] == 2){
				if( rand() % 113 < 75 ){
					Tree[i][j] = abs(rand()) % 3;
			//	printf("%d\n",Tree[i][j]);
				}
				else Map[i][j] = 0 ;
			}
			if(Map[i][j]==4){
				Player[id]=Bomberman(j,i,id);
				MoveStop[id] = Move_Down ;
				id++;
			}
		}
	}

	load_picture();
	map_init();
}

int BFS(int DangerMap[][Grid_M] , int nowx , int nowy ){
	queue<node>Q;
	memset(vis,0,sizeof(vis));

	//将周围可以走的格子加入队列
	for( int j = 1 ; j <= 4 ; ++j ){
		int dx = nowx + Move_x[j];
		int dy = nowy + Move_y[j];
		if( notOut( dx , dy ) ){
			if( LineMap[dy][dx] != -1 )continue;
			int dMap = Map[dy][dx] ;
			if( dMap == 0 || dMap > 3){
				Q.push( node( dx , dy , j ) );
				vis[dy][dx] = 1 ;
			}
		}
	}

	//寻找第一个安全的格子
	while(!Q.empty()){
		node u = Q.front();
		Q.pop();
		if( DangerMap[u.y][u.x]==0 ){
			return u.move;
		}
		for( int j = 1 ; j <= 4 ; ++j ){
			int dx = u.x + Move_x[j];
			int dy = u.y + Move_y[j];
			if( notOut( dx , dy ) && !vis[dy][dx] && LineMap[dy][dx] == -1 ){
				int dMap = Map[dy][dx] ;
				if( dMap == 0 || dMap > 3){
					Q.push( node( dx , dy , u.move ) );
					vis[dy][dx] = 1 ;
				}
			}
		}
	}

	return 0;
}

bool notOut( int nowx , int nowy ){
	return nowy >= 0 && nowy < Grid_N && nowx >= 0 && nowx < Grid_M;
}

int CheckBomb( int nowx , int nowy ,int power){
	memcpy( DangerTmp , DangerMap , sizeof( DangerTmp ));
	DangerTmp[nowy][nowx] ++;
	int tmpx,tmpy;
	
	for( int i = 1 ; i <= 4 ; ++i ){
		tmpx=nowx;
		tmpy=nowy;
		for( int j = 1 ; j <= power ; ++j ){
			tmpx += Move_x[i];
			tmpy += Move_y[i];

			if( !notOut( tmpx , tmpy ) )break;
			if( Map[tmpy][tmpx] == 1 ||
					Map[tmpy][tmpx] == 2 ){
				break;
			}
			DangerTmp[tmpy][tmpx] ++;
		}
	}


	return BFS( DangerTmp ,nowx , nowy );
}

void UpdateMap( int time ){
	queue < node > Q ;
	memset( vis , 0 , sizeof( vis ));
	for ( int i = 0 ; i < Grid_N ; ++i ){
		for ( int j = 0 ; j < Grid_M ; ++j ){
			if( Map[i][j] ==3 ){
				if( time - BombMap[i][j].GetTime() >= Bomb_Time ){
					Q.push(node( j , i ));
					vis[i][j] = 1;
				}
			}
		}
	}
	
	while(!Q.empty()){
		node u = Q.front();
		Q.pop();
		Map[u.y][u.x] = 0 ;
		int id = BombMap[u.y][u.x].GetId();
		Player[id].SetBombrest( Player[id].GetBombrest() + 1 );
		LineMap[u.y][u.x] = 0 ;
		LineTime[u.y][u.x] = time ;
		int Pow = BombMap[u.y][u.x].GetPow();
		int tmpx,tmpy,prex,prey;
		for( int i = 1 ; i <= 4 ; ++i ){
			prex = tmpx = u.x;
			prey = tmpy = u.y;
			for( int j = 1 ; j <= Pow + 1 ; ++j ){
				prex = tmpx ;
				prey = tmpy ;
				if( j == Pow + 1 )break;
				tmpx += Move_x[i] ;
				tmpy += Move_y[i] ;
				if( !notOut( tmpx , tmpy ) )break;
				if( Map[tmpy][tmpx] == 1 )break;
				
				LineMap[tmpy][tmpx] = i ;
				LineTime[tmpy][tmpx] = time ;
				
				if( Map[tmpy][tmpx] == 2 ){
					////////////////////////////物品掉落
					int rd = rand() % 100 ;
					Map[tmpy][tmpx] = 0 ;
					int percent = 0 ;
					if( rd < percent + Buff_Speed ) BuffMap[tmpy][tmpx] = 0;
					else{
						percent += Buff_Speed;
						if(rd < percent + Buff_Bombrest ) BuffMap[tmpy][tmpx] = 1;
						else{
							percent += Buff_Bombrest;
							if(rd < percent + Buff_Power ) BuffMap[tmpy][tmpx] = 2;
							else{
								percent += Buff_Power;
								if( rd < percent + Buff_Life ) BuffMap[tmpy][tmpx] = 3;
							}
						}
					}

					prex = tmpx ;
					prey = tmpy ;
					break;
				}
				if( Map[tmpy][tmpx] ==3){
					if( !vis[tmpy][tmpx] ){
						Q.push( node( tmpx , tmpy ));
						vis[tmpy][tmpx] = 1 ;
					}
				}
			}
			if( !( prex == u.x && prey == u.y ) ){
				LineMap[prey][prex] += 4 ;
			}
		}

	}
	for( int i = 0 ; i < 4 ; ++i ){
		if( clock() - DelayTime - Defend[i] <= Defend_Time )continue;
		int px = Player[i].Getx() ;
		int py = Player[i].Gety() ;
		if( clock() - DelayTime - Defend[i] > Defend_Time && LineMap[py][px] != -1 ){
			Player[i].Die();
			if( Player[i].Life() )Defend[i] = clock() - DelayTime;
		}
	}
}

void GetDanger(){
	memset( DangerMap , 0 , sizeof( DangerMap ));
	for( int i = 0 ; i < Grid_N ; ++i ){
		for( int j = 0 ; j < Grid_M ; ++j ){
			if( LineMap[i][j] != -1) DangerMap[i][j]++;
			if( Map[i][j] == 3 ){
				DangerMap[i][j]++;
				for( int k = 1 ; k <= 4 ; ++k ){
					int tmpx = j , tmpy = i ;
					for( int p = 1 ; p <= BombMap[i][j].GetPow() ; ++p ){
						tmpx += Move_x[k] ;
						tmpy += Move_y[k] ;
						if( !notOut( tmpx , tmpy ) ) break;
						if( Map[tmpy][tmpx] ==1 || 
							Map[tmpy][tmpx] ==2 ) break;
						DangerMap[tmpy][tmpx]++;
					}
				}
			}
		}
	}
}

bool GetResult(int Pnum){
	SDL_Rect rect = { 150, 100, 400 , 320 };
	bool win = 1 , lose = 1 ;
	for( int i = 0 ; i < Pnum ; ++i ){
		if( Player[i].Life() )lose = 0 ;
	}
	if(lose){
		SDL_RenderCopy(render, texture_Lose, NULL, &rect);
		SDL_RenderPresent(render);
		return 1;
	}
	else{
		for( int i = Pnum ; i < 4 ; ++i ){
			if( Player[i].Life() )win = 0;
		}
		if(win){
			SDL_RenderCopy(render, texture_Win, NULL, &rect);
			SDL_RenderPresent(render);
			return 1;
 		}
	}
	return 0;
}

void Run_Init(string Filename){

	//初始化炸弹效果、Buff信息
	memset( LineMap , -1 , sizeof( LineMap ));
	memset( BuffMap, -1 , sizeof( BuffMap ));

	//初始化移动、炸弹标记、决策间隔时间
	memset( Move , 0 , sizeof( Move ));
	memset( IsMoving , 0 , sizeof( IsMoving ));
	for( int i = 0 ; i < 4 ; ++i ) MoveTime[i] = -INF ;
	memset( PutBomb , 0 , sizeof( PutBomb ));
	DecideTime = -INF ;
	
	//初始化被炸保护时间
	for( int i = 0 ; i < 4 ; ++i ) Defend[i] = clock() - DelayTime ;

	DelayTime = 0 ;
	PreTime = -INF ;
	IsPause = false ;
	Music_on = true ;
	//得到地图信息

	GetMap(Filename);
	load_music();
}

void Run1(string Filename){

	Run_Init(Filename);
	
	int Pnum = 1 ;

	//窗口退出
	bool Over = 0 ;
	
	while(!Over){
		SDL_Event event;

		//事件监听
		//关闭游戏
		//P1移动、放置炸弹
		while(SDL_PollEvent( &event )){
			switch(event.type)
			{
				case SDL_QUIT:			//退出游戏
					Over = 1;
					break;
				case SDL_KEYUP:	
					if(event.key.keysym.sym==SDLK_ESCAPE)Over = 1;
					break;
				case SDL_MOUSEBUTTONUP:
					if(event.button.button == SDL_BUTTON_LEFT ){
						int mouse_x = event.button.x;
						int mouse_y = event.button.y;
						if( mouse_x > 0 && mouse_x < 100 && mouse_y > 460 &&mouse_y < 490 ){
							if( IsPause ){
								DelayTime += clock() - PreTime ;
								IsPause = false ;
								SDL_RenderCopy(render, texture_Pause , NULL, &rect_start_or_pause);
								SDL_RenderPresent(render);
							}
							else{
								PreTime = clock() ; 
								IsPause = true ;
								SDL_RenderCopy(render, texture_Start , NULL, &rect_start_or_pause);
								SDL_RenderPresent(render);
							}
						}
						if( mouse_x > 0 && mouse_x < 100 && mouse_y > 490 &&mouse_y < 520 ){
							if( Music_on ){
								stop_music();
								Music_on = false ;
								SDL_RenderCopy(render, texture_Music_off , NULL, &rect_music);
								SDL_RenderPresent(render);
							}
							else{
								load_music();
								Music_on = true ;
								SDL_RenderCopy(render, texture_Music_on , NULL, &rect_music);
								SDL_RenderPresent(render);
							}
						}
					}
				break;
				default:
					break;
			}
			if( IsPause ) continue ;
			switch(event.type)
			{
				//松开按键：玩家移动、放置炸弹
				
				case SDL_KEYUP:	
					switch(event.key.keysym.sym)
					{
						case SDLK_UP:
							if(Move[0] == Move_Up)
								Move[0] = Move_Stop ;
							break;
						case SDLK_DOWN:
							if(Move[0] == Move_Down)
								Move[0] = Move_Stop ;
							break;
						case SDLK_LEFT:
							if(Move[0] == Move_Left)
								Move[0] = Move_Stop ;
							break;
						case SDLK_RIGHT:
							if(Move[0] == Move_Right)
								Move[0] = Move_Stop ;
							break;
						case SDLK_SPACE:
							PutBomb[0] = 0;
							break;
						default:
							break;
					}
					break;
				//按下按键：玩家移动、放置炸弹
				case SDL_KEYDOWN:
					switch(event.key.keysym.sym){
						case SDLK_UP:
							Move[0] = Move_Up ;
							MoveStop[0] = Move_Up ;
							break;
						case SDLK_DOWN:
							Move[0] = Move_Down ;
							MoveStop[0] = Move_Down ;
							break;
						case SDLK_LEFT:
							Move[0] = Move_Left ;
							MoveStop[0] = Move_Left ;
							break;
						case SDLK_RIGHT:
							Move[0] = Move_Right ;
							MoveStop[0] = Move_Right ;
							break;
						case SDLK_SPACE:
							PutBomb[0] = 1 ;
							break;
						default:
							break;
					}
					break;
				default:
					break;
			}
		}
		if( IsPause ) continue ;


		int now = clock() - DelayTime ;		//当前时间
	//	printf("time : %d \n",now);

		for( int i = 0 ; i < Grid_N ; ++i ){
			for( int j = 0 ; j < Grid_M ; ++j ){
				if( LineMap[i][j] != -1 ){
					if( now - LineTime[i][j] > Line_Time ){
						LineMap[i][j] = -1 ;
					}
				}
			}
		}

		now = clock() - DelayTime ;
		//电脑决策移动、放置炸弹
		if( now - DecideTime >= Decide_Time ){
			GetDanger();
			for( int i = Pnum ; i < 4 ; ++i ){
				if( Player[i].Life() == 0 )continue;	//死亡的电脑

				int nowx = Player[i].Getx();
				int nowy = Player[i].Gety();
				int next_move = 0;

				//如果处在危险格子，优先逃跑（BFS）
				if( DangerMap[nowy][nowx] ){

					//控制主动逃跑的频率
					if( rand() % 100 < 100 - Decide_Escape )continue;

					next_move = BFS( DangerMap , nowx , nowy );

					//若无法走到安全格子，随机行走
					if( !next_move ) next_move = rand()%5 ;
					Move[i] = next_move;
					if( Move[i] ) MoveStop[i] = Move[i] ;
				}
				//处于安全位置
				else{
					//一定概率选择放炸弹、移动、不作为

					int type = rand() % 100 ;
	//				printf("rand() : %d \n",type);
					if( type < Decide_PutBomb ){		//放炸弹
						//确定是否可以放置炸弹
						if( PutBomb[i] == 0 && 
								Player[i].GetBombrest() ){
							int flag = CheckBomb( nowx , nowy , Player[i].GetPow() );
							if( flag ){
								PutBomb[i] = 1 ;
								Move[i] = MoveStop[i] = flag;
							}
						}
						
					}
					else if( type < Decide_PutBomb + Decide_Move ){	//移动
						//随机移动方向，但保证不移动到危险格子
						//随机方式：随机初始确定的方向，从该方向开始找到第一个安全的方向
						int t = rand() % 4 + 1 ;
						for( int j = 0 ; j < 4 ; ++j ){
							int tmp_move = t + j;
							if( tmp_move > 4 ) tmp_move -= 4;
							int dx = nowx + Move_x[ tmp_move ];
							int dy = nowy + Move_y[ tmp_move ];
							if( notOut( dx , dy ) ){
								if( DangerMap[dy][dx] == 0 && ( Map[dy][dx] == 0 || Map[dy][dx] > 3 )){
									next_move = tmp_move ;
									break;
								}
							}
						}
						Move[i] = next_move;
//						printf("movedecide   %d : %d\n",i,Move[i]);
					}
				}
			}
			DecideTime = now ;
		}

		now = clock() - DelayTime ;

		//炸弹人放置炸弹
		for( int i = 0 ; i < 4 ; ++i ){
			//炸弹人死亡
			if( Player[i].Life() == 0 )continue;
			//没有炸弹了
			if( Player[i].GetBombrest() == 0 )continue;


			if( PutBomb[i] ){
				int nowx = Player[i].Getx();
				int nowy = Player[i].Gety();
				if( Map[nowy][nowx] != 3){
					BombMap[nowy][nowx] = Bomb( i , Player[i].GetPow() , now );
					Player[i].SetBombrest( Player[i].GetBombrest() - 1 );
					Map[nowy][nowx] = 3 ;
				}
				if( i >= Pnum ) PutBomb[i]=0;
			}
		}

		now = clock() - DelayTime ;
		//炸弹人移动
		for( int i = 0 ; i < 4 ; ++i ){
			//炸弹人死亡
			if( Player[i].Life() == 0 )continue;
			if( now - MoveTime[i] >
					( Move_Time - Player[i].GetSpeed() * 5 ) ){
						
				//移动半格
				if( IsMoving[i] != Move_Stop ){
	//				printf("IsMoving: %d : %d\n",i,IsMoving[i]);
					Player[i].Setx( Player[i].Getx() + Move_x[ IsMoving[i] ] );
					Player[i].Sety( Player[i].Gety() + Move_y[ IsMoving[i] ] );
					int nowx=Player[i].Getx();
					int nowy=Player[i].Gety();
					if( now - Defend[i] > Defend_Time && LineMap[nowy][nowx] != -1 ){
						Player[i].Die();
						if( Player[i].Life() )Defend[i] = clock() - DelayTime;
					}
					if( Player[i].Life() ){
						if( BuffMap[nowy][nowx] != -1){
							Player[i].GetBuff( BuffMap[nowy][nowx] );
							BuffMap[nowy][nowx] = -1;
						}
					}
					MoveStop[i] = IsMoving[i] ;
					IsMoving[i] = Move_Stop ;
					
				}
				//准备移动
				else if( Move[i] != Move_Stop ){
//					printf("Move : %d : %d\n",i,Move[i]);
					int tmpx = Player[i].Getx() + Move_x[ Move[i]];
					int tmpy = Player[i].Gety() + Move_y[ Move[i]];
					if( notOut( tmpx , tmpy ) ){
						int tmpMap = Map[tmpy][tmpx] ;
						if( tmpMap == 0 || tmpMap > 3){
							IsMoving[i] = Move[i];
						}
					}
					MoveStop[i] = Move[i] ;
					if( i >= Pnum ) Move[i] = Move_Stop ;
				}
				MoveTime[i]=now;
			}
		}
		now = clock() - DelayTime ;
		//更新原有炸弹效果
		for( int i = 0 ; i < Grid_N ; ++i ){
			for( int j = 0 ; j < Grid_M ; ++j ){
				if( LineMap[i][j] != -1 ){
					if( now - LineTime[i][j] > Line_Time ){
						LineMap[i][j] = -1 ;
					}
				}
			}
		}
		
		//炸弹爆炸
		UpdateMap( now );

		map_init();

		Over |= GetResult( Pnum );
	}
	SDL_Delay(4000);
}

void Run2(string Filename){

	Run_Init(Filename);
	
	int Pnum = 2 ;

	//窗口退出
	bool Over = 0 ;
	
	while(!Over){
		SDL_Event event;

		//事件监听
		//关闭游戏
		//P1移动、放置炸弹
		while(SDL_PollEvent( &event )){
//			printf("aaaaa  %d\n",event.key.keysym.sym);
			switch(event.type)
			{
				case SDL_QUIT:			//退出游戏
					Over = 1;
					break;
				case SDL_KEYUP:	
					if(event.key.keysym.sym==SDLK_ESCAPE)Over = 1;
					break;
				case SDL_MOUSEBUTTONUP:
					if(event.button.button == SDL_BUTTON_LEFT ){
						int mouse_x = event.button.x;
						int mouse_y = event.button.y;
						if( mouse_x >= 0 && mouse_x <= 100 && mouse_y >= 460 &&mouse_y <= 490 ){
							if( IsPause ){
								DelayTime += clock() - PreTime ;
								IsPause = false ;
								SDL_RenderCopy(render, texture_Pause , NULL, &rect_start_or_pause);
								SDL_RenderPresent(render);
							}
							else{
								PreTime = clock() ; 
								IsPause = true ;
								SDL_RenderCopy(render, texture_Start , NULL, &rect_start_or_pause);
								SDL_RenderPresent(render);
							}
						}
						if( mouse_x > 0 && mouse_x < 100 && mouse_y > 490 &&mouse_y < 520 ){
							if( Music_on ){
								stop_music();
								Music_on = false ;
								SDL_RenderCopy(render, texture_Music_off , NULL, &rect_music);
								SDL_RenderPresent(render);
							}
							else{
								load_music();
								Music_on = true ;
								SDL_RenderCopy(render, texture_Music_on , NULL, &rect_music);
								SDL_RenderPresent(render);
							}
						}
					}
				break;
				default:
					break;
			}
			if( IsPause ) continue ;
			switch(event.type)
			{
				//松开按键：玩家移动、放置炸弹
				case SDL_KEYUP:	
					switch(event.key.keysym.sym)
					{
						case SDLK_w:
							if(Move[0] == Move_Up)
								Move[0] = Move_Stop ;
							break;
						case SDLK_s:
							if(Move[0] == Move_Down)
								Move[0] = Move_Stop ;
							break;
						case SDLK_a:
							if(Move[0] == Move_Left)
								Move[0] = Move_Stop ;
							break;
						case SDLK_d:
							if(Move[0] == Move_Right)
								Move[0] = Move_Stop ;
							break;
						case SDLK_SPACE:
							PutBomb[0] = 0;
							break;

						case SDLK_UP:
							if(Move[1] == Move_Up)
								Move[1] = Move_Stop ;
							break;
						case SDLK_DOWN:
							if(Move[1] == Move_Down)
								Move[1] = Move_Stop ;
							break;
						case SDLK_LEFT:
							if(Move[1] == Move_Left)
								Move[1] = Move_Stop ;
							break;
						case SDLK_RIGHT:
							if(Move[1] == Move_Right)
								Move[1] = Move_Stop ;
							break;
						
						case SDLK_RETURN:
							PutBomb[1] = 0;
							break;
						default:
							break;
					}
					break;
				//按下按键：玩家移动、放置炸弹
				case SDL_KEYDOWN:
					switch(event.key.keysym.sym){
						case SDLK_w:
							Move[0] = Move_Up ;
							MoveStop[0] = Move_Up ;
							break;
						case SDLK_s:
							Move[0] = Move_Down ;
							MoveStop[0] = Move_Down ;
							break;
						case SDLK_a:
							Move[0] = Move_Left ;
							MoveStop[0] = Move_Left ;
							break;
						case SDLK_d:
							Move[0] = Move_Right ;
							MoveStop[0] = Move_Right ;
							break;
						case SDLK_SPACE:
							PutBomb[0] = 1 ;
							break;

						case SDLK_UP:
							Move[1] = Move_Up ;
							MoveStop[1] = Move_Up ;
							break;
						case SDLK_DOWN:
							Move[1] = Move_Down ;
							MoveStop[1] = Move_Down ;
							break;
						case SDLK_LEFT:
							Move[1] = Move_Left ;
							MoveStop[1] = Move_Left ;
							break;
						case SDLK_RIGHT:
							Move[1] = Move_Right ;
							MoveStop[1] = Move_Right ;
							break;
						case SDLK_RETURN:
							PutBomb[1] = 1 ;
							break;
						default:
							break;
					}
					break;
				default:
					break;
			}
		}
		if( IsPause ) continue ;


		int now = clock() - DelayTime ;		//当前时间
	//	printf("time : %d \n",now);

		for( int i = 0 ; i < Grid_N ; ++i ){
			for( int j = 0 ; j < Grid_M ; ++j ){
				if( LineMap[i][j] != -1 ){
					if( now - LineTime[i][j] > Line_Time ){
						LineMap[i][j] = -1 ;
					}
				}
			}
		}
		
		now = clock() - DelayTime ;
		//电脑决策移动、放置炸弹
		if( now - DecideTime >= Decide_Time ){
			GetDanger();
			for( int i = Pnum ; i < 4 ; ++i ){
				if( Player[i].Life() == 0 )continue;	//死亡的电脑

				int nowx = Player[i].Getx();
				int nowy = Player[i].Gety();
				int next_move = 0;

				//如果处在危险格子，优先逃跑（BFS）
				if( DangerMap[nowy][nowx] ){

					//控制主动逃跑的频率
					if( rand() % 100 < 100 - Decide_Escape )continue;

					next_move = BFS( DangerMap , nowx , nowy );

					//若无法走到安全格子，随机行走
					if( !next_move ) next_move = rand()%5 ;
					Move[i] = next_move;
					if( Move[i] )MoveStop[i] = Move[i];
				}
				//处于安全位置
				else{
					//一定概率选择放炸弹、移动、不作为

					int type = rand() % 100 ;
	//				printf("rand() : %d \n",type);
					if( type < Decide_PutBomb ){		//放炸弹
						//确定是否可以放置炸弹
						if( PutBomb[i] == 0 && 
								Player[i].GetBombrest() ){
							int flag = CheckBomb( nowx , nowy , Player[i].GetPow() );
							if( flag ){
								PutBomb[i] = 1 ;
								Move[i] = MoveStop[i] = flag;
							}
						}
						
					}
					else if( type < Decide_PutBomb + Decide_Move ){	//移动
						//随机移动方向，但保证不移动到危险格子
						//随机方式：随机初始确定的方向，从该方向开始找到第一个安全的方向
						int t = rand() % 4 + 1 ;
						for( int j = 0 ; j < 4 ; ++j ){
							int tmp_move = t + j;
							if( tmp_move > 4 ) tmp_move -= 4;
							int dx = nowx + Move_x[ tmp_move ];
							int dy = nowy + Move_y[ tmp_move ];
							if( notOut( dx , dy ) ){
								if( DangerMap[dy][dx] == 0 && ( Map[dy][dx] == 0 || Map[dy][dx] > 3 )){
									next_move = tmp_move ;
									break;
								}
							}
						}
						Move[i] = next_move;
//						printf("movedecide   %d : %d\n",i,Move[i]);
					}
				}
			}
			DecideTime = now ;
		}

		now = clock() - DelayTime ;

		//炸弹人放置炸弹
		for( int i = 0 ; i < 4 ; ++i ){
			//炸弹人死亡
			if( Player[i].Life() == 0 )continue;
			//没有炸弹了
			if( Player[i].GetBombrest() == 0 )continue;


			if( PutBomb[i] ){
				int nowx = Player[i].Getx();
				int nowy = Player[i].Gety();
				if( Map[nowy][nowx] != 3){
					BombMap[nowy][nowx] = Bomb( i , Player[i].GetPow() , now );
					Player[i].SetBombrest( Player[i].GetBombrest() - 1 );
					Map[nowy][nowx] = 3 ;
				}
				if( i >= Pnum ) PutBomb[i]=0;
			}
		}

		now = clock() - DelayTime ;
		//炸弹人移动
		for( int i = 0 ; i < 4 ; ++i ){
			//炸弹人死亡
			if( Player[i].Life() == 0 )continue;
			if( now - MoveTime[i] >
					( Move_Time - Player[i].GetSpeed() * 5 ) ){
						
				//移动半格
				if( IsMoving[i] != Move_Stop ){
	//				printf("IsMoving: %d : %d\n",i,IsMoving[i]);
					Player[i].Setx( Player[i].Getx() + Move_x[ IsMoving[i] ] );
					Player[i].Sety( Player[i].Gety() + Move_y[ IsMoving[i] ] );
					int nowx=Player[i].Getx();
					int nowy=Player[i].Gety();
					if( now - Defend[i] > Defend_Time && LineMap[nowy][nowx] != -1 ){
						Player[i].Die();
						if( Player[i].Life() )Defend[i] = clock() - DelayTime;
					}
					if( Player[i].Life() ){
						if( BuffMap[nowy][nowx] != -1){
							Player[i].GetBuff( BuffMap[nowy][nowx] );
							BuffMap[nowy][nowx] = -1;
						}
					}
					MoveStop[i] = IsMoving[i] ;
					IsMoving[i] = Move_Stop ;
					
				}
				//准备移动
				else if( Move[i] != Move_Stop ){
//					printf("Move : %d : %d\n",i,Move[i]);
					int tmpx = Player[i].Getx() + Move_x[ Move[i]];
					int tmpy = Player[i].Gety() + Move_y[ Move[i]];
					if( notOut( tmpx , tmpy ) ){
						int tmpMap = Map[tmpy][tmpx] ;
						if( tmpMap == 0 || tmpMap > 3){
							IsMoving[i] = Move[i];
						}
					}
					MoveStop[i] = Move[i] ;
					if( i >= Pnum ) Move[i] = Move_Stop ;
				}
				MoveTime[i]=now;
			}
		}
		now = clock() - DelayTime ;
		//更新原有炸弹效果
		for( int i = 0 ; i < Grid_N ; ++i ){
			for( int j = 0 ; j < Grid_M ; ++j ){
				if( LineMap[i][j] != -1 ){
					if( now - LineTime[i][j] > Line_Time ){
						LineMap[i][j] = -1 ;
					}
				}
			}
		}
		
		//炸弹爆炸
		UpdateMap( now );

		map_init();

		Over |= GetResult( Pnum );
	}
	SDL_Delay(4000);
}

