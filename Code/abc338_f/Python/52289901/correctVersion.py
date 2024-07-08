from collections import deque
N,M = map(int, input().split())
inf = 1<<30
dist = [[inf]*N for _ in range(N)]
for _ in range(M):
    u,v,w = map(int, input().split())
    dist[u-1][v-1] = w

for i in range(N):
    dist[i][i] = 0
for k in range(N):
    for i in range(N):
        for j in range(N):
            dist[i][j] = min(dist[i][j], dist[i][k]+dist[k][j])

dp = [[inf]*N for _ in range(2**N)]
for i in range(N):
    dp[1<<i][i] = 0

for i in range(2**N):
    for u in range(N):
        if dp[i][u] == inf:
            continue
        for v in range(N):
            if dist[u][v] == inf:
                continue
            k = i | 1<<v
            dp[k][v] = min(dp[k][v], dp[i][u]+dist[u][v])

ans = min(dp[(1<<N)-1])
if ans == inf:
    ans = "No"
print(ans)