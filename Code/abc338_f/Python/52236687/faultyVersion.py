# -*- coding: utf-8 -*-

INF = float("inf")

n, m = map(int, input().split())

# d[u][v] := 頂点 u, v の最短距離
d = [[INF] * n for _ in range(n)]
graph: list[list[tuple[int, int]]] = [[] for _ in range(n)]

for _ in range(m):
    u, v, w = map(int, input().split())
    graph[u - 1].append((v - 1, w))
    d[u - 1][v - 1] = w

for k in range(n):
    for u in range(n):
        for v in range(n):
            d[u][v] = min(d[u][v], d[u][k] + d[k][v])

# dp[s][t] := 最終地点が t で、訪れた頂点集合が s であるような最短経路長
dp = [[INF] * n for _ in range(1 << n)]

for start in range(n):
    dp[1 << start][start] = 0

for s in range(1 << n):
    for u in range(n):
        if s & (1 << u) == 0:
            continue
        for v, w in graph[u]:
            dp[s | (1 << v)][v] = min(dp[s | (1 << v)][v], dp[s][u] + d[u][v])

ans = INF
for t in range(n):
    ans = min(ans, dp[-1][t])

if ans == INF:
    print("No")
else:
    print(ans)
