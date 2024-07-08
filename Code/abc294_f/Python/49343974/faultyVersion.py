from random import randint, shuffle
from math import gcd, log2, log, sqrt
from fractions import Fraction
from bisect import bisect_left, bisect_right
from itertools import accumulate, permutations, combinations, product, chain
from sortedcontainers import SortedList
from collections import Counter, deque, defaultdict as ddict
from heapq import heappush as push, heappop as pop
from functools import reduce, lru_cache
import sys
input = sys.stdin.readline
inf = 10**18


def read(dtype=int):
    return list(map(dtype, input().split()))


n, m, k = read()

a = [read() for _ in range(n)]
b = [read() for _ in range(m)]

l = 0
r = 10 ** 6


def check(mid):
    A = [x - (x+y) * mid for x, y in a]
    B = [x - (x+y) * mid for x, y in b]
    A.sort()
    B.sort()
    ptr = m-1
    res = 0
    for i in A:
        while ptr >= 0 and i + B[ptr] >= 0:
            ptr -= 1
        res += m - ptr - 1
    return res >= k


while r - l > 1e-10:
    mid = (l+r) / 2
    if check(mid):
        l = mid
    else:
        r = mid


print(l * 100)
