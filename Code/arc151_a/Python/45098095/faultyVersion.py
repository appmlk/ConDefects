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
# module
from collections import defaultdict, deque, Counter
from bisect import bisect_left, bisect_right, insort
from heapq import heapify, heappop, heappush
from math import ceil, floor, gcd, sin, cos, radians, log, sqrt
from itertools import product, combinations, permutations, accumulate, groupby
from decimal import Decimal, ROUND_HALF_UP
sys.setrecursionlimit(10**7)

# import pypyjit
# pypyjit.set_param('max_unroll_recursion=-1')
# [[key,len(list(group))] for key, group in groupby(A)]

# your code

N = ii()
S = si()
T = si()
diff = set([i for i in range(N) if S[i]!=T[i]])

if len(diff)&1:
  print(-1)
else:
  U = []
  balance = 0
  flag = 0
  for i in range(N):
    if i in diff:
      if balance == len(diff):
        U.append(T[i])
        balance -= 1
      elif -balance == len(diff):
        U.append(S[i])
        balance += 1
      else:
        U.append("0")
        balance += (-1)**(S[i]!="0")
      diff.remove(i)
    else:
      U.append(S[i])
    # print(balance)
    
  print("".join(U))