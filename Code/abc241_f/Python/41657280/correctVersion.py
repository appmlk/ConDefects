from collections import defaultdict
H,W,N=map(int,input().split())
sx,sy=map(int,input().split())
gx,gy=map(int,input().split())

from collections import deque
import bisect
def bfs(s):
    dq=deque()
    dq.append([s,0])
    high=defaultdict(lambda:-1)
    high[s]=0
    while len(dq)!=0:
        p,h=dq.popleft()
        x,y=p
        go=[]
        l=bisect.bisect_left(xs[x],y)
        if 0<=l-1:
            go.append((x,xs[x][l-1]+1))
        if l<len(xs[x]):
            go.append((x,xs[x][l]-1))
        l=bisect.bisect_left(ys[y],x)
        if 0<=l-1:
            go.append((ys[y][l-1]+1,y))
        if l<len(ys[y]):
            go.append((ys[y][l]-1,y))
        for g in go:
            if high[g]==-1:
                high[g]=h+1
                dq.append([g,h+1])
    return high

xs=defaultdict(list)
ys=defaultdict(list)
for _ in range(N):
    x,y=map(int,input().split())
    xs[x].append(y)
    ys[y].append(x)
for k in xs.keys():
    xs[k].sort()
for k in ys.keys():
    ys[k].sort()
print(bfs((sx,sy))[(gx,gy)])