;bignummul.asm
.386
.model flat,stdcall
option casemap:none

includelib msvcrt.lib

printf PROTO C:ptr sbyte,:vararg
scanf PROTO C:ptr sbyte,:vararg
 



main:
		mov eax,1

		ret

end	main


