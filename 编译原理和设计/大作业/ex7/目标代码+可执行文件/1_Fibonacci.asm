.386
.model flat,stdcall
option casemap:none
includelib msvcrt.lib
printf   PROTO  C:dword,:vararg
scanf    PROTO  C:dword,:vararg

.data
InMsg  db   '%d',0
IntPrinMsg   db   '%d',0ah,0
_string1    db     "Please input a number:",0ah,0
_string2    db     "This number's fibonacci value is :",0ah,0

.code

fibonacci proc C
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

mov eax, dword ptr[ebp+8]
mov dword ptr[ebp-4],eax
mov eax, dword ptr[ebp-4]
cmp eax, 1
jge L2
mov dword ptr[ebp-8], 0
jmp L3
L2: 
mov eax, dword ptr[ebp-4]
cmp eax, 2
jg L5
mov dword ptr[ebp-8], 1
jmp L6
L5: 
mov eax, dword ptr[ebp-4]
sub eax, 1
mov ebx, eax
push ebx
call fibonacci
mov ebx, eax
add esp, 4
mov eax, dword ptr[ebp-4]
sub eax, 2
mov ecx, eax
push ecx
call fibonacci
mov ecx, eax
add esp, 4
add ebx, ecx
mov dword ptr[ebp-8], ebx
L6: 
L3: 
mov eax, dword ptr[ebp-8]

pop edi
pop esi
pop edx
pop ecx
pop ebx
mov esp,ebp
pop ebp
ret
fibonacci endp 

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
lea eax, dword ptr[ebp-4]
pushad 
push eax
push offset InMsg
call scanf
add esp,8
popad
lea eax, dword ptr[ebp-4]
mov eax, [eax]
mov dword ptr[ebp-8], eax
push dword ptr [ebp-8]
call fibonacci
mov ebx, eax
add esp, 4
mov dword ptr[ebp-8], ebx
push offset _string2
call printf 
add esp, 4
push dword ptr [ebp-8]
push offset IntPrinMsg
call printf
add esp, 8
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
