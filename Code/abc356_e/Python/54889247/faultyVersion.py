#!/usr/bin/env python3
import math
import sys
from bisect import (  # type: ignore
    bisect,
    bisect_left,
    bisect_right,
    insort,
    insort_left,
    insort_right,
)
from collections import Counter, defaultdict, deque  # type: ignore
from heapq import (  # type: ignore
    heapify,
    heappop,
    heappush,
    heappushpop,
    heapreplace,
    merge,
)
from itertools import accumulate, combinations, permutations, product  # type: ignore
from typing import Any, Generic, Iterable, Iterator, List, Optional, Tuple, TypeVar

T = TypeVar("T")

# fmt: off
def InputI():  return int(sys.stdin.buffer.readline())
def InputIM(): return map(int, sys.stdin.buffer.readline().split())
def InputIL(): return list(map(int, sys.stdin.buffer.readline().split()))

def InputS():  return sys.stdin.buffer.readline().rstrip().decode("utf-8")
def InputSS(): return sys.stdin.buffer.readline().rstrip().decode("utf-8").split()

def InputIR(n):  return [InputI() for _ in range(n)]
def InputILR(n): return [InputIL() for _ in range(n)]

def InputSR(n):   return [InputS() for _ in range(n)]
def InputSSR(n):  return [InputSS() for _ in range(n)]
def InputSLR(n):  return [list(InputS()) for _ in range(n)]
def InputSLIR(n): return [[int(i) for i in list(InputS())] for _ in range(n)]

inf = float("inf")
mod = 998244353
MOD = 1000000007
sys.setrecursionlimit(10 ** 7)
# fmt: on

N = InputI()
A = InputIL()
A.sort()
le = [0] * (2*10**6+1)
for i in range(N):
    le[A[i]] += 1
for i in range(1, 2*10**6+1):
    le[i] += le[i-1]
cnt_A = dict(Counter(A))

ans = 0
for key, value in cnt_A.items():
    for alpha in range(1, A[-1]//key+1):
        if alpha == 1:
            ans += alpha * (le[(alpha+1)*key-1] - le[alpha*key])
            ans += alpha * value * (value-1) // 2
        else:
            ans += alpha * (le[(alpha+1)*key-1] - le[alpha*key-1]) * value
print(ans)
