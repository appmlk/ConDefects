import math
import re
import functools
import random
import sys
import os
import typing
from math import gcd,comb,sqrt
from collections import Counter, defaultdict, deque
from functools import lru_cache, reduce
from itertools import accumulate, combinations, permutations
from heapq import nsmallest, nlargest, heappushpop, heapify, heappop, heappush
from io import BytesIO, IOBase
from copy import deepcopy
import threading
from typing import *
from bisect import bisect_left, bisect_right
from types import GeneratorType

# from sortedcontainers import  SortedList

from operator import add

BUFSIZE = 4096


class FastIO(IOBase):
    newlines = 0

    def __init__(self, file):
        self._fd = file.fileno()
        self.buffer = BytesIO()
        self.writable = "x" in file.mode or "r" not in file.mode
        self.write = self.buffer.write if self.writable else None

    def read(self):
        while True:
            b = os.read(self._fd, max(os.fstat(self._fd).st_size, BUFSIZE))
            if not b:
                break
            ptr = self.buffer.tell()
            self.buffer.seek(0, 2), self.buffer.write(b), self.buffer.seek(ptr)
        self.newlines = 0
        return self.buffer.read()

    def readline(self):
        while self.newlines == 0:
            b = os.read(self._fd, max(os.fstat(self._fd).st_size, BUFSIZE))
            self.newlines = b.count(b"\n") + (not b)
            ptr = self.buffer.tell()
            self.buffer.seek(0, 2), self.buffer.write(b), self.buffer.seek(ptr)
        self.newlines -= 1
        return self.buffer.readline()

    def flush(self):
        if self.writable:
            os.write(self._fd, self.buffer.getvalue())
            self.buffer.truncate(0), self.buffer.seek(0)


class IOWrapper(IOBase):
    def __init__(self, file):
        self.buffer = FastIO(file)
        self.flush = self.buffer.flush
        self.writable = self.buffer.writable
        self.write = lambda s: self.buffer.write(s.encode("ascii"))
        self.read = lambda: self.buffer.read().decode("ascii")
        self.readline = lambda: self.buffer.readline().decode("ascii")


sys.stdin = IOWrapper(sys.stdin)
sys.stdout = IOWrapper(sys.stdout)
input = lambda: sys.stdin.readline().rstrip("\r\n")


def I():
    return input()


def II():
    return int(input())


def MII():
    return map(int, input().split())


def LI():
    return list(input().split())


def LII():
    return list(map(int, input().split()))


def GMI():
    return map(lambda x: int(x) - 1, input().split())


def LGMI():
    return list(map(lambda x: int(x) - 1, input().split()))

inf=10**15
def solve():
    n,h=MII()
    A=LII()
    B=[LII() for i in range(n-1)]+[[0,0]]
    dp=[[inf]*(h+1) for i in range(h+1)]
    for i in range(h+1):
        dp[h][i]=0
    pre=0
    for k in range(n):
        p,f=B[k]
        x=A[k]-pre
        pre=A[k]
        ndp=[[inf]*(h+1) for i in range(h+1)]
        for i in range(h+1):
            for j in range(h+1):
                if x<=i and j+x<=h:
                    ndp[i-x][j+x]=min(ndp[i-x][j+x],dp[i][j])
                if i>=x and j+x<=h:
                    ndp[min(i-x+f,h)][j+x] = min(ndp[min(i-x+f,h)][j+x], p+dp[i][j])
                if i+x<=h and min(j+f,h)>=x:
                    ndp[i][j] = min(ndp[i][j], p+dp[i+x][min(j+f,h)-x])
        dp=ndp[:]


    ans=inf
    for i in range(h+1):
        ans=min(dp[i][i],ans)
    if ans<inf:
        print(ans)
    else:
        print(-1)





for _ in range(1):
    solve()