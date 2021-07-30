
.const ;常量区
  g_szClassName db  "CR26WndClass", 0
  g_szWndName   db  "仙剑奇侠传", 0
  g_szGo1 db "go1.bmp", 0
  g_szGo2 db "go2.bmp", 0
  g_szGo3 db "go3.bmp", 0
  g_szGo4 db "go4.bmp", 0
  g_szBg db "bg.bmp", 0
  
.data  ;数据区
  g_szBuf   db 256 dup(0)
  g_hdc dd 0
  g_mdc dd 0
  g_bufdc dd 0
  g_hSpriteUp dd 0
  g_hSpriteDown dd 0
  g_hSpriteLeft dd 0
  g_hSpriteRight dd 0
  g_hBackGround dd 0
  g_tPre dd 0
  g_tNow dd 0
  g_nNum dd 0
  g_nX dd 0
  g_nY dd 0
  g_nDirection dd 0
  
WINDOW_WIDTH EQU 800
WINDOW_HEIGHT EQU 600