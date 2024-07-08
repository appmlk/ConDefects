import sys

input = sys.stdin.readline

INF = 10**18
N, M = map(int, input().split())
dist = [[(INF, 0)] * N for _ in range(N)]
dist2 = [[(INF, 0)] * N for _ in range(N)]
for i in range(N):
    dist[i][i] = (0, 0)
edges = []
for _ in range(M):
    a, b, c = map(int, input().split())
    a -= 1
    b -= 1
    dist[a][b] = dist[b][a] = (c, -1)
    edges.append((a, b, c))


for k in range(N):
    for i in range(N):
        for j in range(N):
            d1, c1 = dist[i][k]
            d2, c2 = dist[k][j]
            dist[i][j] = min(dist[i][j], (d1 + d2, c1 + c2))

ans = 0
for a, b, c in edges:
    if dist[a][b] < (c, -1):
        ans += 1
print(ans)
