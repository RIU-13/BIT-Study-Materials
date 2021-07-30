;bignummul.asm
.386
.model flat,stdcall
option casemap:none

includelib msvcrt.lib

printf PROTO C:ptr sbyte,:vararg
scanf PROTO C:ptr sbyte,:vararg
 

.data
	;A
	stringA byte 200 DUP(0)
	lenA	DWORD 0
	numArrayA DWORD 200 DUP(0)

	;B
	stringB byte 200 DUP(0)
	lenB	DWORD 0
	numArrayB DWORD 200 DUP(0)

	;C
	stringC byte 400 DUP(0)
	lenC	DWORD 0
	numArrayC DWORD 400 DUP(0)


	;string
	szStringInput byte "Please input numbers:",0ah,0		
	szScanf1 byte "%s",0
	szScanf2 byte "%s",0
	szStringOutput byte "The result is %s",0ah,0

	szNumprint	byte "%d",0
	stringTest byte "%d",0ah,0
	szNegSign	byte "-",0
	;
	;negSign byte '-',0
	negNum	DWORD 0

	finishMsg	byte  0AH, "Press enter to exit", 0
	scanfFmt    byte  "%c",0
	
.code

;计算输入字符串的长度

getLen	proc	stdcall	numstring:ptr byte,numLen:ptr DWORD		;stdcall可以自动平衡堆栈
	xor ecx,ecx													;ecx清0，便于循环计数
	mov esi,numstring											;esi保存numstring的首地址
	mov edi,numLen												;edi保存numLen的地址
L1:
	mov al,[esi]
	cmp al,0
	jz  L2
	inc ecx
	inc esi
	jmp L1

L2:
	mov [edi],ecx
	ret
	
getLen endp



;将得到的字符串转化为数字逆序保存在数组中

revNum	proc	stdcall	numString:ptr byte,numArray:ptr DWORD,numLen:ptr DWORD
	mov esi,numArray
	mov edi,numLen
	mov ecx,[edi]
	mov edi,numString
	xor eax,eax
L1:
	mov al,[edi][ecx-1]
	cmp al,45
	jz  L2
	sub eax,30H
	mov [esi],eax
	add esi,4
	loop L1
	ret
L2:
	add negNum,1	;此时肯定是最后一个字符串�?
	mov edi,numLen
	mov eax,[edi]
	dec eax
	mov [edi],eax
	ret 
	
revNum endp	


;进行大数相乘(两层循环)
numMul	proc	stdcall num1:ptr dword,num2:ptr dword,num3:ptr dword,len1:dword,len2:dword,len3:ptr dword

	
	xor esi,esi										;第一层循环的计数
	xor edi,edi										;第二层循环的计数
	mov edx,num1
	mov ebx,num2
	mov ecx,num3
	
L2:													;第一层循环
	xor edi,edi
	cmp esi,len1
	jb L3											;小于len1
	mov eax,len1
	add eax,len2
	sub eax,1
	mov esi,len3
	mov [esi],eax
	ret
L3:													;第二层循�?
	mov edx,num1
	mov eax,[edx+esi*4]
	cmp edi,len2
	jnb L1
	mul DWORD PTR [ebx+edi*4]						;结果保存在EDX:EAX
	add edi,esi
	add [ecx+edi*4],eax
	sub edi,esi
	inc edi
	jmp L3
L1:
	inc esi
	jmp L2

numMul endp



;进位处理
carry	proc	stdcall	numArray:ptr DWORD,numLen:ptr DWORD
	mov esi,numArray
	mov edi,numLen
	xor ecx,ecx
	
L1:
	cmp ecx,[edi]
	jnb	L2
	mov eax,[esi+ecx*4]
	mov ebx,0AH
	xor edx,edx
	div ebx
	cmp eax,0
	jnz L3
	inc ecx
	jmp L1
L2:	
	mov eax,[esi+ecx*4]
	cmp eax,0
	jnz	L4
	ret
L3:
	mov [esi+ecx*4],edx
	inc ecx
	add [esi+ecx*4],eax
	jmp L1
L4:
	mov eax,[edi]
	add eax,1
	mov [edi],eax
	ret

carry endp

printArray	proc	stdcall	numArray:ptr DWORD,numLen:DWORD
	mov ecx,numLen
	mov esi,numArray
	cmp negNum,1
	jz L3
L1:
	mov eax,[esi][ecx*4-4]
	pusha									;调用C函数要保存寄存器现场
	invoke printf, offset szNumprint,eax
	popa
	loop L1
	ret
L3:
	pusha
	invoke printf,offset szNegSign
	popa
	jmp L1

printArray endp


main:
		;输入�?
		invoke printf,offset szStringInput
		invoke scanf,offset szScanf1,offset stringA
		invoke scanf,offset szScanf2,offset stringB

		;计算输入的字符串长度
		invoke getLen,offset stringA,offset lenA
		
		invoke getLen,offset stringB,offset lenB
		

		;逆序存放数字
		invoke revNum,offset stringA,offset numArrayA,offset lenA
		invoke revNum,offset stringB,offset numArrayB,offset lenB
		
		;进行大数相乘
		invoke numMul,offset numArrayA,offset numArrayB,offset numArrayC,lenA,lenB,offset lenC
		
		;进位处理
		invoke carry,offset numArrayC,offset lenC

		;输出结果
		invoke printArray,offset numArrayC,lenC
		invoke printf,offset finishMsg
		invoke scanf,offset scanfFmt,offset stringA
		invoke scanf,offset scanfFmt,offset stringA
		ret
end	main


