.386
.model flat,stdcall
option casemap:none

include ../../../../../masm32/include/windows.inc
include ../../../../../masm32/include/user32.inc
include ../../../../../masm32/include/kernel32.inc
include ../../../../../masm32/include/gdiplus.inc

includelib user32.lib
includelib kernel32.lib
includelib gdiplus.lib


;发现一个神奇的事情，如果没有加ret，register会出现错误
GamePaint PROTO hwnd:HWND
.data
	ErrorGetModule byte "GetModuleHandle ERROR!",0
	szBoxTitle     byte "warning",0
	szClassName    byte "HappyPAOPAO",0
	szCaptionMain  byte "Q版泡泡堂",0
	ErrorRegisterClass byte "RegisterClass ERROR!",0
	ErrorLoadBack   byte  "LoadBackGround Image ERROR!",0

	g_hdc          dword  0
	g_mdc          dword 0
	g_bufdc        dword 0

	g_nX           dword  0
	g_nY           dword  0
	g_nDirection   dword  0
	g_nNum         dword  0
	g_tPre         dword  0

	g_szBg         byte  "bg.bmp",0
	g_szGo1        byte  "go1.bmp", 0
    g_szGo2        byte  "go2.bmp", 0
    g_szGo3        byte  "go3.bmp", 0
    g_szGo4        byte  "go4.bmp", 0
	g_htest        byte  "bomb1.bmp",0
	g_htest2       byte  "bomb0_bmp.bmp",0
	g_hBackGround  dword 0
	g_hSpriteUp dd 0
    g_hSpriteDown dd 0
    g_hSpriteLeft dd 0
    g_hSpriteRight dd 0
	g_hblack       dd 0
	WINDOW_WIDTH   dword 750
	WINDOW_HEIGHT DWORD 750


.code

ProWinMain proc uses ebx edi esi, hwnd, uMsg, wParam, lParam		;窗口句柄,消息标识,消息参数
	.if uMsg == WM_KEYDOWN

		.if wParam == VK_UP
			mov g_nDirection,0
			sub g_nY,10
			.if g_nY <= 10
				mov g_nY,10
			.endif
		.elseif wParam == VK_DOWN
			mov g_nDirection,1
			add g_nY,10
			.if g_nY > 460
				mov g_nY,460
			.endif
		.elseif wParam == VK_LEFT
		  mov g_nDirection, 2
		  sub g_nX, 10
		  .if g_nX <= 10
			mov g_nX, 10
		  .endif
		.elseif wParam == VK_RIGHT
		  mov g_nDirection, 3
		  add g_nX, 10
		  .if g_nX >= 725
			mov g_nX, 725
		  .endif
		.endif
	.elseif uMsg == WM_CLOSE
		invoke DestroyWindow,hwnd
	.elseif uMsg == WM_DESTROY
		invoke PostQuitMessage,0
	.else
	    invoke DefWindowProc,hwnd,uMsg,wParam,lParam
		ret
	.endif
	xor eax,eax
	ret
	

ProWinMain endp

GameInit proc hwnd:HWND     ;窗口的句柄
	local @bmp:HBITMAP

	invoke GetDC,hwnd
	mov g_hdc,eax

	invoke CreateCompatibleDC,g_hdc
	mov g_mdc,eax

	invoke CreateCompatibleDC,g_hdc
	mov g_bufdc,eax

	invoke CreateCompatibleBitmap,g_hdc,WINDOW_WIDTH,WINDOW_HEIGHT  ;创建位图
	mov @bmp,eax

	;设定人物位置和方向
	mov g_nX,150
	mov g_nY,350
	mov g_nDirection,3
	mov g_nNum,0

	invoke SelectObject,g_mdc,@bmp  ;该函数选择一对象到指定的设备上下文环境中

	invoke LoadImage,NULL,offset g_szBg,IMAGE_BITMAP, WINDOW_WIDTH, WINDOW_HEIGHT, LR_LOADFROMFILE
	mov g_hBackGround,eax

	invoke LoadImage,NULL,offset g_szGo1,IMAGE_BITMAP, 480, 216, LR_LOADFROMFILE
	mov g_hSpriteUp,eax
	
	invoke LoadImage,NULL,offset g_szGo2,IMAGE_BITMAP, 480, 216, LR_LOADFROMFILE
	mov g_hSpriteDown,eax

	invoke LoadImage,NULL,offset g_szGo3,IMAGE_BITMAP, 480, 216, LR_LOADFROMFILE
	mov g_hSpriteLeft,eax

	invoke LoadImage,NULL,offset g_szGo4,IMAGE_BITMAP, 480, 216, LR_LOADFROMFILE
	mov g_hSpriteRight,eax

	invoke LoadImage,NULL,offset g_htest,IMAGE_BITMAP,150,150, LR_LOADFROMFILE
	mov g_hblack,eax

	invoke GamePaint,hwnd
		
	ret			
GameInit endp

GamePaint proc hwnd:HWND

	local @framecount:DWORD
	local @framecoord:DWORD

	invoke SelectObject,g_bufdc,g_hBackGround
	invoke BitBlt,g_mdc,0,0,WINDOW_WIDTH,WINDOW_HEIGHT,g_bufdc,0,0,SRCCOPY   ;直接复制源设备区域到目标设备中

	
	invoke SelectObject,g_bufdc,g_hblack
	mov eax,g_nNum
	mov ecx,60
	mul ecx
	mov @framecoord,eax

	;invoke BitBlt,g_mdc,g_nX,g_nY,50,50,g_bufdc,0,0,SRCAND
	invoke BitBlt,g_mdc,g_nX,g_nY,150,150,g_bufdc,0,0,SRCCOPY
	;invoke BitBlt, g_mdc, g_nX, g_nY, 50, 50, g_bufdc, 0, 0, SRCCOPY
  
	invoke BitBlt,g_hdc,0,0,WINDOW_WIDTH,WINDOW_HEIGHT,g_mdc,0,0,SRCCOPY

	;画完一次做个标记
	invoke GetTickCount
	mov g_tPre,eax

	;展示完一张后，显示下一帧
	add g_nNum,1
	.if g_nNum >= 8
		mov g_nNum,0
	.endif


	ret
GamePaint endp

WinMain proc
	local @wc:WNDCLASS           ;用来储存某一类窗口的信息
	local @msg:MSG
	local @hInstance:HINSTANCE
	local @hWnd:HWND

	invoke GetModuleHandle,NULL   ;获得当前程序的句柄
	mov @hInstance,eax

	.if eax == 0 
		invoke  MessageBoxA, NULL, offset ErrorGetModule,
                 offset szBoxTitle, MB_OK+MB_ICONERROR
		ret
	.endif

	invoke RtlZeroMemory,addr @wc,sizeof @wc

	mov @wc.style, CS_HREDRAW or CS_VREDRAW
	mov @wc.lpfnWndProc,ProWinMain
	mov @wc.cbClsExtra, 0          ;窗口扩展
	mov @wc.cbWndExtra, 0          ;窗口实例扩展 
	mov eax,@hInstance
	mov @wc.hInstance,eax
	mov @wc.hIcon, NULL; //图标
    mov @wc.hCursor, NULL;//光标
    mov @wc.hbrBackground, COLOR_ACTIVEBORDER;//背景画刷
    mov @wc.lpszMenuName, NULL; //菜单名
    mov @wc.lpszClassName, offset szClassName;//窗口类名

	invoke RegisterClass, addr @wc
	.if eax == 0
		invoke GetLastError
		invoke  MessageBoxA, NULL, offset ErrorRegisterClass,
                 offset szBoxTitle, MB_OK+MB_ICONERROR
		ret
	.endif
	;建立并显示窗口
	invoke CreateWindowEx, 0,\  
                    offset szClassName, offset szCaptionMain,\  
                    WS_OVERLAPPEDWINDOW, \
					400, 200, WINDOW_WIDTH, WINDOW_HEIGHT,\	
                    NULL, NULL, @hInstance, NULL
	mov @hWnd, eax										;保存窗口句柄
	invoke ShowWindow, @hWnd, SW_SHOWNORMAL				 ;显示窗口		
	invoke UpdateWindow, @hWnd							;绘制客户区

	invoke GameInit,@hWnd


	;消息循环
			.while TRUE
				invoke PeekMessage,addr @msg,NULL,0,0,PM_REMOVE  ;从消息队列里查看消息
				.if eax == 1
					invoke TranslateMessage,addr @msg
					invoke DispatchMessage,addr @msg
				.else
					
				.endif
			.endw
		ret		
	

WinMain endp

main:

	call WinMain
	invoke ExitProcess,NULL

end main
