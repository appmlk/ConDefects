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


h, w, K = read()
si, sj = read()
si -= 1
sj -= 1
a = [read() for _ in range(h)]


def valid(i, j):
    return 0 <= i < h and 0 <= j < w


dx = [1, -1, 0, 0, 0]
dy = [0, 0, 1, -1, 0]
ans = 0

dp = [[-inf] * w for _ in range(h)]

c = min(h*w, K)
dp[si][sj] = 0

ans = a[si][sj] * K

for i in range(1, c+1):
    ndp = [[-inf] * w for _ in range(h)]
    for x in range(h):
        for y in range(w):
            if dp[x][y] == -inf:
                continue
            for k in range(5):
                u = x + dx[k]
                v = y + dy[k]
                if valid(u, v):
                    ndp[u][v] = max(ndp[u][v], dp[x][y] + a[u][v])

    dp = ndp
    for x in range(h):
        for y in range(w):
            if dp[x][y] != -inf:
                ans = max(ans, dp[x][y] + (K-i) * a[x][y])

print(ans)
