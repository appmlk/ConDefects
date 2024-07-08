from collections import deque, defaultdict
from bisect import bisect_left, bisect_right
from atcoder.segtree import SegTree
from atcoder.lazysegtree import LazySegTree
from atcoder.dsu import DSU
from itertools import permutations, combinations
import math, sys
sys.setrecursionlimit(10**7)
MOD = 998244353
INF = 10**20
Yes, No = "Yes", "No"

N, Q = map(int, input().split())
C = list(map(int, input().split()))
S = [set([C[i]]) for i in range(N)]

for _ in range(Q):
    a, b = map(lambda x: int(x)-1, input().split())
    swap = False
    if len(S[a]) > len(S[b]):
        swap = True
        a, b = b, a
    S[b] |= S[a]
    S[a].clear()
    if swap:
        S[b], S[a] = S[a], S[b]

    print(len(S[b]))
