from collections import deque
import sys
ipt = sys.stdin.readline

def calcDiameter(g):
    '''
    g := 木、隣接リスト表記
    '''
    n = len(g)
    inf = 10**9
    dst1,dst2 = [inf]*n, [inf]*n
    
    d = deque()
    d.append(0)
    visited = [0]*n
    visited[0] = 1
    
    while d:
        cur = d.popleft()
        for nxt in g[cur]:
            if visited[nxt]:
                continue
            visited[nxt] = 1
            d.append(nxt)

    d = deque()
    d.append((cur,0))
    visited = [0]*n
    visited[cur] = 1

    while d:
        cur,dist = d.popleft()
        dst1[cur] = dist
        for nxt in g[cur]:
            if visited[nxt]:
                continue
            visited[nxt] = 1
            d.append((nxt,dist+1))

    d = deque()
    d.append((cur,0))
    visited = [0]*n
    visited[cur] = 1

    while d:
        cur,dist = d.popleft()
        dst2[cur] = dist
        for nxt in g[cur]:
            if visited[nxt]:
                continue
            visited[nxt] = 1
            d.append((nxt,dist+1))

    return dist,dst1,dst2

N = int(ipt())
G = [[] for _ in range(N)]
for _ in range(N-1):
    u,v = map(int,ipt().split())
    u,v = u-1,v-1
    G[u].append(v)
    G[v].append(u)

D,dst1,dst2 = calcDiameter(G)
T = []

visited = [0]*N
if D%2:
    for i in range(N):
        if dst1[i] == D//2 and dst2[i] == D//2+1:
            T.append(i)
            visited[i] = 1
        if dst2[i] == D//2 and dst1[i] == D//2+1:
            T.append(i)
            visited[i] = 1
else:
    cen = None
    for i in range(N):
        if dst1[i] == D//2 and dst2[i] == D//2:
            cen = i
            break
    for nxt in G[cen]:
        T.append(nxt)
        visited[i] = 1
    visited[cen] = 1

U = []

for t in T:
    cnt = 0
    dq = deque()
    dq.append((t,0))

    while dq:
        cur,dist = dq.popleft()
        if dist == (D-1)//2:
            cnt += 1
        for nxt in G[cur]:
            if visited[nxt]:
                continue
            visited[nxt] = 1
            dq.append((nxt,dist+1))
    U.append(cnt)

dp = [0]*3
dp[0] = 1

MOD = 998244353
for c in U:
    ndp = [0]*3
    ndp[0] = dp[0]
    ndp[1] = (dp[1] + dp[0]*c%MOD)%MOD
    ndp[2] = (dp[2] + dp[2]*c%MOD + dp[1]*c%MOD)%MOD
    dp = ndp

print(dp[2])