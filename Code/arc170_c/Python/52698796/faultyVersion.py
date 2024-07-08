# import io
# import sys

# _INPUT = """\
# 10 1000000000
# 0 0 1 0 0 0 1 0 1 0

# """
# sys.stdin = io.StringIO(_INPUT)


import sys
sys.setrecursionlimit(10**8)
input = sys.stdin.readline
from collections import defaultdict, deque
import heapq 
import bisect
import math

n, m = map(int, input().split())
A = list(map(int, input().split()))
mod = 998244353
if n<=m-1:
  print(pow(m, A.count(0), mod))
else:
  dp = [0]*(m+2)
  dp[0] = 1
  for a in A:
    dp_ = [0]*(m+2)
    if a==1:
      for i in range(m+1):
        dp_[i+1] = dp[i]
    else:
      for i in range(m+1):
        dp_[i] += dp[i]*i
        dp_[i] %= mod
        dp_[i+1] += dp[i]*(m-i)
        dp_[i+1] %= mod
        # print(m-i)
      dp_[m+1] += dp[m+1]*m
      dp_[m+1] %= mod
    dp = dp_
    # print(dp)
  print(sum(dp)%mod)

    