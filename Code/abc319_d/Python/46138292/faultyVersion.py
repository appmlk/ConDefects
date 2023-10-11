#!/usr/bin/env python3
import math
import sys
from bisect import bisect, bisect_left, bisect_right, insort, insort_left, insort_right  # type: ignore
from collections import Counter, defaultdict, deque  # type: ignore
from heapq import heapify, heappop, heappush, heappushpop, heapreplace, merge  # type: ignore
from itertools import accumulate, combinations, permutations, product  # type: ignore

def InputI(): return int(sys.stdin.buffer.readline())
def InputIM(): return map(int, sys.stdin.buffer.readline().split())
def InputIL(): return list(map(int, sys.stdin.buffer.readline().split()))

def InputS(): return sys.stdin.buffer.readline().rstrip().decode("utf-8")
def InputSS(): return sys.stdin.buffer.readline().rstrip().decode("utf-8").split()

def InputIR(n): return [InputI() for _ in range(n)]
def InputILR(n): return [InputIL() for _ in range(n)]
def InputSR(n): return [InputS() for _ in range(n)]
def InputSSR(n): return [InputSS() for _ in range(n)]
def InputSLR(n): return [list(InputS()) for _ in range(n)]
def InputSLIR(n): return [[int(i) for i in list(InputS())] for _ in range(n)]

n, m = InputIM()
l = InputIL()

def num_required_row(x):
    tmp = 0
    res = 1
    for i in range(n):
        tmp += l[i]
        if tmp > x:
            tmp = l[i] + 1
            res += 1
        else:
            tmp += 1
    return res

ng = max(l); ok = 10**9*2*10**5+2*10**5
while ok - ng > 1:
    mid = (ok + ng) // 2
    if num_required_row(mid) <= m:
        ok = mid
    else:
        ng = mid
print(ok)