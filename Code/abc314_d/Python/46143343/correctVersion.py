N = int(input())
S = input()
Q = int(input())
TXC = [[i for i in input().split()] for _ in range(Q)]

isupper = -1
islower = -1
lastmodified = [-1]*N
li = [c for c in S]
for i, (t,x,c) in enumerate(TXC):
    t = int(t)
    if t == 1:
        x = int(x)
        li[x-1] = c
        lastmodified[x-1] = i
    elif t == 2:
        islower = i
    elif t == 3:
        isupper = i

ans = []
if islower < isupper:
    for c, lm in zip(li, lastmodified):
        ans.append(c if lm>isupper else c.upper())
elif islower > isupper:
    for c, lm in zip(li, lastmodified):
        ans.append(c if lm>islower else c.lower())
else:
    for c, lm in zip(li, lastmodified):
        ans.append(c)
print(''.join(ans))