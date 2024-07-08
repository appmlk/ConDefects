import math
import sys
sys.setrecursionlimit(500_000)
from collections import defaultdict

N, Q = map(int, input().split())
g = [[] for _ in range(N + 1)]
for _ in range(Q):
    l, r = map(int, input().split())
    l -= 1
    g[l].append(r)
    g[r].append(l)
visited = [False] * (N + 1)
q = [0]
visited[0] = True
while len(q) > 0:
    i = q.pop()
    for j in g[i]:
        if not visited[j]:
            visited[j] = True
            q.append(j)
if all(visited):
    print('Yes')
else:
    print('No')    