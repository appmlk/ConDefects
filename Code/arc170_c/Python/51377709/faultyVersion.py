import sys
input = sys.stdin.readline
def ii(): return int(input())
def fi(): return float(input())
def si(): return input().rstrip()
def mii(): return map(int, input().split())
def fii(): return map(float, input().split())
def mii1(): return map(lambda x: int(x)-1, input().split())

def lii(): return list(map(int, input().split()))
def lii1(): return list(map(lambda x: int(x)-1, input().split()))
def lfi(): return list(map(float, input().split()))
def lsi(): return list(input().rstrip())
def lmsi(): return list(map(str, input().split()))

def iir(n): return [int(input()) for _ in range(n)]
def fir(n): return [float(input()) for _ in range(n)]
def sir(n): return [input().rstrip() for _ in range(n)]

def liir(n): return [list(map(int, input().split())) for _ in range(n)]
def lii1r(n): return [list(map(lambda x: int(x)-1, input().split())) for _ in range(n)]
def lfir(n): return [list(map(float, input().split())) for _ in range(n)]
def lsir(n): return [list(input().rstrip()) for _ in range(n)]
def lmsir(n): return [list(map(str, input().split())) for _ in range(n)]

def lii_alp(): return list(map(lambda x: ord(x)-97, input().rstrip()))
def lii_ALP(): return list(map(lambda x: ord(x)-65, input().rstrip()))
around = [[-1,0],[0,1],[1,0],[0,-1]]
from copy import deepcopy
from collections import defaultdict, deque, Counter
from bisect import bisect_left, bisect_right, insort
from heapq import heapify, heappop, heappush
from math import ceil, floor, gcd, sin, cos, radians, log, sqrt, inf
from itertools import product, combinations, permutations, accumulate, groupby
from decimal import Decimal, ROUND_HALF_UP
sys.setrecursionlimit(10**7)
mod = 998244353
n,m = mii()
s = lii()
if n <= m+1:
  ans = 1
  for i in range(n):
    if s[i] == 0:
      ans *= m
      ans %= mod
    else:
      continue
  print(ans)
  exit()
dp = [[0]*(m+2) for i in range(n)]
if s[0] == 0:
  dp[0][1] = m
else:
  dp[0][1] = 1
for i in range(n-1):
  for j in range(1,m+2):
    if s[i+1] == 0:
      dp[i+1][j] += dp[i][j]*j
      dp[i+1][j] %= mod
      if m-j >= 1 :
        dp[i+1][j+1] += dp[i][j]*(m-j)
        dp[i+1][j+1] %= mod
    else:
      if j <= m:
        dp[i+1][j+1] += dp[i][j]
        dp[i+1][j+1] %= mod
ans = 0
for i in range(1,m+2):
  ans += dp[-1][i]
#print(dp)
print(ans)
  