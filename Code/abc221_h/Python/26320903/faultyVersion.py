#!usr/bin/env python3
from collections import defaultdict, deque
from heapq import heappush, heappop
from itertools import permutations, accumulate
import sys
import math
import bisect
def LI(): return [int(x) for x in sys.stdin.readline().split()]
def I(): return int(sys.stdin.readline())
def IR(n):
    return [I() for _ in range(n)]
def LIR(n):
    return [LI() for _ in range(n)]

sys.setrecursionlimit(1000000)
mod = 998244353

def solve():
    n,m = LI()
    dp = [[0]*(n+1) for _ in range(n+1)]
    dp[0][0] = 1
    for i in range(1,n+1):
        ndp = dp[i]
        ndp[1] = 1
        for j in range(2,i+1):
            ndp[j] = dp[i-j][j]+dp[i-1][j-1]
            if i > m:
                ndp[j] -= dp[i-j][j-1-m]
            ndp[j] %= mod
    for i in dp[n][1:]:
        print(i)
    return


if __name__ == "__main__":
    solve()
