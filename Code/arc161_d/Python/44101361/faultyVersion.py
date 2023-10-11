# import sys
# sys.setrecursionlimit(10**7)
# input = sys.stdin.readline
# import pypyjit
# pypyjit.set_param('max_unroll_recursion=-1')
def mp():return map(int,input().split())
def lmp():return list(map(int,input().split()))
def lm1(LIST): return list(map(lambda x:x-1, LIST))
def mps(A):return [tuple(map(int, input().split())) for _ in range(A)]
def stoi(LIST):return list(map(int,LIST))
def itos(LIST):return list(map(str,LIST))
def atoi(LIST): return [ord(i)-ord("a") for i in LIST]
def Atoi(LIST): return [ord(i)-ord("A") for i in LIST]
def LT(LIST,N): return LIST[bisect.bisect_left(LIST,N)-1]
def LE(LIST,N): return LIST[bisect.bisect_right(LIST,N)-1]
def GT(LIST,N): return LIST[bisect.bisect_right(LIST,N)]
def GE(LIST,N): return LIST[bisect.bisect_left(LIST,N)]
def bitA(X,A):return X & 1<<A == 1<<A
import math
import bisect
import heapq
import time
from copy import copy as cc
from copy import deepcopy as dc
from itertools import accumulate, product
from collections import Counter, defaultdict, deque
def ceil(U,V):return (U+V-1)//V
def modf1(N,MOD):return (N-1)%MOD+1
inf = (1<<63)-1
mod = 998244353

n,d = mp()
if n*(n-1)//2 < n*d:
    print("No")
    exit()
print("Yes")
for i in range(1,d+1):
    for j in range(1,n+1):
        print(j,modf1(j+d,n))


