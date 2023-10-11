from collections import defaultdict, deque, Counter
from heapq import heappush, heappop, heapify
import math
import bisect
import random
from itertools import permutations, accumulate, combinations, product
import sys
import string
from bisect import bisect_left, bisect_right
from math import factorial, ceil, floor,gcd
from operator import mul
from functools import reduce
from copy import deepcopy
from pprint import pprint



sys.setrecursionlimit(10 ** 9)

InF = 10 ** 40
def LI(): return list(map(int, sys.stdin.readline().split()))
def I(): return int(input())
def LS(): return sys.stdin.buffer.readline().rstrip().decode('utf-8').split()
def S(): return input()
def IR(n): return [I() for i in range(n)]
def LIR(n): return [LI() for i in range(n)]
def SR(n): return [S() for i in range(n)]
def LSR(n): return [LS() for i in range(n)]
def SRL(n): return [list(S()) for i in range(n)]
def MSRL(n): return [[int(j) for j in list(S())] for i in range(n)]


MOD = 998244353


def f(x,bit):
    ok=2
    ng=100000
    while ng>ok+1:
        mid = (ok + ng) // 2
        m=1
        ret=0
        for g in range(6):
            b=bit>>g&1
            ret+=m*b
            m=m*mid
        if ret>x:
            ng=mid
        elif ret==x:
            return mid
        else:
            ok=mid
    return 0



t=I()
for _ in range(t):
    n=I()
    ans=set()
    for i in range(2,1025):
        flg=0
        ni = n
        while ni:
            if ni%i>=2:
                break
            ni//=i
        else:
            ans.add(i)
    for j in range(2**6):
        ti=f(n,j)
        if ti:
            ans.add(ti)
    print(len(ans))


