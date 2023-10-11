
from collections import deque

N=int(input())
sx,sy=map(int,input().split())
gx,gy=map(int,input().split())
sx,sy,gx,gy=sx-1,sy-1,gx-1,gy-1
S=[list(input()) for _ in range(N)]


d=((1,1),(1,-1),(-1,-1),(-1,1))
que=deque()
inf=1<<60
dist=[[[inf]*N for _ in range(N)] for _ in range(4)]
for i in range(4):
    dist[i][sy][sx]=1
    que.append((sy,sx,i,1))


while que:
    y,x,v,time=que.popleft()
    if dist[v][y][x]<time:continue

    dy,dx=d[v]
    if 0<=y+dy<=N-1 and 0<=x+dx<=N-1:
        if S[x+dx][y+dy]==".":
            if dist[v][y+dy][x+dx]>time:
                dist[v][y+dy][x+dx]=time
                que.appendleft((dy+y,dx+x,v,time))

    for i in range(4):
        if dist[i][y][x]>time+1:
            dist[i][y][x]=time+1
            que.append((y,x,i,time+1))
ans=inf
for i in range(4):
    ans=min(ans,dist[i][gy][gx])

if ans==inf:
    print(-1)
else:
    print(ans)
