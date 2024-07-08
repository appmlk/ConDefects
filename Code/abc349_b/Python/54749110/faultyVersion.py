from random import randint, shuffle
from math import gcd, log2, log, sqrt, hypot, pi, degrees
from fractions import Fraction
from bisect import bisect_left, bisect_right
from itertools import accumulate, permutations, combinations, product, chain, groupby
from sortedcontainers import SortedList
from collections import Counter, deque, defaultdict as ddict
from heapq import heappush as push, heappop as pop
from functools import reduce, lru_cache
import sys
input = sys.stdin.readline
inf = 10**18


def read(dtype=int):
    return list(map(dtype, input().split()))


s, = read(str)
cnt = Counter(s)
pnt = Counter(cnt.values())

if min(pnt.values()) == max(pnt.values()):
    print("Yes")
else:
    print("No")
