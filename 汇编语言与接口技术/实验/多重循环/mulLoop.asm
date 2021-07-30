.386
.model flat,stdcall
option casemap:none

includelib msvcrt.lib

printf PROTO C:ptr sbyte,:vararg
scanf PROTO C:ptr sbyte,:vararg

.data
a dword 1,2,3,4,5,6,7,8,9
printStrlen byte "%d",0ah,0
finishMsg	byte  0AH, "Press enter to exit", 0
scanfFmt    byte  "%c",0
.code

main proc
	local @i:dword,@j:dword,@k:dword,@m:dword,@sum:dword,@temp:dword
	mov @i,0
	jmp L1
L4:
	add @i,1
L1:
	cmp @i,2
	jge L2
	mov @j,0
	jmp L3
L6:
	add @j,1
L3:
	cmp @j,3
	jge L4
	mov @k,0
	jmp L5
L8:
	add @k,1
L5:
	cmp @k,4
	jge L6
	mov @m,0
	jmp L7
L9:
	add @m,1
L7:
	cmp @m,5
	jge L8
	;计算sum
	mov eax,@i
	mov ecx,a[eax*4]
	mov eax,@j
	add ecx,a[eax*4]
	mov eax,@k
	add ecx,a[eax*4]
	mov eax,@m
	add ecx,a[eax*4]
	mov @sum,ecx
	;输出结果
	invoke printf,offset printStrlen,@sum
	
	jmp L9
L2:
	invoke printf,offset finishMsg
	invoke scanf,offset scanfFmt,addr @temp
	ret
	
main endp
end main