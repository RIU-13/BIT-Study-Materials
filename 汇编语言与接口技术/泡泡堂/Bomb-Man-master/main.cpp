#include <iostream>
#include <SDL.h>
#include <SDL_image.h>
#include <SDL_mixer.h>
#include <string>
#include "game.h"

using namespace std;

int Width = 600 + 100;
int Height = 520;

SDL_Window  *win = nullptr;
SDL_Renderer *render = nullptr;

bool init();
SDL_Texture* LoadImage(string file);

void StartMenu(int y_offset);
void GameStart();
void GameIntro();
void SelectMenu(int y_offset);
void StartMode(int y_offset);
void Select_Mode();

int main(int argc, char** argv)
{
	if (!init())
		return 1;
	
	SelectMenu(0);
	return 0;
}

bool init()
{//初始化
	if (SDL_Init(SDL_INIT_EVERYTHING) == -1) {
		cout << SDL_GetError() << endl;
		return false;
	}

	win = SDL_CreateWindow("BOMBER MAN", SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED, Width, Height, SDL_WINDOW_SHOWN);
	if (win == nullptr) {
		cout << SDL_GetError() << endl;
		return false;
	}

	render = SDL_CreateRenderer(win, -1, SDL_RENDERER_ACCELERATED | SDL_RENDERER_PRESENTVSYNC);
	if (render == nullptr) {
		cout << SDL_GetError() << endl;
		return false;
	}
	Mix_OpenAudio(44100,MIX_DEFAULT_FORMAT,2,2048);
	return true;
}

SDL_Texture* LoadImage(string file)
{//加载图片
	SDL_Texture* tex = nullptr;
	tex = IMG_LoadTexture(render, file.c_str());
	if (tex == nullptr)
		throw runtime_error("Failed to load image: " + file + IMG_GetError());
	return tex;
}

void StartMenu(int y_offset)
{
	SDL_Texture *start_background = nullptr, *point = nullptr;
	
	start_background = LoadImage("picture/start_menu.png");
	point = LoadImage("picture/tri_point.png");

	if (start_background == nullptr || point == nullptr) {
		cout << "开始界面图片无法加载" << endl;
		exit(0);
	}
	SDL_RenderClear(render);
	SDL_RenderCopy(render, start_background, NULL, NULL);
	SDL_Rect pos = {0, y_offset, Width, Height};
	SDL_RenderCopy(render, point, NULL, &pos);

	SDL_RenderPresent(render);

	
}

void GameIntro()
{
	SDL_Texture *gi_bg = nullptr;

	gi_bg = LoadImage("picture/game_intro.png");

	if (gi_bg == nullptr) {
		cout << "开始界面图片无法加载" << endl;
		exit(0);
	}

	SDL_RenderClear(render);
	SDL_RenderCopy(render, gi_bg, NULL, NULL);
	SDL_RenderPresent(render);

	SDL_Event e;
	bool quit = false;
	while (!quit) {
		while (SDL_PollEvent(&e))
		{
			switch(e.type)
			{
				case SDL_QUIT:			//退出游戏
					quit = true;
					break;
				case SDL_KEYDOWN:	
					switch (e.key.keysym.sym)
					{
						case SDLK_RETURN:
							quit = true;
							SelectMenu(45);
							break;
						default:
							break;
					}
			}
		}
	}
}

void SelectMenu(int y_offset)
{

	SDL_Event e;
	bool quit = false;

	StartMenu(y_offset);
	while (!quit) 
	{
		while (SDL_PollEvent(&e))
		{	
			switch(e.type)
			{
				case SDL_QUIT:			//退出游戏
					quit = true;
					break;
				case SDL_KEYDOWN:	
					switch (e.key.keysym.sym)
					{
					case SDLK_UP:
					case SDLK_DOWN:
						y_offset = y_offset == 0 ? 45 : 0;
						StartMenu(y_offset);
						break;
					case SDLK_RETURN:
						quit = true;
						if (y_offset == 0)
							Select_Mode();
						else
							GameIntro();
						break;
					default:
						break;
					}
			}
		}
	}
}

void StartMode(int y_offset)
{
	SDL_Texture *mode_background = nullptr, *point = nullptr;
	
	mode_background = LoadImage("picture/game_mode.png");
	point = LoadImage("picture/tri_point.png");

	if (mode_background == nullptr || point == nullptr) {
		cout << "开始界面图片无法加载" << endl;
		exit(0);
	}
	SDL_RenderClear(render);
	SDL_RenderCopy(render, mode_background, NULL, NULL);
	SDL_Rect pos = {0, y_offset, Width, Height};
	SDL_RenderCopy(render, point, NULL, &pos);

	SDL_RenderPresent(render);
}

void Select_Mode()
{
	SDL_Event e;
	bool quit = false;
	int y_offset = 0;

	StartMode(y_offset);
	while (!quit) {
		while (SDL_PollEvent(&e))
		{
			switch(e.type)
			{
				case SDL_QUIT:			//退出游戏
					quit = true;
				case SDL_KEYDOWN:	
					switch (e.key.keysym.sym)
					{
						case SDLK_UP:
						case SDLK_DOWN:
							y_offset = y_offset == 0 ? 45 : 0;
							StartMode(y_offset);
							break;
						case SDLK_RETURN:
							quit = true;
							if (y_offset == 0)
								Start1();
							else
								Start2();
							break;
						default:
							break;
					}
			}
		}
	}
}

