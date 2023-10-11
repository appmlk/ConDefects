#!/usr/bin/env python
import os
import sys
from io import BytesIO, IOBase


def main():
    n, m = map(int,input().split())
    x = list(map(int,input().split()))
    d = list(map(int,input().split()))
    arrow = [None] * (10 ** 6 + 1)
    currentMin = 1
    currentMinLoc = 1
    direction = 1
    length = 10 ** 6
    zeroDict = dict()
    for index, elem in enumerate(d):
        if length == 0:
            break
        prev_pivot = currentMinLoc - currentMin * direction
        current_pivot = prev_pivot + direction * elem
        currentEnd = currentMinLoc + (length - 1) * direction
        if (currentMinLoc - current_pivot) * direction > 0:
            currentMin = abs(currentMinLoc - current_pivot)
        elif (current_pivot - currentEnd) * direction > 0:
            direction = -direction
            currentMinLoc = currentEnd
            currentMin = abs(currentEnd - current_pivot)
        else:
            if abs(current_pivot - currentMinLoc) <= abs(currentEnd - current_pivot):
                for j in range(currentMinLoc, current_pivot + direction, direction):
                    arrow[j] = current_pivot * 2 - j
                    if arrow[j] == j:
                        zeroDict[j] = index + 1
                    length -= 1
                currentMin = 1
                currentMinLoc = current_pivot + direction
            else:
                for j in range(current_pivot, currentEnd + direction, direction):
                    arrow[j] = current_pivot * 2 - j
                    if arrow[j] == j:
                        zeroDict[j] = index + 1
                    length -= 1
                currentMin = 1
                direction = -direction
                currentMinLoc = current_pivot + direction


    graph = [[] for _ in range(10 ** 6 + 1)]
    for i in range(10 ** 6 + 1):
        if arrow[i] is not None and arrow[i] != i:
            graph[arrow[i]].append(i)
    num = [0]
    for i in range(1, 10 ** 6 + 1):
        if arrow[i] is None:
            num.append(abs(i - currentMinLoc) + currentMin)
        else:
            num.append(None)
    dist = [None] * (10 ** 6 + 1)
    for i in range(10 ** 6 + 1):
        if arrow[i] is None:
            stack = [i]
            dist[i] = 0
            while stack:
                v = stack.pop()
                for u in graph[v]:
                    if dist[u] is None:
                        dist[u] = dist[v] + 1
                        num[u] = num[v]
                        stack.append(u)
        elif arrow[i] == i:
            stack = [i]
            dist[i] = zeroDict[i]
            num[i] = 0
            while stack:
                v = stack.pop()
                for u in graph[v]:
                    if dist[u] is None:
                        dist[u] = dist[v]
                        num[u] = num[v]
                        stack.append(u)
    for elem in x:
        if num[elem] == 0:
            print("Yes " + str(dist[elem]))
        else:
            print("No " + str(num[elem] if (dist[elem] + direction) % 2 == 0 else -num[elem]))
            


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