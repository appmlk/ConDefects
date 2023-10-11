S = input()
N = len(S)
x,y = 0,0

for i in range(N):
    if S[i] != "a":
        break
    x += 1

for i in reversed(range(N)):
    if S[-i] != "a":
        break
    y += 1

S = "a"*(y-x) + S
if S == S[::-1]:
    print('Yes')
else:
    print('No')
    