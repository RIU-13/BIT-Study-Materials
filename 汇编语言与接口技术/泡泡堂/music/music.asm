.386 
.model FLAT,stdcall 
option casemap:none  
;__UNICODE__ equ


include windows.inc
include user32.inc
include kernel32.inc
include gdi32.inc
include	msvcrt.inc
include winmm.inc


														
includelib user32.lib	
includelib kernel32.lib
includelib gdi32.lib
includelib msvcrt.lib
includelib winmm.lib

PlayMidiFile PROTO:DWORD,:DWORD,:DWORD
printf PROTO C:DWORD,:vararg


.data
hInstance dd ?
StarMidiName db "test.mid",0
szMIDISeqr db "Sequencer",0
MidDeviceID dd 0
szMsg db "hello!",0ah,0
dwExitCode db 0
;per_time dd 0
.code


PlayMusicThread proc hWin
	;local @per_time1:dword
	;mov per_time,4000
    invoke PlayMidiFile,hWin,ADDR StarMidiName,4000
	ret

PlayMusicThread endp




main PROC
	local @dwThreadID
	invoke GetModuleHandle,NULL
	mov hInstance,eax

	invoke PlayMidiFile,hInstance,ADDR StarMidiName,4000

	invoke printf,offset szMsg
	
	;invoke CreateThread,NULL,0,offset PlayMusicThread,hInstance,NULL,addr @dwThreadID
	

	;invoke PlayMidiFile,hInstance,ADDR StarMidiName
	;invoke CreateThread,NULL,0,offset mytest,NULL,NULL,addr @dwThreadID
    ;invoke CloseHandle,eax
	;.while 1
	;	mov ebx,1
	;.endw
	.while 1
	.endw
	

main endp


PlayMidiFile proc hWin:DWORD,NameOfFile:DWORD,per_time:DWORD
	local mciOpenParams:MCI_OPEN_PARMS,mciPlayParams:MCI_PLAY_PARMS
	pushad
	mov eax,hWin  ;ÌáÈ¡¾ä±ú
	mov mciPlayParams.dwCallback,eax
	mov eax,offset szMIDISeqr
	mov mciOpenParams.lpstrDeviceType,eax
	mov eax,NameOfFile
	mov mciOpenParams.lpstrElementName,eax
	invoke mciSendCommand,0,MCI_OPEN,MCI_OPEN_TYPE or MCI_OPEN_ELEMENT,ADDR mciOpenParams
	mov eax,mciOpenParams.wDeviceID
	mov MidDeviceID,eax	
	invoke mciSendCommand,MidDeviceID,MCI_PLAY,MCI_NOTIFY,ADDR mciPlayParams ;²¥·Å
	invoke Sleep,per_time
	invoke mciSendCommand,MidDeviceID,MCI_STOP,MCI_NOTIFY,ADDR mciPlayParams 
	popad
	ret
PlayMidiFile endp

end main

