.386 
.model FLAT,stdcall 
option casemap:none  
;__UNICODE__ equ


include windows.inc
include user32.inc
include kernel32.inc
include gdi32.inc

include xj.inc

includelib user32.lib
includelib kernel32.lib
includelib gdi32.lib

GameInit PROTO hwnd:HWND
GamePaint PROTO hwnd:HWND

.code ;代码区  r/e  api
WinMain proc
  LOCAL  @wc: WNDCLASS
  LOCAL  @hInstance : HINSTANCE
  LOCAL  @hWnd:HWND 
  local  @msg:MSG

  ;获取模块基址
  invoke GetModuleHandle, NULL
  mov @hInstance, eax
  .if eax == 0
    mov eax, 0
    ret
  .endif 

  mov @wc.style, CS_VREDRAW or CS_HREDRAW; ;默认,垂直和水平拉伸窗口,窗口内容重新布局和绘制
  mov @wc.lpfnWndProc, CR26WindowProc;设置窗体接收windws消息函数
  mov @wc.cbClsExtra, 0;  //额外内存大小
  mov @wc.cbWndExtra, 0; 
  mov eax, @hInstance
  mov @wc.hInstance, eax;//实例句柄
  mov @wc.hIcon, NULL; //图标
  mov @wc.hCursor, NULL;//光标
  mov @wc.hbrBackground, COLOR_ACTIVEBORDER;//背景画刷
  mov @wc.lpszMenuName, NULL; //菜单名
  mov @wc.lpszClassName, offset g_szClassName;//窗口类名
  invoke RegisterClass, addr @wc
  .if eax == 0
    mov eax, 0
    ret
  .endif

  invoke CreateWindowEx, 0, offset g_szClassName,  offset g_szWndName,
    WS_OVERLAPPEDWINDOW,
    CW_USEDEFAULT, 
    CW_USEDEFAULT,
    WINDOW_WIDTH,
    WINDOW_HEIGHT,
    NULL, 
    NULL, 
    @hInstance, 
    NULL
  .if eax == 0
    invoke GetLastError
    mov eax, 0
    ret
  .endif
  mov @hWnd, eax

  invoke ShowWindow, @hWnd, SW_SHOW;
  invoke UpdateWindow, @hWnd;
  
  invoke GameInit, @hWnd

  ;消息循环
  .while  TRUE
  mov eax, 0
   ;invoke PeekMessage, addr @msg, NULL, 0, 0, PM_REMOVE
   .if  eax == 1
	 invoke TranslateMessage, addr @msg 
     invoke DispatchMessage, addr @msg 
   .else
     invoke GetTickCount
	 sub eax, g_tPre
	 .if eax >= 30
;       invoke Bomb
	   invoke GamePaint, @hWnd;刷新
	 .endif
   .endif

    
  .endw

  mov eax, TRUE
  ret
WinMain endp

Calculate proc C  y:byte,x:byte
xor eax,eax
mov al,y
mov ebx, 15
mul ebx
movzx ebx, x
add eax,ebx
ret
Calculate endp

Bomb proc 
    .if bomb1_flag==1
    sub bomb1_time,1
    .endif
    .if bomb1_time==0

    mov bomb1_flag,0
    mov bomb1_time,100
    .endif
    .if bomb2_flag==1
    sub bomb2_time,1
    .endif
    .if bomb2_time==0
    mov bomb2_flag,0
    mov bomb2_time,100
    .endif

Bomb endp

CR26WindowProc proc hwnd:HWND,  uMsg:UINT, wParam:WPARAM, lParam:LPARAM;//消息的处理程序


  .if uMsg == WM_KEYDOWN
    ;invoke MessageBox, hwnd, offset g_szKeyDown, offset g_szTitle, MB_OK
	INVOKE GetKeyState, 020h;W
   ; test al, 1
        .IF ax>1 && bomb1_time ==100
        invoke Calculate ,g_man1_y,g_man1_x
        mov g_map[eax],101

        mov al,g_man1_x
        mov bomb1_x,al

        mov al,g_man1_y
        mov bomb1_y,al

        mov bomb1_flag,1
        .endif
    
     xor eax,eax
     INVOKE GetKeyState, VK_SHIFT;W
   ; test al, 1
    .IF ax>1 && bomb2_time ==100
        invoke Calculate ,g_man2_y,g_man2_x
        mov g_map[eax],102

        mov al,g_man2_x
        mov bomb2_x,al

        mov al,g_man2_y
        mov bomb2_y,al

        mov bomb2_flag,1
    .endif

    xor eax,eax

    INVOKE GetKeyState, 057h;W
   ; test al, 1
   ;bl = bl
    .IF ax>1
      mov g_nDirection_1, 0
      mov bl,g_man1_y
	  ;sub g_man1_y, 1
	  .if g_man1_y<1;边缘检测
        mov g_man1_y,0
      .else
        sub g_man1_y,1
	  .endif
      invoke Calculate ,g_man1_y,g_man1_x
       .if g_map[eax]<4 && g_map[eax]>0;碰撞检测
         mov g_man1_y,bl
       .elseif g_map[eax]==10
         mov g_man1_y,bl
       .elseif g_map[eax]==102
         mov g_man1_y,bl
       .elseif g_map[eax]==9
         mov g_man1_y,bl
       .endif

       invoke Calculate ,bl,g_man1_x
         .if bl!=g_man1_y && g_map[eax]==101;走出
            mov g_map[eax],10
         .else
                    mov g_map[eax],0
                    invoke Calculate ,g_man1_y,g_man1_x
                 .if g_map[eax]==101
                    mov g_map[eax],101
                .else 
                    mov g_map[eax],8
                .endif
        .endif
    .ENDIF

    xor eax,eax
    INVOKE GetKeyState, 053h;S
   ; test al, 1
    .IF ax>1
      
      mov g_nDirection_1, 1
      invoke Calculate ,g_man1_y,g_man1_x
      mov g_map[eax],0
      mov bl,g_man1_y
	  ;sub g_man1_y, 1
	  .if g_man1_y>13
        mov g_man1_y,14
      .else
        add g_man1_y,1
	  .endif

      invoke Calculate ,g_man1_y,g_man1_x
       .if g_map[eax]<4 && g_map[eax]>0;碰撞检测
         mov g_man1_y,bl
       .elseif g_map[eax]==10
         mov g_man1_y,bl
       .elseif g_map[eax]==102
         mov g_man1_y,bl
       .elseif g_map[eax]==9
         mov g_man1_y,bl
       .endif

       invoke Calculate ,bl,g_man1_x
         .if bl!=g_man1_y && g_map[eax]==101;走出
            mov g_map[eax],10
         .else
                    mov g_map[eax],0
                    invoke Calculate ,g_man1_y,g_man1_x
                 .if g_map[eax]==101
                    mov g_map[eax],101
                .else 
                    mov g_map[eax],8
                .endif
        .endif

    .ENDIF
    xor ax,ax
    INVOKE GetKeyState, 041h;A
   ; test al, 1
    .IF ax>1
      mov g_nDirection_1, 2
      invoke Calculate ,g_man1_y,g_man1_x
      mov g_map[eax],0
            mov bl,g_man1_x
	  ;sub g_man1_y, 1
	  .if g_man1_x<1
        mov g_man1_x,0
      .else
        sub g_man1_x,1
	  .endif

        invoke Calculate ,g_man1_y,g_man1_x    
       .if g_map[eax]<4 && g_map[eax]>0;碰撞检测
         mov g_man1_x,bl
       .elseif g_map[eax]==10
         mov g_man1_x,bl
       .elseif g_map[eax]==102
         mov g_man1_x,bl
       .elseif g_map[eax]==9
         mov g_man1_x,bl
       .endif

        invoke Calculate ,g_man1_y,bl
         .if bl!=g_man1_x && g_map[eax]==101;走出
            mov g_map[eax],10
         .else
                    mov g_map[eax],0
                    invoke Calculate ,g_man1_y,g_man1_x  
                 .if g_map[eax]==101
                    mov g_map[eax],101
                .else 
                    mov g_map[eax],8
                .endif
        .endif

    .ENDIF
    xor ax,ax
    INVOKE GetKeyState, 044h;D
   ; test al, 1
    .IF ax>1
      mov g_nDirection_1, 3
      invoke Calculate ,g_man1_y,g_man1_x  
      mov g_map[eax],0
            mov bl,g_man1_x
	  ;sub g_man1_y, 1
	  .if g_man1_x>13
        mov g_man1_x,14
      .else
        add g_man1_x,1
	  .endif
        
        invoke Calculate ,g_man1_y,g_man1_x  
       .if g_map[eax]<4 && g_map[eax]>0;碰撞检测
         mov g_man1_x,bl
       .elseif g_map[eax]==10
         mov g_man1_x,bl
       .elseif g_map[eax]==102
         mov g_man1_x,bl
       .elseif g_map[eax]==9
         mov g_man1_x,bl
       .endif

         invoke Calculate ,g_man1_y,bl 
         .if bl!=g_man1_x && g_map[eax]==101;走出
            mov g_map[eax],10
         .else
                    mov g_map[eax],0
                    invoke Calculate ,g_man1_y,g_man1_x  
                 .if g_map[eax]==101
                    mov g_map[eax],101
                .else 
                    mov g_map[eax],8
                .endif
        .endif

    .ENDIF
    xor ax,ax

    INVOKE GetKeyState, VK_UP;shang
   ; test al, 1
    .IF ax>1
      mov g_nDirection_2, 0
      invoke Calculate ,g_man2_y,g_man2_x  
      mov g_map[eax],0
            mov bl,g_man2_y
	  ;sub g_man1_y, 1
	  .if g_man2_y<1
        mov g_man2_y,0
        
      .else
        sub g_man2_y,1
	  .endif
      invoke Calculate ,g_man2_y,g_man2_x   
       .if g_map[eax]<4 && g_map[eax]>0;碰撞检测
         mov g_man2_y,bl
       .elseif g_map[eax]==10
         mov g_man2_y,bl
       .elseif g_map[eax]==101
         mov g_man2_y,bl
       .elseif g_map[eax]==8
         mov g_man2_y,bl
       .endif
       invoke Calculate ,bl,g_man2_x 
         .if bl!=g_man2_y && g_map[eax]==102;走出
            mov g_map[eax],10
         .else
                    mov g_map[eax],0
                    invoke Calculate ,g_man2_y,g_man2_x 
                 .if g_map[eax]==102
                    mov g_map[eax],102
                .else 
                    mov g_map[eax],9
                .endif
        .endif


    .ENDIF
    xor ax,ax
    INVOKE GetKeyState, VK_DOWN;xia
   ; test al, 1
    .IF ax>1
      mov g_nDirection_2, 1
      invoke Calculate ,g_man2_y,g_man2_x 
      mov g_map[eax],0
            mov bl,g_man2_y
	  ;sub g_man1_y, 1
	  .if g_man2_y>13
        mov g_man2_y,14
      .else
        add g_man2_y,1
	  .endif
      invoke Calculate ,g_man2_y,g_man2_x 
       .if g_map[eax]<4 && g_map[eax]>0;碰撞检测
         mov g_man2_y,bl
       .elseif g_map[eax]==10
         mov g_man2_y,bl
       .elseif g_map[eax]==101
         mov g_man2_y,bl
       .elseif g_map[eax]==8
         mov g_man2_y,bl
       .endif
       invoke Calculate ,bl,g_man2_x 
         .if bl!=g_man2_y && g_map[eax]==102;走出
            mov g_map[eax],10
         .else
                    mov g_map[eax],0
                    invoke Calculate ,g_man2_y,g_man2_x 
                 .if g_map[eax]==102
                    mov g_map[eax],102
                .else 
                    mov g_map[eax],9
                .endif
        .endif


    .ENDIF
    xor ax,ax
    INVOKE GetKeyState, VK_LEFT;zuo
   ; test al, 1
    .IF ax>1
      mov g_nDirection_2, 2
      invoke Calculate ,g_man2_y,g_man2_x 
      mov g_map[eax],0
            mov bl,g_man2_x
	  ;sub g_man1_y, 1
	  .if g_man2_x<1
        mov g_man2_x,0
      .else
        sub g_man2_x,1
	  .endif
        invoke Calculate ,g_man2_y,g_man2_x 
       .if g_map[eax]<4 && g_map[eax]>0;碰撞检测
         mov g_man2_x,bl
       .elseif g_map[eax]==10
         mov g_man2_x,bl
       .elseif g_map[eax]==101
         mov g_man2_x,bl
       .elseif g_map[eax]==8
         mov g_man2_x,bl
       .endif
       invoke Calculate ,g_man2_y,bl
         .if bl!=g_man2_x && g_map[eax]==102;走出
            mov g_map[eax],10
         .else
                    mov g_map[eax],0
                    invoke Calculate ,g_man2_y,g_man2_x 
                 .if g_map[eax]==102
                    mov g_map[eax],102
                .else 
                    mov g_map[eax],9
                .endif
        .endif

    .ENDIF
    xor ax,ax
    INVOKE GetKeyState, VK_RIGHT;you
   ; test al, 1
    .IF ax>1
      mov g_nDirection_2, 3
      invoke Calculate ,g_man2_y,g_man2_x 
      mov g_map[eax],0
            mov bl,g_man2_x
	  ;sub g_man1_y, 1
	  .if g_man2_x>13
        mov g_man2_x,14
      .else
        add g_man2_x,1
	  .endif

        invoke Calculate ,g_man2_y,g_man2_x 
       .if g_map[eax]<4 && g_map[eax]>0;碰撞检测
         mov g_man2_x,bl
       .elseif g_map[eax]==10
         mov g_man2_x,bl
       .elseif g_map[eax]==101
         mov g_man2_x,bl
       .elseif g_map[eax]==8
         mov g_man2_x,bl
       .endif

          invoke Calculate ,g_man2_y,bl 
         .if bl!=g_man2_x && g_map[eax]==102;走出
            mov g_map[eax],10
         .else
                    mov g_map[eax],0
                    invoke Calculate ,g_man2_y,g_man2_x 
                 .if g_map[eax]==102
                    mov g_map[eax],102
                .else 
                    mov g_map[eax],9
                .endif
        .endif


    .ENDIF
    xor ax,ax
     


  .elseif uMsg == WM_CLOSE
    invoke DestroyWindow, hwnd
  .elseif uMsg == WM_DESTROY
    invoke PostQuitMessage, 0
  .endif

  invoke DefWindowProc, hwnd, uMsg, wParam, lParam;这个函数是默认的窗口处理函数，我们可以把不关心的消息都丢给它来处理
  ret
CR26WindowProc endp

GameInit proc hwnd:HWND
  local @bmp: HBITMAP
  
  invoke GetDC, hwnd      ;提取句柄 便于画图
  mov g_hdc, eax

  invoke CreateCompatibleDC, g_hdc  ;创建设备上下文环境的句柄
  mov g_mdc, eax
  
  invoke CreateCompatibleDC, g_hdc
  mov g_bufdc, eax
  
  invoke CreateCompatibleBitmap, g_hdc, WINDOW_WIDTH, WINDOW_HEIGHT   ;创建与指定的设备环境相关的设备兼容的位图
  mov @bmp, eax
  
  ;设定人物位置和方向
  mov g_nDirection_1, 1
  mov g_nNum, 0
  
  invoke SelectObject, g_mdc, @bmp  ;把一个对象(位图、画笔、画刷等)选入指定的设备描述表
  
  ;加载图片
  invoke LoadImage, NULL, offset g_player0_up_1, IMAGE_BITMAP, 200, 100, LR_LOADFROMFILE
  mov g_hSpriteUp_1, eax
  invoke LoadImage, NULL, offset g_player0_down_1, IMAGE_BITMAP, 200, 100, LR_LOADFROMFILE
  mov g_hSpriteDown_1, eax
  invoke LoadImage, NULL, offset g_player0_left_1, IMAGE_BITMAP, 200, 100, LR_LOADFROMFILE
  mov g_hSpriteLeft_1, eax
  invoke LoadImage, NULL, offset g_player0_right_1, IMAGE_BITMAP, 200, 100, LR_LOADFROMFILE
  mov g_hSpriteRight_1, eax
  
  invoke LoadImage, NULL, offset g_black, IMAGE_BITMAP, 50, 50, LR_LOADFROMFILE
  mov g_hBlack, eax
  invoke LoadImage, NULL, offset g_yBrick, IMAGE_BITMAP, 50, 50, LR_LOADFROMFILE
  mov g_hYBrick, eax
  invoke LoadImage, NULL, offset g_oBrick, IMAGE_BITMAP, 50, 50, LR_LOADFROMFILE
  mov g_hOBrick, eax
  invoke LoadImage, NULL, offset g_cactus, IMAGE_BITMAP, 50, 50, LR_LOADFROMFILE
  mov g_hCactus, eax
  


  invoke GamePaint, hwnd
  
  ret
GameInit endp

GamePaint proc hwnd:HWND
  local @framecount:DWORD
  local @framecoord:DWORD
  local @row:DWORD
  local @col:DWORD
  local @bombcount:DWORD
  local @bombcoord:DWORD

    mov esi, 0
p:
    mov ebx, 15
	xor edx, edx
	mov eax, esi
	div ebx
	mov @row, eax
	mov @col, edx

	mov ebx, 50
	mov eax, @row
	mul ebx
	mov @row, eax
	mov eax, @col
	mul ebx
	mov @col, eax

	mov al, g_map[esi]

	.if g_map[esi] == 0
		invoke SelectObject, g_bufdc, g_hBlack
		invoke BitBlt, g_mdc, @row, @col, 50, 50, g_bufdc, 0, 0, SRCCOPY
	.elseif g_map[esi] == 1
		invoke SelectObject, g_bufdc, g_hYBrick
		invoke BitBlt, g_mdc, @row, @col, 50, 50, g_bufdc, 0, 0, SRCCOPY
	.elseif g_map[esi] == 2
		invoke SelectObject, g_bufdc, g_hOBrick
		invoke BitBlt, g_mdc, @row, @col, 50, 50, g_bufdc, 0, 0, SRCCOPY
	.elseif g_map[esi] == 3
		invoke SelectObject, g_bufdc, g_hCactus
		invoke BitBlt, g_mdc, @row, @col, 50, 50, g_bufdc, 0, 0, SRCCOPY
	.endif


	inc esi
	cmp esi, 225
	jl p
  
  .if g_nDirection_1 == 0
    invoke SelectObject, g_bufdc, g_hSpriteUp_1
	;invoke SelectObject, g_bufdc, g_player0_up_1
  .endif					
  
  .if g_nDirection_1 == 1			
    invoke SelectObject, g_bufdc, g_hSpriteDown_1
	;invoke SelectObject, g_bufdc, g_player0_down_1
  .endif
  
  .if g_nDirection_1 == 2
    invoke SelectObject, g_bufdc, g_hSpriteLeft_1
	;invoke SelectObject, g_bufdc, g_player0_left_1
  .endif
  
  .if g_nDirection_1 == 3
    invoke SelectObject, g_bufdc, g_hSpriteRight_1
	;invoke SelectObject, g_bufdc, g_player0_right_1
  .endif
  
  mov eax, g_nNum
  mov @framecount, eax
  mov @framecoord, 0
  
  .while @framecount > 0
	sub @framecount, 1
	add @framecoord, 50
  .endw															
																	;SRCAND: 使用与运算组合源设备和目标设备区域的颜色
  invoke BitBlt, g_mdc, g_man1_x, g_man1_y, 50, 50, g_bufdc, @framecoord, 50, SRCAND
																	;SRCPAINT:使用或运算组合源设备区域颜色和目标设备区域颜色
  invoke BitBlt, g_mdc, g_man1_x, g_man1_y, 50, 50, g_bufdc, @framecoord, 0, SRCPAINT
  
  invoke BitBlt, g_hdc, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, g_mdc, 0, 0, SRCCOPY
  
  invoke GetTickCount

  mov g_tPre, eax									
														
  add g_nNum, 1
  
  .if g_nNum >= 4
    mov g_nNum, 0
  .endif
  
  ret
GamePaint endp

end WinMain
