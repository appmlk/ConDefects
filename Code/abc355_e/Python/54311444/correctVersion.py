import math
import re
import functools
import random
import sys
import os
import typing
from math import gcd, comb, sqrt,isqrt,lcm
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

BUFSIZE = 8192


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


sys.stdin, sys.stdout = IOWrapper(sys.stdin), IOWrapper(sys.stdout)
input = lambda: sys.stdin.readline().rstrip("\r\n")
# 1000000000000002493

mod = 998244353
inf = 10 ** 15


def solve():
    sn,l,r=MII()
    n=2**sn
    r+=1
    d=[-1]*(n+1)
    d[l]=0
    dist=[-1]*(n+1)
    q=deque([l])

    while d[r]==-1:
        x=q.popleft()
        if x==0:
            my=sn+1
        else:
            my=(x&(-x)).bit_length()
        for ny in range(my):
            y=2**ny
            if x + y <= n and d[x + y] == -1:
                d[x + y] = d[x] + 1
                dist[x + y] = x
                q.append(x + y)
            if x - y >= 0 and d[x - y] == -1:
                d[x - y] = d[x] + 1
                dist[x - y] = x
                q.append(x - y)



    A=[r]
    pos=r
    while pos!=l:
        A.append(dist[pos])
        pos=A[-1]
    A.pop()
    pos=l
    ans=0
    for p in A[::-1]:
        if p>pos:
            d=p-pos
            print("?",d.bit_length()-1,pos//d,flush=True)
            y=II()
            ans=(ans+y)%100
        else:
            d=pos-p
            print("?", d.bit_length() - 1, p//d, flush=True)
            y = II()
            ans = (ans - y) % 100
        pos=p
    print('!',ans,flush=True)
    return






for _ in range(1):
    solve()