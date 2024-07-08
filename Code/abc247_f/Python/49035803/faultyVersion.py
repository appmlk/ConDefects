import sys,random,bisect,copy, time
from math import gcd, comb
from collections import deque,defaultdict
from heapq import heapify,heappop,heappush
from functools import lru_cache, cmp_to_key
from itertools import permutations, combinations
from math import gcd,log,sqrt
from sortedcontainers import SortedList
from atcoder.modint import ModContext, Modint
from atcoder.dsu import DSU
from atcoder.segtree import SegTree
from atcoder.fenwicktree import FenwickTree

ModContext(1).context.append(998244353)
sys.setrecursionlimit(100000000)

input = lambda :sys.stdin.readline().rstrip()
mi = lambda :map(int,input().split())
li = lambda :list(mi())

N = int(input())
MOD = 998244353
P = li()
Q = li()
uf = DSU(N)
for i in range(N):
    uf.merge(P[i]-1, Q[i]-1)
f = [2, 3]
for i in range(N-2):
    f.append((f[-1] + f[-2]) % MOD)
ans = 1
for g in uf.groups():
    k = len(g)
    if k == 1:
        t = 1
    elif k == 2:
        t = 3
    elif k == 3:
        t = 4
    else:
        t = f[k-1] + f[k-3]
    ans *= t
    ans %= MOD
print(ans) 