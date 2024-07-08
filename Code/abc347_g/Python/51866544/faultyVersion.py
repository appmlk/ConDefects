#!/usr/bin/env python
import os
import sys
from io import BytesIO, IOBase

from random import random, randint


def main():
    n = int(input())
    a = [list(map(int,input().split())) for _ in range(n)]
    fixed = [[a[i][j] != 0 for j in range(n)] for i in range(n)]

    minAns = float("inf")
    p2 = []
    for _ in range(700):
        ans = 0
        for i in range(n):
            for j in range(n):
                if fixed[i][j] == 0:
                    a[i][j] = randint(1, 5)
        
        for t in range(80):
            for i in range(n):
                for j in range(n):
                    cnt = 0
                    p = 0
                    if fixed[i][j]:
                        continue
                    if i > 0:
                        cnt += 1
                        p += a[i - 1][j]
                    if j > 0:
                        cnt += 1
                        p += a[i][j - 1]
                    if i < n - 1:
                        cnt += 1
                        p += a[i + 1][j]
                    if j < n - 1:
                        cnt += 1
                        p += a[i][j + 1]
                    if cnt != 0:
                        if random() < 0.5:
                            a[i][j] = round((p - 0.000001) / cnt)
                        else:
                            a[i][j] = round((p + 0.000001) / cnt)
        
        for i in range(n):
            for j in range(n - 1):
                ans += (a[i][j] - a[i][j + 1]) ** 2
        for i in range(n - 1):
            for j in range(n):
                ans += (a[i + 1][j] - a[i][j]) ** 2
        if ans < minAns:
            minAns = ans
            p2 = []
            for i in range(n):
                p2.append(tuple(a[i]))

    for i in range(n):
        print(" ".join(map(str, p2[i])))

# region fastio

BUFSIZE = 8192


class FastIO(IOBase):
    newlines = 0

    def __init__(self, file):
        self._file = file
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

# endregion

if __name__ == "__main__":
    main()