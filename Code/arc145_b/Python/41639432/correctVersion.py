import sys, re
#------pypyで再帰などを提出する場合は下記２行を使用-----
#import pypyjit
#pypyjit.set_param('max_unroll_recursion=-1')
#import numpy as np
from math import ceil, floor, sqrt, pi, factorial, gcd,isfinite
from copy import deepcopy
from collections import Counter, deque,defaultdict
from heapq import heapify, heappop, heappush
from itertools import accumulate, product, combinations, combinations_with_replacement,permutations
from bisect import bisect, bisect_left, bisect_right,insort_right,insort_left
from functools import reduce,lru_cache
#メモ化の場合は下記を使用
#@lru_cache(maxsize=1000)
from decimal import Decimal, getcontext
# input = sys.stdin.readline 
def i_input(): return int(input())
def i_map(): return map(int, input().split())
def i_none_map(): return map(int,input())
def i_list(): return list(i_map())
def i_list_for(N): return [i_list() for _ in range(N)]
def i_none_list(): return list(i_none_map())
def i_row(N): return [i_input() for _ in range(N)]
def i_row_list(N): return [i_list() for _ in range(N)]
def s_input(): return input()
def s_map(): return input().split()
def s_list(): return list(s_map())
def s_row(N): return [s_input for _ in range(N)]
def s_row_str(N): return [s_list() for _ in range(N)]
def s_row_list(N): return [list(s_input()) for _ in range(N)]
def lcm(a, b): return a * b // gcd(a, b)
sys.setrecursionlimit(10 ** 6)
INF = float('inf')
MOD = 10 ** 9 + 7

N,A,B=i_map()
if(A<=B): print(max(0,N-(A-1)))
else:
    a = (A-1)
    q,mod = divmod(N+1,A)
    q-=1
    b = q * B
    if(mod > B):print(b + B)
    else:print(max(0,b+mod))

