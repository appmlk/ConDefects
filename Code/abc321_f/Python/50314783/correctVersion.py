import sys
from collections import Counter, defaultdict, deque
from itertools import accumulate, combinations, permutations
from heapq import heappop, heappush
from math import inf
sys.setrecursionlimit(10**6)
MOD = 998244353

stdin = sys.stdin

ni = lambda: int(ns())
na = lambda: list(map(int, stdin.readline().split()))
ns = lambda: stdin.readline().rstrip()  # ignore trailing spaces

q,k = na()
dp = [0]*(k+1)
dp[0] = 1
for _ in range(q):
    query = ns().split()
    t = query[0]
    x = int(query[1])
    if t == '+':
        for i in range(k,-1,-1):
            if i-x < 0: break
            dp[i] += dp[i-x]
    else:
        assert t == '-'
        for i in range(k+1):
            if i-x < 0: continue
            dp[i] -= dp[i-x]
    # print(t,x,dp)
    print(dp[k]%MOD)
