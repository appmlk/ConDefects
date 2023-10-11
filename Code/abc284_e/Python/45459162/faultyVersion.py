import sys

sys.setrecursionlimit(10**9)


def dfs(u):
    tmp = 1
    for v in G[u]:
        if not used[v]:
            used[v] = 1
            tmp += dfs(v)
            used[v] = 0
    return tmp


n, m = map(int, input().split())
G = [[] for _ in range(n)]
for _ in range(m):
    u, v = map(int, input().split())
    G[u - 1] += [v - 1]
    G[v - 1] += [u - 1]
used = [0] * n
used[0] = 1
print(dfs(0))
