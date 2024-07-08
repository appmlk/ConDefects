from collections import deque, defaultdict, Counter
from bisect import bisect_left, bisect_right
from atcoder.segtree import SegTree
from atcoder.lazysegtree import LazySegTree
from atcoder.dsu import DSU
from atcoder.scc import SCCGraph
from itertools import permutations, combinations
from heapq import heappop, heappush
import math, sys
_int = lambda x: int(x)-1
MOD = 998244353
INF = 1<<62
Yes, No = "Yes", "No"

N, M = map(int, input().split())
A = list(map(int, input().split()))
S = sorted(set(A))

UV = []
D = DSU(N)
for _ in range(M):
    u, v = map(_int, input().split())
    if A[u] == A[v]:
        D.merge(u, v)
    else:
        if A[u] > A[v]: u, v = v, u
        UV.append((u, v))

G = SCCGraph(N)
E = defaultdict(list)
for u, v in UV:
    G.add_edge(D.leader(u), D.leader(v))
    E[u].append(v)

memo = [0]*N
memo[D.leader(0)] = 1
for g in G.scc():
    for i in g:
        if memo[i] == 0: continue
        for j in E[i]:
            memo[j] = max(memo[j], memo[i]+1)

print(memo[D.leader(N-1)])