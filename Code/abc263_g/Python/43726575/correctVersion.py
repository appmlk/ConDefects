import sys

input = sys.stdin.readline

def isPrime(x):
    p = 2
    while p * p <= x:
        if x % p == 0:
            return False
        p += 1
    return True

n = int(input())
li = [list(map(int, input().split())) for _ in range(n)]

from collections import deque
from math import inf


class Dinic:
    """
    Usage:
       mf = Dinic(n)
    -> mf.add_link(from, to, capacity)
    -> mf.max_flow(source, target)
    """

    def __init__(self, n):
        self.n = n
        self.links = [[] for _ in range(n)]

    def add_edge(self, from_, to, capacity):
        self.links[from_].append([capacity, to, len(self.links[to])])
        self.links[to].append([0, from_, len(self.links[from_]) - 1])

    def bfs(self, s):
        depth = [-1] * self.n
        depth[s] = 0
        q = deque([s])
        while q:
            v = q.popleft()
            for cap, to, rev in self.links[v]:
                if cap > 0 and depth[to] < 0:
                    depth[to] = depth[v] + 1
                    q.append(to)
        return depth

    def dfs(self, s, t, depth, progress, link_counts):
        links = self.links
        stack = [s]

        while stack:
            v = stack[-1]
            if v == t:
                break
            for i in range(progress[v], link_counts[v]):
                progress[v] = i
                cap, to, rev = links[v][i]
                if cap == 0 or depth[v] >= depth[to] or progress[to] >= link_counts[to]:
                    continue
                stack.append(to)
                break
            else:
                progress[v] += 1
                stack.pop()
        else:
            return 0

        f = 1 << 60
        fwd_links = []
        bwd_links = []
        for v in stack[:-1]:
            cap, to, rev = link = links[v][progress[v]]
            f = min(f, cap)
            fwd_links.append(link)
            bwd_links.append(links[to][rev])

        for link in fwd_links:
            link[0] -= f

        for link in bwd_links:
            link[0] += f

        return f

    def flow(self, s, t):
        link_counts = list(map(len, self.links))
        flow = 0
        while True:
            depth = self.bfs(s)
            if depth[t] < 0:
                break
            progress = [0] * self.n
            current_flow = self.dfs(s, t, depth, progress, link_counts)
            while current_flow > 0:
                flow += current_flow
                current_flow = self.dfs(s, t, depth, progress, link_counts)
        return flow

mf = Dinic(2 * n + 2)
start = 0
end = 2 * n + 1

for i in range(1, n + 1):
    mf.add_edge(start, i, li[i - 1][1])
    mf.add_edge(i + n, end, li[i - 1][1])

for i in range(n):
    for j in range(n):
        if isPrime(li[i][0] + li[j][0]):
            mf.add_edge(i + 1, j + n + 1, inf)

print(mf.flow(start, end)//2)