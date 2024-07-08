import sys
import os
from io import BytesIO, IOBase
BUFSIZE = 8192
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

def I():
    return input()
def II():
    return int(input())
def MI():
    return map(int, input().split())
def LI():
    return list(input().split())
def LII():
    return list(map(int, input().split()))
def GMI():
    return map(lambda x: int(x) - 1, input().split())

#------------------------------FastIO---------------------------------

from bisect import *
from heapq import *
from collections import *
from functools import *
from itertools import *
from time import *
from random import *
from math import log, gcd
#dfs - stack#
#check top!#

mod = 998244353
dp = [[0 for _ in range(5010)] for _ in range(5010)]

def solve():
    n = II()
    r = LII()
    c = LII()

    if sum(r) != sum(c):
        print(0)
        return

    x, s = 0, 0
    for i in range(n):
        s += c[i]
        if c[i] == 2:
            x += 1
    
    dp[0][x] = 1
    for i in range(1, n + 1):
        s -= r[i - 1]
        for j in range(n + 1):
            y = s - 2 * j
            if y < 0:
                break
            if r[i - 1] == 0:
                dp[i][j] = dp[i - 1][j]
            elif r[i - 1] == 1:
                dp[i][j] = (dp[i - 1][j + 1] * (j + 1) % mod + dp[i - 1][j] * (y + 1) % mod) % mod
            else:
                dp[i][j] = (dp[i - 1][j + 1] * (j + 1) % mod + dp[i - 1][j + 2] * ((j + 2) * (j + 1) // 2) % mod + dp[i - 1][j] * ((y + 2) * (y + 1) // 2) % mod + dp[i - 1][j + 1] * (j + 1) * y % mod) % mod    

    print(dp[n][0])

for _ in range(1):solve()

