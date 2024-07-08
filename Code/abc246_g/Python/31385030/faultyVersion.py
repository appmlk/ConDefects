import sys
import math
import sys
from collections import defaultdict


N = int(input())
S = [0]+list(map(int, input().split()))
AB = [list(map(int, input().split())) for _ in range(N-1)]
g = [[] for _ in range(N)]
for a, b in AB:
    a -= 1
    b -= 1
    g[a].append(b)
    g[b].append(a)
children = [[] for _ in range(N)]
parents = [None]*N
q = [(0, None)]
dfs_ord = []
while len(q) > 0:
    u, p = q.pop()
    parents[u] = p
    dfs_ord.append(u)
    for v in g[u]:
        if v != p:
            q.append((v, u))
            children[u].append(v)


def is_ok(arg):
    dp = [None]*N
    def dfs(u):
        if dp[u] is not None:
            return dp[u]
        ret = -1
        for v in children[u]:
            if v == parents[u]:
                continue
            ret = sum([ret, dfs(v)])
        ret = max(0, ret)
        ret += S[u]>arg
        dp[u] = ret
        return dp[u]
    for u in dfs_ord[::-1]:
        dfs(u)
    return dfs(0)==0




def bisect(ng, ok):
    while abs(ok - ng) > 1:
        mid = (ok + ng) // 2
        if is_ok(mid):
            ok = mid
        else:
            ng = mid
    return ok
print(bisect(0, max(S)+1))
