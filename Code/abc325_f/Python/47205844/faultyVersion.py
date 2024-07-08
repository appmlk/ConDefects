import io
import sys
import pdb
from collections import defaultdict, deque, Counter
from itertools import permutations, combinations, accumulate
from heapq import heappush, heappop
sys.setrecursionlimit(10**6)
from bisect import bisect_right, bisect_left
from math import gcd
import math

_INPUT = """\
6
3
3 5 10
4 3 3
2 2 6
3
3 5 10
4 3 3
2 2 3
2
4 8
3 1 100
4 10000 100
"""

inf=10**4

def solve(test):
  N=int(input())
  D=list(map(int, input().split()))
  L1,C1,K1=list(map(int, input().split()))
  L2,C2,K2=list(map(int, input().split()))
  dp=[inf]*(N+1)*(K1+1)
  dp[0]=0
  def idx(i, j):
    return i*(K1+1)+j
  for i in range(N):
    for j in range(K1+1):
      for k in range(j+1):
        dp[idx(i+1, j)]=min(dp[idx(i+1, j)], dp[idx(i, j-k)]+max((D[i]-k*L1-1)//L2+1,0))
  ans=10*100
  for i in range(K1+1):
    if dp[idx(N, i)]<=K2:
      ans=min(ans, i*C1+dp[idx(N, i)]*C2)
  if ans==10**100: ans=-1
  if test==0:
    print(ans)
  else:
    return None

def random_input():
  from random import randint,shuffle
  N=randint(1,10)
  M=randint(1,N)
  A=list(range(1,M+1))+[randint(1,M) for _ in range(N-M)]
  shuffle(A)
  return (" ".join(map(str, [N,M]))+"\n"+" ".join(map(str, A))+"\n")*3

def simple_solve():
  return []

def main(test):
  if test==0:
    solve(0)
  elif test==1:
    sys.stdin = io.StringIO(_INPUT)
    case_no=int(input())
    for _ in range(case_no):
      solve(0)
  else:
    for i in range(1000):
      sys.stdin = io.StringIO(random_input())
      x=solve(1)
      y=simple_solve()
      if x!=y:
        print(i,x,y)
        print(*[line for line in sys.stdin],sep='')
        break

#0:提出用、1:与えられたテスト用、2:ストレステスト用
main(0)