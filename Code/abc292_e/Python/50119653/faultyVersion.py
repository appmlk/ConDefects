n, m = map(int, input().split())
edge = [[] for _ in range(n+1)]
eset = [set() for _ in range(n+1)]

for _ in range(m):
    a, b = map(int, input().split())
    edge[a] += [b]
    eset[a].add(b)

ans = 0
for i in range(n):
    vis = [0]*(n+1)
    i += 1
    vis[i] = 1
    q = [i]
    while q:
        v = q.pop()
        for nv in edge[v]:
            if vis[nv]: continue
            vis[nv] = 1
            q += [nv]
            ans += 1
print(ans)
