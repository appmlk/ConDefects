from collections import deque

N,M = map(int,input().split())

G = []

for i in range(N):
    G.append([-1]*N)

G[0][0] = 0

Q = deque()
Q.append((0,0))

dir = []

for i in range(M+1):
    x,y = i,int(abs(M-i**2)**0.5)
    if x**2 + y**2 == M:
        dir.append([x,y])
        dir.append([-x,y])
        dir.append([x,-y])
        dir.append([-x,-y])

while len(Q):
    a,b = Q.popleft()

    for aa,bb in dir:
        nex1,nex2 = a+aa,b+bb
        if 0 <= nex1 <= N-1 and 0 <= nex2 <= N-1:
            if G[nex1][nex2] == -1:
                G[nex1][nex2] = G[a][b]+1
                Q.append((nex1,nex2))
            elif G[nex1][nex2] > G[a][b]+1:
                G[nex1][nex2] = G[a][b]+1
                Q.append((nex1,nex2))

for i in G:
    print(*i)
