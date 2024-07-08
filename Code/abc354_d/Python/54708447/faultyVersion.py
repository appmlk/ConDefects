import bisect
import collections
import functools
import heapq
import itertools
import math
import operator
import string
import sys
import typing

readline = sys.stdin.readline
LS = lambda: readline()
LI = lambda: int(readline())
LLS = lambda: readline().split()
LL = lambda: list(map(int, readline().split()))

a, b, c, d = LL()

def cntItvMod(l, r, div, mod):
    if l > r:
        return cntItvMod(r, l, div, mod)
    l, r = l - mod, r - mod
    rd = r // div * div
    ld = ((l - 1) // div) * div
    return (rd - ld) // div

xodd = cntItvMod(a, c - 1, 2, 1)
xeven = cntItvMod(a, c - 1, 2, 0)
yodd = cntItvMod(b, d - 1, 2, 1)
yeven = cntItvMod(b, d - 1, 2, 0)
half = xodd * yeven + xeven * yodd

m0 = cntItvMod(a, c - 1, 4, 0)
m1 = cntItvMod(a - 1, c - 2, 4, 1)
sq = (m0 * yeven + m1 * yodd) * 2
print(half + sq)
