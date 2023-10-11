import sys
# input = sys.stdin.readline
# input = lambda :sys.stdin.readline().rstrip()
readline = sys.stdin.readline
input = lambda :readline().rstrip()
sys.setrecursionlimit(6*10**5)
import pypyjit
pypyjit.set_param('max_unroll_recursion=-1')
from heapq import heappush,heappop,heapify
from collections import defaultdict,deque,Counter
from bisect import bisect_left,bisect_right
from itertools import combinations,permutations,product
from math import gcd,sin,cos,atan2,degrees,pi,floor,ceil,radians,factorial
from decimal import Decimal
from string import ascii_lowercase
# import copy
# 整数の計算か小数の計算かでINFを変える。
# INF = float('inf')
INF = 8*10**18
mi = lambda:map(int,input().split())
li = lambda:list(mi())
ti = lambda:tuple(mi())
ii = lambda:int(input())
MOD = 998244353
# MOD = 10**9+7
# 大きい数のべき乗はpow関数を使う。
# dijkstraではなくbfsやdpで解けないか。
# 再帰がPypyでTLEする場合はPythonで提出する。もしくは再帰をDPにする。
# defaultdictでTLEした場合はlistにしてみる。
# listの足し算は遅い。e.g. l = [0]+l

n,k,d = mi()
a = ti()
dp = [[-INF for _ in range(d)] for _ in range(k+1)]
dp[0][0] = 0
for ai in a:
  for i in range(k-1,-1,-1):
    for j in range(d):
      nj = (j+ai)%d
      dp[i+1][nj] = max(dp[i][j]+ai,dp[i+1][nj])
print(dp[k][0] if dp[k][0] > 0 else -1)