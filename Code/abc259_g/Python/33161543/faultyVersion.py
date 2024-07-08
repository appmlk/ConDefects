from collections import deque
from math import inf
import sys
import io
import os

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
def read_int_list(): return list(map(int, input().split()))
def read_int_tuple(): return tuple(map(int, input().split()))
def read_int(): return int(input())

# endregion


if 'AW' in os.environ.get('COMPUTERNAME', ''):
    test_no = 1
    f = open(os.path.dirname(__file__) + f'\\in{test_no}.txt', 'r')
    def input(): return f.readline().rstrip("\r\n")


class FordFulkerson:
    def __init__(self, edges, source_node, end_node, max_node_num, max_edge_num):
        self.edges = edges
        self.source_node = source_node
        self.end_node = end_node
        self.max_edge_num = max_edge_num
        self.max_node_num = max_node_num

    def getMaxFlow(self):
        e = [-1] * (self.max_edge_num*2 + 1)
        f = [-1] * (self.max_edge_num*2 + 1)
        ne = [-1] * (self.max_edge_num*2 + 1)
        h = [-1] * (self.max_node_num + 1)
        dis = [-1] * (self.max_node_num + 1)
        cur = [-1] * (self.max_node_num + 1)

        idx = 0
        for a, b, w in self.edges:
            e[idx], f[idx], ne[idx], h[a] = b, w, h[a], idx
            idx += 1
            e[idx], f[idx], ne[idx], h[b] = a, 0, h[b], idx
            idx += 1

        def bfs() -> bool:
            for i in range(self.max_node_num + 1):
                dis[i] = -1

            que = deque()
            que.append(self.source_node)
            dis[self.source_node] = 0
            cur[self.source_node] = h[self.source_node]

            while len(que) > 0:
                cur_node = que.popleft()
                idx = h[cur_node]
                while idx != -1:
                    next_node = e[idx]
                    if dis[next_node] == -1 and f[idx] > 0:
                        dis[next_node] = dis[cur_node] + 1
                        cur[next_node] = h[next_node]
                        if next_node == self.end_node:
                            return True

                        que.append(next_node)

                    idx = ne[idx]

            return False

        def dfs(node, limit) -> int:
            if node == self.end_node:
                return limit

            flow = 0
            idx = cur[node]
            while idx != -1 and flow < limit:
                cur[node] = idx

                next_node = e[idx]
                if dis[next_node] == dis[node]+1 and f[idx] > 0:
                    t = dfs(next_node, min(f[idx], limit - flow))
                    if t == 0:
                        dis[next_node] = -1

                    f[idx], f[idx ^ 1], flow = f[idx]-t, f[idx ^ 1]+t, flow+t

                idx = ne[idx]

            return flow

        max_flow = 0
        while bfs():
            max_flow += dfs(self.source_node, 0x7fffffff)
        return max_flow


def solve(m, n, nums):
    S, T = m + n, m + n + 1
    edges, res = [], 0
    row_neg, col_neg = [0] * m, [0] * n
    
    

    for i in range(m):
        for j in range(n):
            if nums[i][j] >= 0:
                edges.append((i, m + j, nums[i][j]))
                res += nums[i][j]
            else:
                edges.append((m + j, i, inf))
                row_neg[i] -= nums[i][j]
                col_neg[j] -= nums[i][j]
    
    for i in range(m):
        edges.append((S, i, row_neg[i]))
        
    for j in range(n):
        edges.append((m + j, T, col_neg[j]))

    gf = FordFulkerson(edges, S, T, T, len(edges))
    flow = gf.getMaxFlow()
    # print(res, flow)
    print(res - flow)


for _ in range(1):
    m, n = read_int_tuple()
    nums = [read_int_list() for _ in range(m)]
    print(solve(m, n, nums))
