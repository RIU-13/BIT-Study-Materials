.386
.model flat,stdcall
option casemap:none

include d:/masm32/include/windows.inc
includelib gdi32.lib
include d:/masm32/include/gdi32.inc
include d:/masm32/include/user32.inc
includelib user32.lib
include d:/masm32/include/kernel32.inc
includelib kernel32.lib
includelib shell32.lib
include d:/masm32/include/shell32.inc
includelib msvcrt.lib
include d:/masm32/include/msvcrt.inc
include d:/masm32/include/Comdlg32.inc
includelib Comdlg32.lib


printf PROTO C:ptr sbyte,:vararg

.data?
	hInstance dd ?
	hWinMain dd	?

.data
	output1 byte '两个文件完全相同！',0
	output2 byte '两个文件不一样，具体不同对应的行号为：',0ah,0
	szBoxTitle byte '结果',0
	szbutton byte 'button',0
	szedit byte 'edit',0

	button1	byte '选择文件A',0
	button2 byte '选择文件B',0
	button3 byte '比较',0

	textOpenPermisson byte 'r',0
	textNone byte '文件不存在',0
	textout1 byte 'File1 Path:',0
	textout2 byte 'File2 Path:',0

	szClassName	db	'MyClass',0
	szCaptionMain byte	'File Compare',0

	szText	byte	'请点击按钮选择你要比较的文件！',0

	filePath1 byte 200 dup(0)
	filePath2 byte 200 dup(0)
	data1 byte 1024 dup(0)
	data2 byte 1024 dup(0)
	lineSeqs  dd  1000 dup(0)
	lineCount dd  0
	szCaption	byte	'执行结果',0
	szFilter	byte	'Text Files(*.txt)',0,'*.txt',0,'All Files(*.*)',0,'*.*',0,0
	eof byte 0
	testout byte '%d',0ah,0
.code

OpenFileDirectory proc path:ptr byte
	local	@stOF:OPENFILENAME
 
		invoke	RtlZeroMemory,addr @stOF,sizeof @stOF
		mov	@stOF.lStructSize,sizeof @stOF
		push	hWinMain
		pop	@stOF.hwndOwner
		mov	@stOF.lpstrFilter,offset szFilter
		mov eax,path
		mov	@stOF.lpstrFile,eax
		mov	@stOF.nMaxFile,MAX_PATH
		mov	@stOF.Flags,OFN_FILEMUSTEXIST or OFN_PATHMUSTEXIST
		invoke	GetOpenFileName,addr @stOF
	
		ret

OpenFileDirectory endp


CompareFile proc stdcall, path1:ptr byte,path2:ptr byte
	local @pfile1:ptr FILE
	local @pfile2:ptr FILE
	local @readnum
	local @temp1,@temp2
	local @len1,@len2
	local @idx
	
	;打开文件
	invoke crt_fopen ,path1,offset textOpenPermisson
	
	mov @pfile1,eax
	


	invoke crt_fopen,path2,offset textOpenPermisson
	mov @pfile2,eax


	mov @idx,0
	
	.while TRUE

		inc @idx
		mov @len1,0
		mov @len2,0
		mov @temp1,1
		mov @temp2,1
	
		invoke crt_memset, addr data1, 0, sizeof data1         		;初始化缓冲区
		invoke crt_memset, addr data2, 0, sizeof data2
		
		invoke crt_feof, @pfile1
		mov @temp1,eax
		invoke crt_feof, @pfile2
		mov @temp2,eax

		mov ecx,@temp1
		mov edx,@temp2
		.break .if ecx && edx


		.if !@temp1											;说明没有到文件末尾
			
			invoke crt_fgets ,addr data1,1024,@pfile1
			invoke lstrlen,addr data1
			mov @len1,eax
			
		.endif

		.if !@temp2
			
			invoke crt_fgets ,addr data2,1024,@pfile2
			invoke lstrlen,addr data2
			mov @len2,eax
		.endif
		

		mov eax,@len1
		mov ebx,@len2
		.if eax!=ebx							;长度不等当然不一样
			
			
			mov ecx,lineCount
			
			mov ebx,@idx
			lea esi,lineSeqs
			mov [esi+ecx*4],ebx
			inc lineCount
			
			.continue
		.endif
		
		
		invoke lstrcmp,addr data1,addr data2
		mov @temp1,eax
		
		.if eax
			
			mov ecx,lineCount
			mov ebx,@idx
			lea esi,lineSeqs
			mov [esi+ecx*4],ebx
			inc lineCount
			
		.endif

	.endw
	ret

CompareFile endp

_ProcWinMain proc uses ebx edi esi, hWnd, uMsg, wParam, lParam		;窗口句柄,消息标识,消息参数
	local @stPs:PAINTSTRUCT
    local @stRect:RECT
    local @hDc
	local @temp:dword
	local @buffer[1024]:byte
	local @TextMSG[1024]:byte
	mov eax,uMsg

	
    .if eax==WM_PAINT
        invoke BeginPaint, hWnd, addr @stPs			;获取窗口设备环境句柄
        mov @hDc, eax					
        
		invoke GetClientRect,hWnd,addr @stRect		;获取客户区大小
		invoke DrawText,@hDc,addr szText,-1,\
			   addr @stRect,\
			   DT_CENTER
		invoke	lstrlen, offset textout1
		invoke TextOut,@hDc, 200,65,offset textout1,eax
		invoke	lstrlen, offset textout2
		invoke TextOut,@hDc, 200,145,offset textout2,eax
        invoke EndPaint, hWnd, addr @stPs

    .elseif eax==WM_CLOSE  ;窗口关闭消息
        invoke DestroyWindow, hWinMain
        invoke PostQuitMessage, NULL	;发出WM_QUIT消息来退出循环


	.elseif eax==WM_CREATE 

		invoke CreateWindowEx,NULL,\
			   offset szbutton,offset button1,\
			   WS_CHILD OR WS_VISIBLE,\
			   50,60,120,35,\
			   hWnd,1,hInstance,NULL

		invoke CreateWindowEx,NULL,\
			   offset szbutton,offset button2,\
			   WS_CHILD OR WS_VISIBLE,\
			   50,140,120,35,\
			   hWnd,2,hInstance,NULL

		invoke CreateWindowEx,NULL,\
			   offset szbutton,offset button3,\
			   WS_CHILD OR WS_VISIBLE,\
			   300,250,120,35,\
			   hWnd,3,hInstance,NULL

		invoke CreateWindowEx,NULL,\
			   offset szedit,NULL,\
			   WS_CHILD OR WS_VISIBLE,\
			   300,65,400,35,\
			   hWnd,4,hInstance,NULL

		invoke CreateWindowEx,NULL,\
			   offset szedit,NULL,\
			   WS_CHILD OR WS_VISIBLE,\
			   300,145,400,35,\
			   hWnd,5,hInstance,NULL

	.elseif eax==WM_COMMAND
		mov eax,wParam
		.if eax == 1
			invoke OpenFileDirectory ,offset filePath1				;打开文件对话框选中相应文件
			invoke  SetDlgItemText, hWnd, 4, offset filePath1		;输出相应的文件路径到窗口上
		.elseif eax == 2
			invoke OpenFileDirectory ,offset filePath2
			invoke  SetDlgItemText, hWnd, 5, offset filePath2
		.elseif eax == 3
			invoke CompareFile ,offset filePath1,offset filePath2
			.if lineCount==0
			invoke  MessageBoxA, NULL, offset output1,
                 offset szBoxTitle, MB_OK+MB_ICONINFORMATION
		    .elseif
				xor esi,esi
				pusha
					invoke crt_strcat,addr @TextMSG,offset output2
				popa
				.while esi < lineCount
					mov edi,lineSeqs[esi*4]
					mov @temp,edi
					pusha
					invoke crt_sprintf ,addr @buffer,addr testout,@temp
					popa
					invoke crt_strcat,addr @TextMSG,addr @buffer
					inc esi
				.endw
				
				invoke  MessageBoxA, NULL, addr @TextMSG,
                 offset szBoxTitle, MB_OK+MB_ICONERROR
			.endif
		.endif 

	.else						;其他默认一部分的消息
		invoke DefWindowProc,hWnd,uMsg,wParam,lParam
		ret
	.endif
	xor eax,eax
	ret

_ProcWinMain endp


_WinMain	proc	
			local @stWndClass:WNDCLASSEX
			local @stMsg:MSG

			invoke GetModuleHandle,NULL		;获得当前程序的句柄
			mov hInstance,eax
			invoke RtlZeroMemory,addr @stWndClass,sizeof @stWndClass

			;注册窗口
			invoke LoadCursor,0,IDC_ARROW
			mov @stWndClass.hCursor,eax
			push hInstance
			pop @stWndClass.hInstance
			mov @stWndClass.cbSize, sizeof WNDCLASSEX			
			mov @stWndClass.style, CS_HREDRAW or CS_VREDRAW
			mov @stWndClass.lpfnWndProc,offset _ProcWinMain	;过程地址		
			mov @stWndClass.hbrBackground, COLOR_WINDOW+1			
			mov @stWndClass.lpszClassName, offset szClassName	
				
			invoke RegisterClassEx, addr @stWndClass

			;建立并显示窗口
			invoke CreateWindowEx, WS_EX_CLIENTEDGE,\  
                           offset szClassName, offset szCaptionMain,\  
                           WS_OVERLAPPEDWINDOW, \
						   300, 200, 800, 400,\	
                           NULL, NULL, hInstance, NULL
			mov hWinMain, eax										;保存窗口句柄
			invoke ShowWindow, hWinMain, SW_SHOWNORMAL				 ;显示窗口		
			invoke UpdateWindow, hWinMain							;绘制客户区
			
			;消息循环
			.while TRUE
				invoke GetMessage,addr @stMsg,NULL,0,0
				.break .if eax == 0		;如果是WM_QUIT，则结束循环
				invoke TranslateMessage,addr @stMsg
				invoke DispatchMessage,ADDR @stMsg
				.endw
				ret
_WinMain endp

 start:
	call _WinMain
	invoke ExitProcess,NULL
	ret
 end start