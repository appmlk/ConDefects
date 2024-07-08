import os,sys
from io import BytesIO, IOBase
sys.setrecursionlimit(10**6)
from typing import *
# only use pypyjit when needed, it usese more memory, but speeds up recursion in pypy
# import pypyjit
# pypyjit.set_param('max_unroll_recursion=-1')
# sys.stdout = open('output.txt', 'w')

# Fast IO Region
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

from collections import deque
from heapq import heappush, heappop

def main():
    n, m = map(int, input().split())
    indegrees = [0] * (n + 1)
    outdegrees = [0] * (n + 1)
    adj_list = [[] for _ in range(n + 1)]
    rev_adj_list = [[] for _ in range(n + 1)]
    for _ in range(m):
        u, v = map(int, input().split())
        adj_list[u].append(v)
        rev_adj_list[v].append(u)
        indegrees[v] += 1
        outdegrees[u] += 1
    print('indegrees', indegrees)
    lranges, rranges = [0] * (n + 1), [0] * (n + 1)
    for i in range(1, n + 1):
        left, right = map(int, input().split())
        lranges[i], rranges[i] = left, right
    # reverse topological sort
    queue = deque()
    for i in range(1, n + 1):
        if outdegrees[i] == 0:
            queue.append(i)
    while queue:
        node = queue.popleft()
        for nei in rev_adj_list[node]:
            outdegrees[nei] -= 1
            rranges[nei] = min(rranges[nei], rranges[node] - 1)
            if outdegrees[nei] == 0:
                queue.append(nei)
    # topological sort
    res = [0] * (n + 1)
    p = 1
    ready, not_ready = [], []
    for i in range(1, n + 1):
        if indegrees[i] == 0:
            if lranges[i] > p: heappush(not_ready, (lranges[i], i))
            else: heappush(ready, (rranges[i], i))
    while ready:
        # print('ready', ready, 'not_ready', not_ready)
        right, node = heappop(ready)
        if p > right: 
            print("No")
            return
        res[node] = p
        # print('node', node, 'p', p)
        p += 1
        while not_ready and not_ready[0][0] <= p:
            _, n1 = heappop(not_ready)
            heappush(ready, (rranges[n1], n1))
        for nei in adj_list[node]:
            indegrees[nei] -= 1
            if indegrees[nei] == 0:
                if lranges[nei] > p: heappush(not_ready, (lranges[nei], nei))
                else: heappush(ready, (rranges[nei], nei))
    if p <= n: 
        print("No")
        return
    print("Yes")
    print(*res[1:])

if __name__ == '__main__':
    main()