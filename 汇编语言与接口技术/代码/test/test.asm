.386
.model flat,stdcall
option casemap:none

include c:/masm32/include/windows.inc
includelib gdi32.lib
include c:/masm32/include/gdi32.inc
include c:/masm32/include/user32.inc
includelib user32.lib
include c:/masm32/include/kernel32.inc
includelib kernel32.lib
includelib shell32.lib
include c:/masm32/include/shell32.inc
includelib msvcrt.lib
include c:/masm32/include/msvcrt.inc
include c:/masm32/include/Comdlg32.inc
includelib Comdlg32.lib


printf PROTO C:ptr sbyte,:vararg

.data?
	hInstance dd ?
	hWinMain dd	?

.data
	output1 byte '��ϲ�㣬�����ļ���ȫ��ͬ��',0
	output2 byte '��ѽ�������ļ���һ�������岻ͬ��Ӧ���к�Ϊ��',0ah,0
	szBoxTitle byte '���',0
	szbutton byte 'button',0
	szedit byte 'edit',0

	button1	byte 'ѡ���ļ�A',0
	button2 byte 'ѡ���ļ�B',0
	button3 byte '�Ƚ�',0

	textOpenPermisson byte 'r',0
	textNone byte '�ļ�������',0
	textout1 byte 'File1 Path:',0
	textout2 byte 'File2 Path:',0

	szClassName	db	'MyClass',0
	szCaptionMain byte	'File Compare',0

	szText	byte	'������ťѡ����Ҫ�Ƚϵ��ļ���',0
	flag byte 0
	filePath1 byte 200 dup(0)
	filePath2 byte 200 dup(0)
	data1 byte 1024 dup(0)
	data2 byte 1024 dup(0)
	lineSeqs  dd  1000 dup(0)
	lineCount dd  0
	szCaption	byte	'ִ�н��',0
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

MyOpenFile proc path:ptr byte
	local @temp
	mov eax,path
	mov @temp,eax
	invoke CreateFile,@temp,GENERIC_READ,\
		   FILE_SHARE_READ,0,\
		   OPEN_EXISTING,FILE_ATTRIBUTE_NORMAL,0
	
	ret
MyOpenFile endp

CompareFile proc stdcall, path1:ptr byte,path2:ptr byte
	local @pfile1:ptr FILE
	local @pfile2:ptr FILE
	local @readnum
	local @temp1,@temp2
	local @len1,@len2
	local @idx
	
	;���ļ�
	invoke crt_fopen ,path1,offset textOpenPermisson
	
	mov @pfile1,eax
	.if eax == NULL
		invoke  MessageBoxA, NULL, addr textNone,
                 offset szBoxTitle, MB_OK+MB_ICONERROR
		ret
	.endif


	invoke crt_fopen,path2,offset textOpenPermisson
	mov @pfile2,eax
	.if eax == NULL
		invoke  MessageBoxA, NULL, addr textNone,
                 offset szBoxTitle, MB_OK+MB_ICONERROR
		ret 
	.endif
	mov flag,1
	mov @idx,0
	
	.while TRUE

		inc @idx
		mov @len1,0
		mov @len2,0
		mov @temp1,1
		mov @temp2,1
	
		invoke crt_memset, addr data1, 0, sizeof data1         		;��ʼ��������
		invoke crt_memset, addr data2, 0, sizeof data2
		
		invoke crt_feof, @pfile1
		mov @temp1,eax
		invoke crt_feof, @pfile2
		mov @temp2,eax

		mov ecx,@temp1
		mov edx,@temp2
		.break .if ecx && edx


		.if !@temp1											;˵��û�е��ļ�ĩβ
			
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
		.if eax!=ebx							;���Ȳ��ȵ�Ȼ��һ��
			
			
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

_ProcWinMain proc uses ebx edi esi, hWnd, uMsg, wParam, lParam		;���ھ��,��Ϣ��ʶ,��Ϣ����
	local @stPs:PAINTSTRUCT
    local @stRect:RECT
    local @hDc
	local @temp:dword
	local @buffer[1024]:byte
	local @TextMSG[1024]:byte
	mov eax,uMsg

	
    .if eax==WM_PAINT
        invoke BeginPaint, hWnd, addr @stPs			;��ȡ�����豸�������
        mov @hDc, eax					
        
		invoke GetClientRect,hWnd,addr @stRect		;��ȡ�ͻ�����С
		invoke DrawText,@hDc,addr szText,-1,\
			   addr @stRect,\
			   DT_CENTER
		invoke	lstrlen, offset textout1
		invoke TextOut,@hDc, 200,65,offset textout1,eax
		invoke	lstrlen, offset textout2
		invoke TextOut,@hDc, 200,145,offset textout2,eax
        invoke EndPaint, hWnd, addr @stPs

    .elseif eax==WM_CLOSE  ;���ڹر���Ϣ
        invoke DestroyWindow, hWinMain
        invoke PostQuitMessage, NULL	;����WM_QUIT��Ϣ���˳�ѭ��


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
			invoke OpenFileDirectory ,offset filePath1				;���ļ��Ի���ѡ����Ӧ�ļ�
			invoke  SetDlgItemText, hWnd, 4, offset filePath1		;�����Ӧ���ļ�·����������
		.elseif eax == 2
			invoke OpenFileDirectory ,offset filePath2
			invoke  SetDlgItemText, hWnd, 5, offset filePath2
		.elseif eax == 3
			
			invoke CompareFile ,offset filePath1,offset filePath2

			.if !flag			;flag=0�����ļ�������
			 ret
			
			.endif
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

	.else						;����Ĭ��һ���ֵ���Ϣ
		invoke DefWindowProc,hWnd,uMsg,wParam,lParam
		ret
	.endif
	xor eax,eax
	ret

_ProcWinMain endp


_WinMain	proc	
			local @stWndClass:WNDCLASSEX
			local @stMsg:MSG

			invoke GetModuleHandle,NULL		;��õ�ǰ����ľ��
			mov hInstance,eax
			invoke RtlZeroMemory,addr @stWndClass,sizeof @stWndClass

			;ע�ᴰ��
			invoke LoadCursor,0,IDC_ARROW
			mov @stWndClass.hCursor,eax
			push hInstance
			pop @stWndClass.hInstance
			mov @stWndClass.cbSize, sizeof WNDCLASSEX			
			mov @stWndClass.style, CS_HREDRAW or CS_VREDRAW
			mov @stWndClass.lpfnWndProc,offset _ProcWinMain	;���̵�ַ		
			mov @stWndClass.hbrBackground, COLOR_WINDOW+1			
			mov @stWndClass.lpszClassName, offset szClassName		
			invoke RegisterClassEx, addr @stWndClass

			;��������ʾ����
			invoke CreateWindowEx, WS_EX_CLIENTEDGE,\  
                           offset szClassName, offset szCaptionMain,\  
                           WS_OVERLAPPEDWINDOW, \
						   300, 200, 800, 400,\	
                           NULL, NULL, hInstance, NULL
			mov hWinMain, eax										;���洰�ھ��
			invoke ShowWindow, hWinMain, SW_SHOWNORMAL				 ;��ʾ����		
			invoke UpdateWindow, hWinMain							;���ƿͻ���
			
			;��Ϣѭ��
			.while TRUE
				invoke GetMessage,addr @stMsg,NULL,0,0
				.break .if eax == 0		;�����WM_QUIT�������ѭ��
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