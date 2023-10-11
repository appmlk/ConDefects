import collections

N, M = map(int, input().split())
links = [[] for _ in range(N+1)]
for _ in range(M):
    u, v = map(int, input().split())
    links[u].append(v)
    links[v].append(u)



inf = float("inf")
def bfs(s):
    visited = [inf] * (N+1)
    visited[s] = 0
    que = collections.deque([s])
    while que:
        x = que.popleft()
        for nx in links[x]:
            if visited[nx] != inf:
                continue
            visited[nx] = visited[x] + 1
            que.append(nx)
    return visited

A = bfs(1)
B = bfs(N)

ans = []
for i in range(1, N+1):
    a = min(A[0] + B[i], A[N])
    if a == inf:
        a = -1
    ans.append(a)

print(*ans)