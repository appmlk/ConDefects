#!/usr/bin/env python
import os
import sys
from io import BytesIO, IOBase


def main():
    n, m = map(int,input().split())
    a = list(map(int,input().split()))
    b = list(map(int,input().split()))
    orientation = [None] * m

    graph = [[] for _ in range(n)]
    for i in range(m):
        graph[a[i] - 1].append((b[i] - 1, i))
        graph[b[i] - 1].append((a[i] - 1, i))
    dist = [-1] * n
    parent = [None] * n
    parentEdge = [None] * n
    for i in range(n):
        if dist[i] != -1:
            continue
        stack = [i]
        vertices = []
        dist[i] = 0
        while stack:
            v = stack.pop()
            if dist[v] != -1 and v != i:
                continue
            vertices.append(v)
            if parentEdge[v] is not None:
                orientation[parentEdge[v]] = (parent[v], v)
                dist[v] = dist[parent[v]] + 1
            for u, index in graph[v]:
                if parent[v] == u:
                    continue
                if dist[v] != -1:
                    stack.append(u)
                    parent[u] = v
                    parentEdge[u] = index
        for v in vertices:
            for u, index in graph[v]:
                if orientation[index] is not None:
                    continue
                orientation[index] = (u, v) if dist[u] > dist[v] else (v, u)
    ans = []
    for i in range(m):
        if orientation[i][0] == a[i]:
            ans.append("0")
        else:
            ans.append("1")
    print("".join(ans))
                


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