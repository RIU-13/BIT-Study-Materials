.386 
.model FLAT,stdcall 
option casemap:none  
;__UNICODE__ equ


include windows.inc
include user32.inc
include kernel32.inc
include gdi32.inc


include ddq.inc
														
includelib user32.lib	
includelib kernel32.lib
includelib gdi32.lib
includelib msvcrt.lib

GameInit PROTO hwnd:HWND
GamePaint PROTO hwnd:HWND

printf PROTO C: dword,:dword

.data 
	ErrorRegisterClass byte "Error!",0
	szBoxTitle         byte "warning",0
	szFmt			   byte "row:", 0
.code ;������  r/e  api
WinMain proc
  LOCAL  @wc: WNDCLASS
  LOCAL  @hInstance : HINSTANCE
  LOCAL  @hWnd:HWND 
  local  @msg:MSG

  ;��ȡģ���ַ
  invoke GetModuleHandle, NULL
  mov @hInstance, eax
  .if eax == 0
    mov eax, 0
    ret
  .endif 

  mov @wc.style, CS_VREDRAW or CS_HREDRAW; ;Ĭ��,��ֱ��ˮƽ���촰��,�����������²��ֺͻ���
  mov @wc.lpfnWndProc, CR26WindowProc;
  mov @wc.cbClsExtra, 0;  //�����ڴ��С
  mov @wc.cbWndExtra, 0; 
  mov eax, @hInstance
  mov @wc.hInstance, eax;//ʵ�����
  mov @wc.hIcon, NULL; //ͼ��
  mov @wc.hCursor, NULL;//���
  mov @wc.hbrBackground, COLOR_ACTIVEBORDER;//������ˢ
  mov @wc.lpszMenuName, NULL; //�˵���
  mov @wc.lpszClassName, offset g_szClassName;//��������
  invoke RegisterClass, addr @wc						
  .if eax == 0
    mov eax, 0
	invoke GetLastError
	invoke  MessageBoxA, NULL, offset ErrorRegisterClass,
                 offset szBoxTitle, MB_OK+MB_ICONERROR
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
  mov @hWnd, eax      ;���洰�ھ��

  invoke ShowWindow, @hWnd, SW_SHOW;
  invoke UpdateWindow, @hWnd;
  
  invoke GameInit, @hWnd

  ;��Ϣѭ��
  .while  TRUE
   invoke PeekMessage, addr @msg, NULL, 0, 0, PM_REMOVE  ;�ȴ���Ϣ
   .if  eax == 1
	 invoke TranslateMessage, addr @msg 
     invoke DispatchMessage, addr @msg 
   .else
     invoke GetTickCount   ;������
	 sub eax, g_tPre
	 .if eax >= 50
	   invoke GamePaint, @hWnd
	 .endif
   .endif

    
  .endw

  mov eax, TRUE
  ret
WinMain endp

CR26WindowProc proc hwnd:HWND,  uMsg:UINT, wParam:WPARAM, lParam:LPARAM

  
  .if uMsg == WM_KEYDOWN
    ;invoke MessageBox, hwnd, offset g_szKeyDown, offset g_szTitle, MB_OK
	.if wParam == VK_UP
	  mov g_nDirection_1, 0
	  sub g_man1_y, 15
	  .if g_man1_y <= 15
	    mov g_man1_y, 0
	  .endif
	.elseif wParam == VK_DOWN
	  mov g_nDirection_1, 1
	  add g_man1_y, 15
	  .if g_man1_y > 15
	    mov g_man1_y, 15
	  .endif
	.elseif wParam == VK_LEFT
	  mov g_nDirection_1, 2
	  sub g_man1_x, 50
	  .if g_man1_x <= 50
	    mov g_man1_x, 0
	  .endif
	.elseif wParam == VK_RIGHT
	  mov g_nDirection_1, 3
	  add g_man1_x, 50
	  .if g_man1_x >= 50
	    mov g_man1_x, 50
	  .endif
	.endif
  .elseif uMsg == WM_CLOSE
    invoke DestroyWindow, hwnd
  .elseif uMsg == WM_DESTROY
    invoke PostQuitMessage, 0
  .endif

  invoke DefWindowProc, hwnd, uMsg, wParam, lParam ;Ĭ�ϵĴ��ڴ����������ǿ��԰Ѳ����ĵ���Ϣ��������������
  ret
CR26WindowProc endp

GameInit proc hwnd:HWND
  local @bmp: HBITMAP
  
  invoke GetDC, hwnd      ;��ȡ��� ���ڻ�ͼ
  mov g_hdc, eax

  invoke CreateCompatibleDC, g_hdc  ;�����豸�����Ļ����ľ��
  mov g_mdc, eax
  
  invoke CreateCompatibleDC, g_hdc
  mov g_bufdc, eax
  
  invoke CreateCompatibleBitmap, g_hdc, WINDOW_WIDTH, WINDOW_HEIGHT   ;������ָ�����豸������ص��豸���ݵ�λͼ
  mov @bmp, eax
  
  ;�趨����λ�úͷ���
  mov g_nDirection_1, 1
  mov g_nNum, 0
  
  invoke SelectObject, g_mdc, @bmp  ;��һ������(λͼ�����ʡ���ˢ��)ѡ��ָ�����豸������
  
  ;����ͼƬ
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
  
  invoke LoadImage, NULL, offset g_bombed, IMAGE_BITMAP, 750, 750, LR_LOADFROMFILE
  mov g_hBoomed, eax

  .if eax == 0 
		invoke  MessageBoxA, NULL, offset ErrorMsg,
                 offset BoxTitle, MB_OK+MB_ICONERROR
	    ret
  .endif




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

	;invoke SelectObject,g_bufdc,g_hBoomed
	;invoke BitBlt, g_mdc, 200, 200, 250, 250, g_bufdc, 0, 0, SRCCOPY

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
																	;SRCAND: ʹ�����������Դ�豸��Ŀ���豸�������ɫ
  invoke BitBlt, g_mdc, g_man1_x, g_man1_y, 50, 50, g_bufdc, @framecoord, 50, SRCAND
																	;SRCPAINT:ʹ�û��������Դ�豸������ɫ��Ŀ���豸������ɫ
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





.const ;������
  g_szClassName db  "CR26WndClass", 0
  g_szWndName   db  "DDQ", 0

  g_player0_up_1 db "people0_up.bmp", 0
  g_player0_left_1 db "people0_left.bmp", 0
  g_player0_right_1 db "people0_right.bmp", 0
  g_player0_down_1 db "people0_down.bmp", 0
  
  g_bombed db "bombexplode.bmp",0
  g_bomb db "bomb.bmp", 0
  g_yBrick db "barrier2.bmp", 0
  g_oBrick db "barrier1.bmp", 0
  g_cactus db "barrier0.bmp", 0
  g_black db "black.bmp", 0

.data  ;������
  g_szBuf   db 512 dup(0)
  g_hdc dd 0
  g_mdc dd 0
  g_bufdc dd 0

  g_hYBrick dd 0
  g_hOBrick dd 0
  g_hCactus dd 0
  g_hBlack dd 0

  g_hBoom dd 0
  g_hBoomed dd 0

  g_hSpriteUp_1 dd 0
  g_hSpriteDown_1 dd 0
  g_hSpriteLeft_1 dd 0
  g_hSpriteRight_1 dd 0

  g_tPre dd 0
  g_tNow dd 0
  g_nNum dd 0

  g_man1_x dd 0
  g_man1_y dd 0
  g_man2_x dd 0
  g_man2_y dd 10

  g_nDirection_1 dd 0
  g_nDirection_2 dd 0

  ErrorMsg byte "Error!",0ah,0
  BoxTitle byte "��ʾ",0ah,0
  g_map	byte 2,2,2,2,2,2,2,2,2,2,2,2,2,2,2
		byte 2,3,3,0,0,0,0,0,0,0,1,0,0,3,2
		byte 2,2,2,0,0,1,1,1,0,3,1,0,3,3,2
		byte 2,0,0,0,0,0,1,1,0,0,1,1,2,3,2
		byte 2,2,2,2,0,0,0,0,0,2,1,1,3,2,2
		byte 2,0,2,0,1,0,1,2,1,1,1,1,2,3,2
		byte 2,0,0,0,3,3,0,2,0,0,1,1,3,3,2
		byte 2,2,2,1,3,3,1,3,0,2,1,1,2,3,2
		byte 2,0,0,0,3,3,0,2,0,0,1,1,3,3,2
		byte 2,0,2,0,1,0,1,2,1,1,1,1,2,3,2
		byte 2,2,2,2,0,0,0,0,0,2,1,1,3,2,2
		byte 2,0,0,0,0,0,1,1,0,0,1,1,2,3,2
		byte 2,2,2,0,0,1,1,1,0,3,1,0,3,3,2
		byte 2,3,3,0,0,0,0,0,0,0,1,0,0,3,2
		byte 2,2,2,2,2,2,2,2,2,2,2,2,2,2,2

  
WINDOW_WIDTH EQU 765
WINDOW_HEIGHT EQU 795

