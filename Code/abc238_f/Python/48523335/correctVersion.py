import os,sys,random,threading
from random import randint
from copy import deepcopy
from io import BytesIO, IOBase
from types import GeneratorType
from functools import lru_cache, reduce
from bisect import bisect_left, bisect_right
from collections import Counter, defaultdict, deque
from itertools import accumulate, combinations, permutations
from heapq import  heapify, heappop, heappush
from typing import Generic, Iterable, Iterator, TypeVar, Union, List
from string import ascii_lowercase, ascii_uppercase
from math import ceil, floor, sqrt, pi, factorial, gcd, log, log10, log2, inf
from decimal import Decimal, getcontext
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
mod = int(1e9 + 7) #998244353
inf = int(1e20)
input = lambda: sys.stdin.readline().rstrip("\r\n")
MI = lambda :map(int,input().split())
li = lambda :list(MI())
ii = lambda :int(input())
py = lambda :print("YES")
pn = lambda :print("NO")
DIRS = [(0, 1), (1, 0), (0, -1), (-1, 0)]  # 右下左上
DIRS8 = [(0, 1), (1, 1), (1, 0), (1, -1), (0, -1), (-1, -1), (-1, 0),(-1, 1)]  # →↘↓↙←↖↑↗


class BIT1:
    """单点修改,区间和查询"""

    __slots__ = "size", "bit", "tree"

    def __init__(self, n: int):
        self.size = n
        self.bit = n.bit_length()
        self.tree = [0]*(n+1)

    def add(self, index: int, delta: int) -> None:
        # index 必须大于0
        while index <= self.size:
            self.tree[index]+=delta
            index += index & -index

    def _query(self, index: int) -> int: 
        res = 0
        while index > 0:
            res += self.tree[index]
            index -= index & -index
        return res

n,p=li() #n个人选p个的方案数

a=li()
b=li()

arr=sorted(zip(a,b))

mod=998244353

#f[i][j][k]前i个人里，选j个人，没选的人里b序号最小为k的方案数

f=[[0]*(n+5) for _ in range(n+5)]


f[0][n+1]=1

for a,b in arr:
    nf=[[0]*(n+5) for _ in range(n+5)]
    for j in range(n+1):
        for k in range(1,n+2):
            if k>b:
                nf[j+1][k]=(nf[j+1][k]+f[j][k])%mod
            nf[j][min(k,b)]=(nf[j][min(k,b)]+f[j][k])%mod
    f=nf

res=0

for k in range(1,n+2):
    res=(res+f[p][k])%mod

print(res)

#print(f)