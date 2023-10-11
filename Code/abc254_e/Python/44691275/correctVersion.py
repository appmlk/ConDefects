# BFS
import collections

N, M = map(int, input().split())
AB = [list(map(int, input().split())) for _ in range(M)]
Q = int(input())
XK = [list(map(int, input().split())) for _ in range(Q)]

graph = collections.defaultdict(list)

for i in range(0, M):
    a = AB[i][0]
    b = AB[i][1]
    graph[a].append(b)
    graph[b].append(a)

for i in range(Q):
    x = XK[i][0]
    k = XK[i][1]
    queue = []
    if k != 0: queue.append(x)
    dist = collections.defaultdict(lambda: -1)
    dist[x] = 0

    while queue:
        q = queue.pop(0)
        if dist[q] >= k: continue
        for v in graph[q]:
            if dist[v] != -1: continue
            dist[v] = dist[q] + 1
            queue.append(v)

    print(sum(dist.keys()))
