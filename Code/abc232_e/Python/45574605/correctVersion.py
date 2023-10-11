import sys,random,bisect
from collections import deque,defaultdict
from heapq import heapify,heappop,heappush
from itertools import permutations
from math import gcd,log,sqrt
from atcoder.modint import ModContext, Modint
from atcoder.dsu import DSU

ModContext(1).context.append(998244353)
sys.setrecursionlimit(1000000)

input = lambda :sys.stdin.readline().rstrip()
mi = lambda :map(int,input().split())
li = lambda :list(mi())

MOD = 998244353

H, W, K = mi()
x1, y1, x2, y2 = mi()
dp = [[0] * 4 for i in range(K + 1)]
def cal(x, y):
    if x == x2:
        if y == y2:
            return 3
        else:
            return 1
    else:
        if y == y2:
            return 2
        else:
            return 0
dp[0][cal(x1, y1)] = 1
for i in range(K):
    for j in range(4):
        if j==0:
            dp[i + 1][0] += (W - 2 + H - 2) * dp[i][j] % MOD
            dp[i + 1][1] += dp[i][j] % MOD
            dp[i + 1][2] += dp[i][j] % MOD
        elif j == 1:
            dp[i + 1][0] += (H - 1) * dp[i][j] % MOD
            dp[i + 1][j] += (W - 2) * dp[i][j] % MOD
            dp[i + 1][3] += dp[i][j] % MOD
        elif j == 2:
            dp[i + 1][0] += (W - 1) * dp[i][j] % MOD
            dp[i + 1][j] += (H - 2) * dp[i][j] % MOD
            dp[i + 1][3] += dp[i][j] % MOD
        else:
            dp[i + 1][1] += (W-1) * dp[i][j] % MOD
            dp[i + 1][2] += (H-1) * dp[i][j] % MOD
print(dp[K][3] % MOD)