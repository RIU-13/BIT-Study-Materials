.386
.model flat,stdcall
option casemap:none
includelib msvcrt.lib
printf   PROTO  C:dword,:vararg
scanf    PROTO  C:dword,:vararg

.data
InMsg  db   '%d',0
IntPrinMsg   db   '%d',0ah,0
_string1    db     "please input ten int number for bubble sort:",0ah,0
_string2    db     "before bubble sort:",0ah,0
_string3   db   0ah,0
_string4    db     "after bubble sort:",0ah,0

.code

main proc C
push ebp
mov ebp,esp
sub esp,160
push ebx
push ecx
push edx
push esi
push edi

lea edi,[ebp-160]
mov ecx,40
mov eax,0cccccccch
rep stos dword ptr[edi]

push offset _string1
call printf 
add esp, 4
mov dword ptr[ebp-44], 0
L2: 
mov eax, dword ptr[ebp-44]
cmp eax, 10
jge L3
mov ebx, dword ptr[ebp-44]
imul ebx, 4
lea eax, dword ptr[ebp-40][ebx] 
lea ebx, dword ptr[ebp-48]
pushad 
push ebx
push offset InMsg
call scanf
add esp,8
popad
lea ebx, dword ptr[ebp-48]
mov ebx, [ebx]
mov dword ptr[eax], ebx
mov eax, dword ptr[ebp-44]
mov dword ptr [ebp-44], eax
add dword ptr [ebp-44],1
jmp L2
L3: 
push offset _string2
call printf 
add esp, 4
mov dword ptr[ebp-44], 0
L9: 
mov eax, dword ptr[ebp-44]
cmp eax, 10
jge L10
mov ebx, dword ptr[ebp-44]
imul ebx, 4
lea eax, dword ptr[ebp-40][ebx] 
push dword ptr[eax]
push offset IntPrinMsg
call printf
add esp, 8
mov eax, dword ptr[ebp-44]
mov dword ptr [ebp-44], eax
add dword ptr [ebp-44],1
jmp L9
L10: 
push offset _string3
call printf 
add esp, 4
mov dword ptr[ebp-44], 0
L16: 
mov eax, dword ptr[ebp-44]
cmp eax, 10
jge L17
mov dword ptr[ebp-52], 0
L19: 
mov eax, 10
mov ebx, dword ptr[ebp-44]
sub eax, ebx
mov ecx, eax
sub ecx, 1
mov ebx, ecx
mov ecx, dword ptr[ebp-52]
cmp ecx, ebx
jge L20
mov ecx, dword ptr[ebp-52]
imul ecx, 4
lea ebx, dword ptr[ebp-40][ecx] 
mov ecx, dword ptr[ebp-52]
add ecx, 1
mov edx, ecx
imul edx, 4
lea ecx, dword ptr[ebp-40][edx] 
mov edx, [ebx]
mov ebx, [ecx]
cmp edx, ebx
jle L28
mov ecx, dword ptr[ebp-52]
imul ecx, 4
lea ebx, dword ptr[ebp-40][ecx] 
mov ecx, dword ptr[ebx]
mov dword ptr[ebp-56], ecx
mov ecx, dword ptr[ebp-52]
imul ecx, 4
lea ebx, dword ptr[ebp-40][ecx] 
mov ecx, dword ptr[ebp-52]
add ecx, 1
mov edx, ecx
imul edx, 4
lea ecx, dword ptr[ebp-40][edx] 
mov edx, dword ptr[ecx]
mov dword ptr[ebx], edx
mov ebx, dword ptr[ebp-52]
add ebx, 1
mov ecx, ebx
imul ecx, 4
lea ebx, dword ptr[ebp-40][ecx] 
mov ecx, dword ptr[ebp-56]
mov dword ptr[ebx], ecx
L28: 
mov ebx, dword ptr[ebp-52]
mov dword ptr [ebp-52], ebx
add dword ptr [ebp-52],1
jmp L19
L20: 
mov ebx, dword ptr[ebp-44]
mov dword ptr [ebp-44], ebx
add dword ptr [ebp-44],1
jmp L16
L17: 
push offset _string4
call printf 
add esp, 4
mov dword ptr[ebp-44], 0
L38: 
mov ebx, dword ptr[ebp-44]
cmp ebx, 10
jge L39
mov ecx, dword ptr[ebp-44]
imul ecx, 4
lea ebx, dword ptr[ebp-40][ecx] 
push dword ptr[ebx]
push offset IntPrinMsg
call printf
add esp, 8
mov ebx, dword ptr[ebp-44]
mov dword ptr [ebp-44], ebx
add dword ptr [ebp-44],1
jmp L38
L39: 
mov eax,0

pop edi
pop esi
pop edx
pop ecx
pop ebx
mov esp,ebp
pop ebp
ret
main endp 
end main
