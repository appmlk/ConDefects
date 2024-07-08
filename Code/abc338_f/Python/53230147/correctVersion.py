N, M = map(int, input().split())
G = [[] for _ in range(N)]
for _ in range(M):
    U, V, W = map(int, input().split())
    G[U-1].append((V-1, W))

def func(n):
    for _ in range(M):
        for i in range(N):
            for v, w in G[i]:
                dist[n][v] = min(dist[n][v], dist[n][i]+w)

INF = float("inf")
dist = [[INF]*N for _ in range(N)]
for i in range(N):
    dist[i][i] = 0
    func(i)

dp = [[INF]*N for _ in range(1<<N)]
for i in range(N):
    dp[1<<i][i] = 0

for i in range(1<<N):
    for j in range(N):
        if dp[i][j] != INF:
            for k in range(N):
                dp[i|(1<<k)][k] = min(dp[i|(1<<k)][k], dp[i][j]+dist[j][k])

for i in range(N):
    dp[-1][i] += min(dist[i])

print(min(dp[-1]) if min(dp[-1]) != INF else "No")