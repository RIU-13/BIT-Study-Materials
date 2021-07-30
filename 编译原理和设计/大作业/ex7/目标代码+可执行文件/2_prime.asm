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
_string2    db     "The number of prime numbers within n is:",0ah,0

.code

prime proc C
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
mov dword ptr[ebp-8], 0
mov dword ptr[ebp-12], 1
mov dword ptr[ebp-16], 2
L1: 
mov eax, dword ptr[ebp-16]
mov ebx, dword ptr[ebp-4]
cmp eax, ebx
jg L2
mov dword ptr[ebp-12], 1
mov dword ptr[ebp-20], 2
L4: 
mov eax, dword ptr[ebp-20]
mov ebx, dword ptr[ebp-20]
imul eax, ebx
mov ecx, eax
mov eax, dword ptr[ebp-16]
cmp ecx, eax
jg L5
xor edx, edx
mov eax,  dword ptr[ebp-16]
mov ebx, dword ptr[ebp-20]
idiv ebx
mov eax, edx
cmp eax, 0
jne L10
mov dword ptr[ebp-12], 0
jmp L5
L10: 
mov eax, dword ptr[ebp-20]
mov dword ptr [ebp-20], eax
add dword ptr [ebp-20],1
jmp L4
L5: 
mov eax, dword ptr[ebp-12]
cmp eax, 1
jne L13
mov eax, dword ptr[ebp-8]
mov dword ptr [ebp-8], eax
add dword ptr [ebp-8],1
push dword ptr [ebp-16]
push offset IntPrinMsg
call printf
add esp, 8
L13: 
mov eax, dword ptr[ebp-16]
mov dword ptr [ebp-16], eax
add dword ptr [ebp-16],1
jmp L1
L2: 
mov eax, dword ptr[ebp-8]

pop edi
pop esi
pop edx
pop ecx
pop ebx
mov esp,ebp
pop ebp
ret
prime endp 

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
mov dword ptr[ebp-4], eax
push dword ptr [ebp-4]
call prime
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
