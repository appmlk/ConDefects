import bisect
import collections
import functools
import heapq
import itertools
import math
import operator
import string
import sys

from atcoder.lazysegtree import LazySegTree

readline = sys.stdin.readline
LS = lambda: readline().strip()
LI = lambda: int(readline().strip())
LLS = lambda: readline().strip().split()
LL = lambda: list(map(int, readline().strip().split()))
LLMI = lambda: list(map((1).__rsub__, LL()))

n, q = LL()
X = LLMI()


def _op(x1, x2):
    return [x1[0] + x2[0], 1]


def _mapping(lz, x):
    return [x[0] + x[1] * lz, x[1]]


def _composition(lz1, lz2):
    return lz1 + lz2


seg = LazySegTree(
    op=_op, e=[0, 0], mapping=_mapping, composition=_composition, id_=0, v=n
)
l = 0
for p in X:
    v, u = seg.get(p)
    l += 1 - 2 * u
    seg.set(p, [v, u ^ 1])
    seg.apply(0, n, l)


print([seg.get(i)[0] for i in range(n)])
