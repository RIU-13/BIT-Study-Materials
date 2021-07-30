
.const ;常量区
  g_szClassName db  "CR26WndClass", 0
  g_szWndName   db  "DDQ", 0

  g_player0_up_1 db "people0_up.bmp", 0
  g_player0_left_1 db "people0_left.bmp", 0
  g_player0_right_1 db "people0_right.bmp", 0
  g_player0_down_1 db "people0_down.bmp", 0
  
  g_bomb db "bomb.bmp", 0
  g_yBrick db "barrier2.bmp", 0
  g_oBrick db "barrier1.bmp", 0
  g_cactus db "barrier0.bmp", 0
  g_black db "black.bmp", 0
  
.data  ;数据区
  g_szBuf   db 256 dup(0)
  g_hdc dd 0
  g_mdc dd 0
  g_bufdc dd 0

  g_nX dd 0
  g_nY dd 0
  g_nDirection_1 dd 0
  g_nDirection_2 dd 0
  g_map db 225 dup(0)
  g_man1_x db 0
  g_man1_y db 0
  g_man2_x db 0
  g_man2_y db 14
  temp db 0
  bomb1_x db 0
  bomb1_y db 0
  bomb2_x db 0
  bomb2_y db 0
  bomb1_time db 0
  bomb2_time db 0
  bomb1_flag db 0
  bomb2_flag db 0

  g_hYBrick dd 0
  g_hOBrick dd 0
  g_hCactus dd 0
  g_hBlack dd 0

  g_hBoom dd 0

  g_hSpriteUp_1 dd 0
  g_hSpriteDown_1 dd 0
  g_hSpriteLeft_1 dd 0
  g_hSpriteRight_1 dd 0

  g_tPre dd 0
  g_tNow dd 0
  g_nNum dd 0

WINDOW_WIDTH EQU 800
WINDOW_HEIGHT EQU 600