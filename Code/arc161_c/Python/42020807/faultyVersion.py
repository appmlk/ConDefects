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
def dfs(x, parent):
  color = -1
  for y in adj[x]:
    if y == parent:
      continue
    dfs(y, x)
    if ret[y] == S[x]:
      mj[x] += 1
    else:
      mj[x] -= 1
      
    if mj[y] < 0:
      color = INF
      break
    if mj[y] > 0:
      continue
    
    if S[y] == 'B':
      if color == 1:
        color = INF
        break
      mj[y] += 1
      color = 0
    else:
      if color == 0:
        color = INF
        break
      mj[y] += 1
      color = 1

  if color == -1:
    color = S[parent]
  elif color == 0:
    color = 'B'
  elif color == 1:
    color = 'W'
  ret[x] = color
  return

for _ in range(int(input().strip())):
  N = int(input().strip())
  adj = [[] for _ in range(N + 1)]
  mj = [0] * (N + 1)
  for _ in range(N - 1):
    a, b = map(int, input().split())
    adj[a].append(b)
    adj[b].append(a)

  S = 'B' + input().strip()
  ret = [-1] * (N + 1)
  
  root = 1
  dfs(root, 0)

  if INF in ret[1:]:
    print(-1)
  else:
    print(''.join(ret[1:]))
      








      
  
  
  
  