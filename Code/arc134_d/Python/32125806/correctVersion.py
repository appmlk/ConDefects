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

def lii_alp(): return list(map(lambda x: ord(x)-97, input().rstrip()))
def lii_ALP(): return list(map(lambda x: ord(x)-65, input().rstrip()))
around_4 = [[-1,0],[0,1],[1,0],[0,-1]]
# module
from collections import defaultdict, deque, Counter
from bisect import bisect_left, bisect_right
from heapq import heapify, heappop, heappush
from math import ceil, floor
sys.setrecursionlimit(10**7)
# your code

N = ii()
A = lii()
d = defaultdict(list)
for i,v in enumerate(A[:N]):
  d[v].append(i)
mind = min(d)
b = mind+1
ind = 0
for i in d[mind]:
  if b > A[i+N]:
    b = min(b, A[i+N]) 
    ind = i

if b != mind+1:
  print(A[ind], A[ind+N]) 
else:
  now = d[mind][0]
  ans = [now]
  bottom = A[now+N]
  ok = 0
  for v in sorted(d.keys()):
    for i in d[v]:
      if ans[-1] < i:
        if A[i] < bottom or (A[i] == bottom and ok == 1):
          if ok == 0:
            if A[ans[-1]+N] < A[i+N]:
              ok = 1
            elif A[ans[-1]+N] > A[i+N]:
              ok = -1
          ans.append(i)
          
  for i in ans:
    print(A[i],end = " ")
  for i in ans:
    print(A[i+N],end = " ")