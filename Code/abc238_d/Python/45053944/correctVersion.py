import sys, re
from math import ceil, floor, sqrt, pi, factorial, gcd,sin,cos,tan,asin,acos,atan2,exp,log,log10
from collections import deque, Counter, defaultdict
from itertools import product, accumulate
from functools import reduce,lru_cache
from bisect import bisect
from heapq import heapify, heappop, heappush
sys.setrecursionlimit(5 * 10 ** 5)
try:
    from pypyjit import set_param
    set_param('max_unroll_recursion=-1')
except ModuleNotFoundError:
    pass
input = lambda: sys.stdin.readline().rstrip()
ii = lambda: int(input())
mi = lambda: map(int, input().split())
li = lambda: list(mi())
inf = 2 ** 63 - 1
tokens = (i for line in iter(input, "") for i in line.split())

YES = "Yes"
NO = "No"

def get_ans(a,s):
    ss = s - a*2
    return ss >= 0 and a & ss == 0    

def solve(T,q_list):
    for [a,s] in q_list:
        if get_ans(a,s):
            print(YES)
        else:
            print(NO) 

def main():
    T = int(next(tokens))  # type: int
    q_list = [li() for _ in range(T)]
    solve(T,q_list)
    return

main()
