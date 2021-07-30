
.const ;常量区
 g_szClassName db  "CR26WndClass", 0
  g_szWndName   db  "DDQ", 0

  g_player0_up_1 db "people0_up.bmp", 0
  g_player0_left_1 db "people0_left.bmp", 0
  g_player0_right_1 db "people0_right.bmp", 0
  g_player0_down_1 db "people0_down.bmp", 0

  g_player1_up_1 db "people1_up.bmp", 0
  g_player1_left_1 db "people1_left.bmp", 0
  g_player1_right_1 db "people1_right.bmp", 0
  g_player1_down_1 db "people1_down.bmp", 0
  
  g_bombexplode db "bombexplode1.bmp", 0
  g_bomb db "boom.bmp", 0
  g_bomb2 db "boom2.bmp", 0
  g_yBrick db "barrier2.bmp", 0
  g_oBrick db "barrier1.bmp", 0
  g_cactus db "barrier0.bmp", 0
  g_black db "black.bmp", 0

  ;用来播放音乐的
  StarMidiName db "test.mid",0
  szMIDISeqr db "Sequencer",0
  


.data  ;数据区
  MidDeviceID   dd 0
  g_szBuf   db 256 dup(0)
  g_hdc dd 0
  g_mdc dd 0
  g_mdc1 dd 0
  g_bufdc dd 0
  g_bufdc1 dd 0
  g_nX dd 0
  g_nY dd 0
  g_nDirection_1 dd 0
  g_nDirection_2 dd 0
  
  g_man1_x db 0
  g_man1_y db 0
  g_man2_x db 0
  g_man2_y db 0
  temp db 0
  bomb1_x db 0
  bomb1_y db 0
  bomb2_x db 0
  bomb2_y db 0
  bomb1_time db 60
  bomb2_time db 60
  bombing1 db 0
  bombing2 db 0
  bomb1_flag db 0
  bomb2_flag db 0

  bomb_count1 db 5
  bomb_count2 db 5

  g_hYBrick dd 0
  g_hOBrick dd 0
  g_hCactus dd 0
  g_hBlack dd 0

  g_hBomb dd 0
  g_hBombExplode dd 0

  g_hSpriteUp_1 dd 0
  g_hSpriteDown_1 dd 0
  g_hSpriteLeft_1 dd 0
  g_hSpriteRight_1 dd 0

  g_hSpriteUp_2 dd 0
  g_hSpriteDown_2 dd 0
  g_hSpriteLeft_2 dd 0
  g_hSpriteRight_2 dd 0

  ErrorMsg byte "Error!",0ah,0
  BoxTitle byte "提示",0ah,0

  g_tPre dd 0
  g_tNow dd 0
  g_nNum dd 0

  g_map byte  2,2,2,2,2,2,2,2,2,2,2,2,2,2,2
	byte  2,3,2,0,2,0,0,2,0,0,2,0,2,3,2
	byte  2,3,2,0,2,2,0,2,0,2,2,0,2,3,2
	byte  2,0,0,0,2,0,0,1,0,0,2,0,0,0,2
	byte  2,0,0,0,0,1,3,3,3,1,0,0,0,0,2
	byte  2,0,1,0,0,0,3,3,3,0,0,0,1,0,2
	byte  2,0,1,1,0,1,0,1,0,1,0,1,1,0,2
	byte  2,0,1,1,0,2,2,3,2,2,0,1,1,0,2
	byte  2,0,0,0,0,1,0,0,0,1,0,0,0,0,2
	byte  2,0,3,0,2,1,0,2,0,1,2,0,3,0,2
	byte  2,1,1,1,1,1,1,1,1,1,1,1,1,1,2
	byte  2,0,0,1,1,1,1,1,1,1,1,1,0,0,2
	byte  2,0,3,2,3,2,3,2,3,2,3,2,3,0,2
	byte  2,3,3,3,2,3,3,3,3,3,2,3,3,3,2
	byte  2,2,2,2,2,2,2,2,2,2,2,2,2,2,2
szOutMsg byte "%d",0ah,0
lose_1 byte "red lose",0ah,0
lose_2 byte "bule lose",0ah,0
WINDOW_WIDTH EQU 765
WINDOW_HEIGHT EQU 795
