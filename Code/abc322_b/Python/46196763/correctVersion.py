N,M = map(int,input().split())
S = input()
T = input()

is_head = True
is_tail = True

for i in range(N):
    if S[i] != T[i]:
        is_head = False
        break

for i in range(N):
    if S[-1-i] != T[-1-i]:
        is_tail = False
        break

if is_head and is_tail:
    print(0)
elif is_head and is_tail == False:
    print(1)
elif is_head == False and is_tail:
    print(2)
elif is_head == False and is_tail == False:
    print(3)

