from collections import deque

n, m, k = map(int, input().split())
g = [[] for _ in range(n)]
for _ in range(m):
    u, v, a = map(int, input().split())
    u -= 1
    v -= 1
    g[u].append((v, a))
    g[v].append((u, a))
sw = set(map(lambda x: int(x) - 1, input().split()))


INF = float("INF")
dist = [[INF, INF] for _ in range(n)]
dist[0][0] = 0
q = deque([(0, 0)])
while q:
    v, s = q.popleft()
    if v in sw:
        dist[v][1 - s] = dist[v][s]
        sw.discard(v)
        q.appendleft((v, 1 - s))
    for to, a in g[v]:
        if a == s:
            continue
        if dist[to][s] > dist[v][s] + 1:
            dist[to][s] = dist[v][s] + 1
            q.append((to, s))
ans = min(dist[n - 1])
print(ans if ans != INF else -1)
