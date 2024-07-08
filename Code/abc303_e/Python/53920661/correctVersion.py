from collections import deque
N = int(input())
G = [[] for _ in range(N)]
for _ in range(N-1):
    a, b = map(int, input().split())
    a -= 1
    b -= 1
    G[a].append(b)
    G[b].append(a)

dist = [-1 for i in range(N)]
que = deque()
for i in range(N):
    if len(G[i]) == 1:
        dist[i] = 0
        que.append(i)
        break

while que:
    v = que.popleft()
    for v2 in G[v]:
        if dist[v2] != -1:
            continue
        dist[v2] = dist[v] + 1
        que.append(v2)

center = [i for i in range(N) if dist[i]%3==1]
ans = [len(G[i]) for i in center]
ans.sort()
print(*ans)
