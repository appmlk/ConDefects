from collections import deque, defaultdict
from math import log, asin, acos, cos, sin, tan, atan2, floor, gcd, sqrt, pi
# from math import *
from heapq import *
from bisect import bisect, bisect_left
import sys
from itertools import combinations, permutations, count
from functools import lru_cache, cmp_to_key
from operator import add, mul, sub, xor
import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 6) 
# atan2(y, x) := 
# artan(y/x) ([-pi, pi] -> if theta < 0 -> theta += 2pi -> [0, 2pi])
def ceil(m, n):
  if n == 0:
    return INF
  return (m + n - 1) // n

INF = 10**20
BASE = 31
CONST = 10 ** 9

MAX = 10 ** 6
MOD = 998244353

N, D = map(int, input().split())
if D * 2 > (N - 1):
  print('No')
else:
  print('Yes')
  for i in range(N):
    for j in range(D):
      k = (i + j + 2) % N
      if k == 0:
        k = N
      print(i+1, k)
  
      
  
  
  
  