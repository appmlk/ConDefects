import cProfile
import math
import sys
import io
import os
import traceback
from bisect import bisect_left, bisect_right
from collections import deque
from functools import lru_cache
from itertools import accumulate


# region IO
BUFSIZE = 8192


class FastIO(io.IOBase):
    newlines = 0

    def __init__(self, file):
        self._file = file
        self._fd = file.fileno()
        self.buffer = io.BytesIO()
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


class IOWrapper(io.IOBase):
    def __init__(self, file):
        self.buffer = FastIO(file)
        self.flush = self.buffer.flush
        self.writable = self.buffer.writable
        self.write = lambda s: self.buffer.write(s.encode("ascii"))
        self.read = lambda: self.buffer.read().decode("ascii")
        self.readline = lambda: self.buffer.readline().decode("ascii")


def print(*args, **kwargs):
    """Prints the values to a stream, or to sys.stdout by default."""
    sep, file = kwargs.pop("sep", " "), kwargs.pop("file", sys.stdout)
    at_start = True
    for x in args:
        if not at_start:
            file.write(sep)
        file.write(str(x))
        at_start = False
    file.write(kwargs.pop("end", "\n"))
    if kwargs.pop("flush", False):
        file.flush()


sys.stdin, sys.stdout = IOWrapper(sys.stdin), IOWrapper(sys.stdout)


def input(): return sys.stdin.readline().rstrip('\r\n')


def read_int_list():
    return list(map(int, input().split()))


def read_int_tuple():
    return tuple(map(int, input().split()))


def read_int():
    return int(input())


# endregion

# region local test
if 'AW' in os.environ.get('COMPUTERNAME', ''):
    test_no = 1
    # f = open(os.path.dirname(__file__) + f'\\in{test_no}.txt', 'r')
    file = open('inputs')
    def input():
        return file.readline().rstrip("\r\n")
# endregion

MOD = 998244353

def solve():
    n, m = read_int_tuple()
    p = m + 1
    pp = p * p
    ppp = pp * p

    dp = [0] * ppp
    dp[-1] = 1

    for _ in range(n):
        np = [0] * ppp
        for a in range(p):
            for b in range(p):
                for c in range(p):
                    ft = a * pp + b * p + c
                    if dp[ft] == 0: continue
                    for x in range(m):
                        if x > c: continue
                        if x <= a:
                            tt = x * pp + b * p + c
                        elif x <= b:
                            tt = a * pp + x * p + c
                        else:
                            tt = a * pp + b * p + x
                        # else:
                        #     continue
                        np[tt] += dp[ft]
                        np[tt] %= MOD
        dp = np
    print(sum(dp[a * pp + b * p + c] for a in range(m) for b in range(a + 1, m) for c in range(b + 1, m)))

T = 1
for t in range(T):
    solve()

