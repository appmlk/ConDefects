import collections
N=int(input())
XY=[list(map(int,input().split())) for i in range(N)]
lx=collections.defaultdict(list)
rx=collections.defaultdict(list)
S=input()
for i in range(N):
    x,y=XY[i]
    if S[i]=="L":
        lx[y].append(x)
    else:
        rx[y].append(x)
        
ans=False
for y in lx:
    ans|=y in rx and max(lx)>min(rx)
if ans:
    print("Yes")
else:
    print("No")
