.386
.model flat,stdcall
option casemap:none

includelib msvcrt.lib

;AL 部分模式说明
.code 
main:
mov ax,0600h   ;清屏
mov bh,7	   ;黑底白字
mov cx,0       ;窗口左上角坐标（0，0）
mov dx,184fh   ;窗口右下角（24，79）
INT 10h

mov ah,0fh
int 10h
push ax

mov ah,0
mov al,12h
int 10h

mov cx,200
mov dx,200
lop1:
mov ah,0ch
mov al,0ah
mov bh,0
int 10h

inc cx
cmp cx,300
jnz lop1

mov ah,1
int 21h

pop ax
mov ah,0
int 10h



end main

