from atcoder.dsu import DSU
from heapq import heappop, heappush
from collections import deque
N, M = map(int, input().split())
A = list(map(int, input().split()))

G = [[0] * N for _ in range(N)]
for i in range(N):
    for j in range(i+1, N):
        G[i][j] = (pow(A[i], A[j], M) + pow(A[j], A[i], M)) % M
        G[j][i] = G[i][j]

edges = []
for i in range(N):
    for j in range(i+1, N):
        edges.append(((pow(A[i], A[j], M) + pow(A[j], A[i], M)) % M, i, j))
edges.sort()

ans = 0
uf = DSU(N)
for p, i, j in edges:
    if uf.same(i, j): continue
    ans += p
    uf.merge(i, j)
print(ans)