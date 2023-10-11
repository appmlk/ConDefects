n=int(input())
LR=[list(map(int,input().split())) for _ in range(n)]
LR.sort()
A=[LR[0]]
for i in range(1,n):
    L,R=LR[i]
    if A[-1][1]<L:
        A+=[[L,R]]
    else:
        A[-1][1]=R
for i in A:
    print(*i)