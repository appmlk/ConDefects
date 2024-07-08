def Make_Centroid_Tree():
    parent = [0] * N
    size = [0] * N
    used = [0] * N

    def dfs(root):
        que = [root + N * N]
        order = []
        while que:
            x = que.pop()
            v, u = x % N, x // N
            order.append([v, u])
            for w in g[v]:
                if w == u or used[w]:
                    continue
                que.append(w + v * N)
        order.reverse()

        for v, u in order:
            size[v] = 1
            for w in g[v]:
                if w == u or used[w]:
                    continue
                size[v] += size[w]

        border = size[root] // 2
        centroid = -1

        for v, u in order:
            if size[v] < border:
                continue
            flag = 1
            for w in g[v]:
                if w == u or used[w]:
                    continue
                if size[w] > border:
                    flag = 0
            if flag:
                centroid = v
        return centroid

    que = [N * N]
    while que:
        x = que.pop()
        root, p = x % N, x // N
        centroid = dfs(root)
        parent[centroid] = p
        used[centroid] = 1
        for w in g[centroid]:
            if used[w]:
                continue
            que.append(w + centroid * N)
    for i, p in enumerate(parent):
        parent[i] = p if p != N else -1
    return parent


N = int(input())
g = [[] for _ in range(N)]
for _ in range(N - 1):
    v, w = map(int, input().split())
    v -= 1
    w -= 1
    g[v].append(w)
    g[w].append(v)

par = Make_Centroid_Tree()
ans = [a + 1 if a != -1 else -1 for a in par]
print(*ans)