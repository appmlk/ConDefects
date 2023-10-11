from collections import defaultdict
import heapq
N,M = map(int, input().split())

in_edges = [0]*(N+1)
G = defaultdict(list)
for _ in range(M):
    A,B = map(int, input().split())
    G[A].append(B)
    in_edges[B] += 1

H = []
for i in range(1,N+1):
    if in_edges[i] == 0:
        heapq.heappush(H, i)

heapq.heapify(H)
ans = []
while H:
    v = heapq.heappop(H)
    ans.append(v)
    for u in G[v]:
        in_edges[u] -= 1
        if in_edges[u] == 0:
            heapq.heappush(H, u)

if ans:
    print(*ans)
else:
    print(-1)