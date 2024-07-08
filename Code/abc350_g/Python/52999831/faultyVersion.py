import sys
import io, os
sys.setrecursionlimit(10**7)
input = sys.stdin.readline

mod = 998244353

n, q = map(int, input().split())
x = 0

par = [-1]*n
sz = [1]*n
root = list(range(n))
g = [[] for _ in range(n)]

for i in range(q):
    A, B, C = map(int, input().split())
    t = (A*(x+1)%mod)%2 + 1
    a = (B*(x+1)%mod)%n
    b = (C*(x+1)%mod)%n
    if t == 1:
        if sz[root[a]] < sz[root[b]]:
            a, b = b, a
        sz[root[a]] += sz[root[b]]
        def dfs(v, p = -1):
            par[v] = p
            root[v] = root[a]
            for u in g[v]:
                if u == p:
                    continue
                dfs(u, v)
        dfs(b)
        par[b] = a
        g[a].append(b)
        g[b].append(a)
    else:
        ans = -1
        pa = par[a]
        pb = par[b]
        if pa == pb and pa != -1:
            ans = pa
        elif pb != -1 and par[pb] == a:
            ans = pb
        ans += 1
        print(ans)
        x = ans
