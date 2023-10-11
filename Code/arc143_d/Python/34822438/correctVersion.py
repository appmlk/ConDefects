n, m = map(int, input().split())
a = [u - 1 for u in map(int, input().split())]
b = [u - 1 for u in map(int, input().split())]

edges = [[] for _ in range(n)]
for i in range(m):
    edges[a[i]].append((b[i], i))
    edges[b[i]].append((a[i], i))

# DFS
ans = [0] * m
visited = [0] * n
for root in range(n):
    if visited[root]:
        continue
    todo = [(root, -1)]
    while todo:
        u, i = todo.pop()
        if visited[u]:
            if u == a[i]:
                ans[i] = 0
            else:
                ans[i] = 1
            continue
        visited[u] = 1
        if i != -1:
            if u == a[i]:
                ans[i] = 1
            else:
                ans[i] = 0
        for v, j in edges[u]:
            if not visited[v]:
                todo.append((v, j))
print(*ans, sep='')
