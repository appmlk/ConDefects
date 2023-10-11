import sys,random,bisect
from collections import deque,defaultdict
from heapq import heapify,heappop,heappush
from functools import lru_cache
from itertools import permutations
from math import gcd,log,sqrt
from atcoder.modint import ModContext, Modint
from atcoder.dsu import DSU

ModContext(1).context.append(998244353)
sys.setrecursionlimit(1000000)

input = lambda :sys.stdin.readline().rstrip()
mi = lambda :map(int,input().split())
li = lambda :list(mi())

N, D = mi()
P = []
for i in range(N):
    l, r = mi()
    P.append((r, l))
P.sort()
ans = 0
x = - 1e10
for i in range(N):
    r, l = P[i]
    if l > x + D - 1:
        ans += 1
        x = r
print(ans)