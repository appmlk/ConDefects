from heapq import *

n, m = map(int, input().split())
A = [*map(int, input().split())]
G = [set() for _ in range(n)]
score = [0] * n
for _ in range(m):
    u, v = map(int, input().split())
    G[u - 1].add(v - 1)
    G[v - 1].add(u - 1)
    score[u - 1] += A[v - 1]
    score[v - 1] += A[u - 1]
Q = [(c, i) for i, c in enumerate(score)]
heapify(Q)
used = [0] * n
ans = 0
while Q:
    _, u = heappop(Q)
    if used[u]:
        continue
    used[u] = 1
    cnt = 0
    for v in G[u]:
        cnt += A[v]
        G[v].remove(u)
        score[v] -= u
        heappush(Q, (score[v], v))
    ans = max(ans, cnt)
print(ans)
