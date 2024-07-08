from random import randint, shuffle
from math import gcd, log2, log, sqrt
from fractions import Fraction
from bisect import bisect_left, bisect_right
from itertools import accumulate, permutations, combinations, product, chain, groupby
from sortedcontainers import SortedList
from collections import Counter, deque, defaultdict as ddict
from heapq import heappush as push, heappop as pop
from functools import reduce, lru_cache
import sys
input = sys.stdin.readline
inf = 10**18 + 1


def read(dtype=int):
    return list(map(dtype, input().split()))


n, h = read()
a = [read() for _ in range(n)]

event = Counter()
event[1] = 0

for t, d in a:
    event[t] = max(event[t], d)

l = 0
r = h
keys = sorted(event)
K = len(keys)
keys.append(inf)
R = [0] * (K+1)
for i in range(K-1, -1, -1):
    R[i] = max(R[i+1], event[keys[i]])


def f(l, r):
    return (l+r) * (r-l+1) // 2


def check(m):
    tot = best = 0
    for i in range(K):
        best = max(best, event[keys[i]] * keys[i])
        curr = keys[i]
        nxt = min(m+1, keys[i+1])
        deal = R[i+1]
        if deal == 0:
            ptr = inf
        else:
            ptr = (best + deal - 1) // deal

        tot += best * max(0, min(ptr, nxt) - curr)
        if ptr < nxt:
            ptr = max(ptr, curr)
            tot += deal * f(ptr, nxt-1)
    return tot >= h


# tot = 0
# for i in range(1, 10):
#     best = 0
#     for u, v in event.items():
#         best = max(best, v * min(u, i))
#     tot += best
#     print("cmp", i, tot)

# print(check(9))


while l < r:
    m = (l+r) >> 1
    if check(m):
        r = m
    else:
        l = m + 1


print(l)
