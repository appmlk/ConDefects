from io import BytesIO, IOBase
import os
from random import getrandbits, randrange
from string import ascii_lowercase, ascii_uppercase
import sys
from math import ceil, floor, sqrt, pi, factorial, gcd, log, log10, log2, inf, cos, sin
from copy import deepcopy, copy
from collections import Counter, deque, defaultdict
from heapq import heapify, heappop, heappush
from itertools import (
    accumulate,
    product,
    combinations,
    combinations_with_replacement,
    permutations,
)
from bisect import bisect, bisect_left, bisect_right
from functools import lru_cache, reduce
from decimal import Decimal, getcontext
from typing import List, Tuple, Optional


inf = float("inf")


def ceil_div(a, b):
    return (a + b - 1) // b


def isqrt(x):
    return int(sqrt(x))


def int1(s):
    return int(s) - 1


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


def input():
    return sys.stdin.readline().rstrip("\r\n")


print = lambda *args, end="\n", sep=" ": sys.stdout.write(
    sep.join(map(str, args)) + end
)


def yes(res):
    return print("Yes" if res else "No")


def YES(res):
    return print("YES" if res else "NO")


class Debug:
    def __init__(self, debug=False):
        self.debug = debug

    def get_ic(self):
        if self.debug:
            from icecream import ic

            return ic
        else:
            return lambda *args, **kwargs: ...


def pairwise(a):
    n = len(a)
    for i in range(n - 1):
        yield a[i], a[i + 1]


def factorial(n):
    return reduce(lambda x, y: x * y, range(1, n + 1))


ic = Debug(False).get_ic()


def solve(N: int, L: int, W: int, a: "List[int]"):
    pre = 0
    a.append(L)
    res = 0
    for ai in a:
        if ai > pre:

            res += ceil_div(ai - pre, W)
        pre = ai + W
    return res


def main():
    def iterate_tokens():
        for line in sys.stdin:
            for word in line.split():
                yield word

    tokens = iterate_tokens()
    N = int(next(tokens))
    L = int(next(tokens))
    W = int(next(tokens))
    a = [int(next(tokens)) for _ in range(N)]
    print(solve(N, L, W, a))


if __name__ == "__main__":
    main()
