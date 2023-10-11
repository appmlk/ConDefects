#!/usr/bin/env python
import os
import sys
from io import BytesIO, IOBase


def main():
    n = int(input())
    a = list(map(int,input().split()))
    hull = [(0, a[0]), (1, a[1])]
    for i in range(2, n):
        nextv = (i, a[i])
        while True:
            v1 = hull[-1]
            v2 = hull[-2]
            grad1 = (v1[1] - v2[1]) // (v1[0] - v2[0])
            grad2 = (nextv[1] - v1[1]) // (nextv[0] - v1[0])
            if grad2 <= grad1:
                hull.pop()
                if len(hull) < 2:
                    break
            else:
                break
        hull.append(nextv)
    ans = 0
    for i in range(len(hull) - 1):
        distance = hull[i + 1][0] - hull[i][0]
        height_difference = abs(hull[i + 1][1] - hull[i][1])
        grad_min = height_difference // distance
        grad_min_dur = height_difference % distance
        p = min(hull[i][1], hull[i + 1][1])
        for j in range(distance + 1):
            ans += p
            if j < distance - grad_min_dur:
                p += grad_min
            else:
                p += grad_min + 1
        ans -= hull[i + 1][1]
    ans += hull[-1][1]
    print(ans)

        





# region fastio

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

# endregion

if __name__ == "__main__":
    main()