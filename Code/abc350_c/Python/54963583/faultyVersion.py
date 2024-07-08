import bisect
import collections
import functools
import heapq
import itertools
import math
import operator
import string
import sys

from atcoder.dsu import DSU

readline = sys.stdin.readline
LS = lambda: readline().strip()
LI = lambda: int(readline().strip())
LLS = lambda: readline().strip().split()
LL = lambda: list(map(int, readline().strip().split()))
LLMI = lambda: list(map((1).__rsub__, LL()))

n = LI()
A = LLMI()

uf = DSU(n)
for i, a in enumerate(A):
    uf.merge(i, a)

groups = uf.groups()
print(n - len(groups))
for g in groups:
    u = g[0]
    for _ in range(len(g) - 1):
        print(u + 1, A[u] + 1)
        A[u], A[A[u]] = A[A[u]], A[u]
