.386 
.model FLAT,stdcall 
option casemap:none  

include windows.inc
include user32.inc
include kernel32.inc
include gdi32.inc
includelib msvcrt.lib

printf PROTO C:ptr sbyte,:vararg

.data
i   dword 0
msg byte "%d",0ah,0
map byte  2,2,2,2,2,2,2,2,2,2,2,2,2,2,2
	byte  2,3,2,0,2,0,0,2,0,0,2,0,2,3,2
	byte  2,3,2,0,2,2,0,2,0,2,2,0,2,3,2
	byte  2,0,0,0,2,0,0,1,0,0,2,0,0,0,2
	byte  2,0,0,0,0,1,3,3,3,1,0,0,0,0,2
	byte  2,0,1,0,0,0,3,3,3,0,0,0,1,0,2
	byte  2,0,1,1,0,1,0,1,0,1,0,1,1,0,2
	byte  2,0,1,1,0,2,2,3,2,2,0,1,1,0,2
	byte  2,0,0,0,0,1,0,0,0,1,0,0,0,0,2
	byte  2,0,3,0,2,1,0,2,0,1,2,0,3,0,2
	byte  2,1,1,1,1,1,1,1,1,1,1,1,1,1,2
	byte  2,0,0,1,1,1,1,1,1,1,1,1,0,0,2
	byte  2,0,3,2,3,2,3,2,3,2,3,2,3,0,2
	byte  2,3,3,3,2,3,3,3,3,3,2,3,3,3,2
	byte  2,2,2,2,2,2,2,2,2,2,2,2,2,2,2			 
			  

.code
main proc
	local temp:byte
	mov i,0
	.while i<225
		mov eax,i
		mov bl,map[eax]
		mov temp,bl
		invoke printf,offset msg,temp
		add i,1
	.endw

	ret

main endp
end main