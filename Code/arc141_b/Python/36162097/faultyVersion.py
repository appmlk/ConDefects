INF = 1 << 64
mod = 10**9 + 7
dir = [(1, 0), (0, 1), (-1, 0), (0, -1)]
import sys
from collections import defaultdict, deque
#from functools import lru_cache
#@lru_cache
input = sys.stdin.readline
sys.setrecursionlimit(10**9)
def ni(): return int(input())
def na(): return map(int, input().split())
def nl(): return list(map(int, input().split()))
def ns(): return input().strip()
def nsl(): return list(input().strip())

mod = 998244353
N, M = na()
for i in range(61):
    if not M >> i:
        bit = i
        break
exit()
nums = [0] * bit
for i in range(1, bit+1):
    if i != bit:
        nums[i-1] = (1 << i) - (1 << (i-1))
    else:
        nums[i-1] = M - (1 << (i-1)) + 1
    nums[i-1] %= mod

dp = [[0] * (bit+1) for _ in range(min(bit+1, N+1))]
dp[0][0] = 1
for i in range(1, min(bit+1, N+1)):
    for j in range(bit+1):
        for k in range(j):
            dp[i][j] += dp[i-1][k] * nums[j-1]
            dp[i][j] %= mod

if N > bit:
    print(0)
else:
    print(sum(dp[N])%mod)


    