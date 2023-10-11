from collections import deque

H,W = map(int,input().split())
C = [input() for _ in range(H)]

dxs = [0,1]
dys = [1,0]

que = deque()
que.append((0,0))
dis = [[-1]*W for _ in range(H)]
dis[0][0]=1

ans = 0
while que:
    y,x = que.popleft()
    for dy,dx in zip(dys,dxs):
        ny = y+dy
        nx = x+dx
        if ny>=H or nx>=W: continue
        if C[ny][nx]=='.' and dis[ny][nx]==-1:
            dis[ny][nx]=dis[y][x]+1
            ans = max(ans,dis[ny][nx])
            que.append((ny,nx))

print(ans)
