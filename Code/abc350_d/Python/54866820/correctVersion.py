from collections import defaultdict
import math
import sys

sys.setrecursionlimit(790000)


class UnionFind:
    def __init__(self, N):
        self.par = list(range(N))
        self.g = [[] for i in range(N)]

    def root(self, x):
        if self.par[x] == x:
            return x
        self.par[x] = self.root(self.par[x])

        return self.par[x]

    def unite(self, x, y):
        rx = self.root(x)
        ry = self.root(y)
        self.par[rx] = ry
        self.g[x].append(y)

    def same(self, x, y):
        return self.root(x) == self.root(y)


n, m = map(int, input().split())
union = UnionFind(n)
for i in range(m):
    a, b = map(int, input().split())
    a -= 1
    b -= 1
    union.unite(a, b)

edge_nums = defaultdict(int)
node_nums = defaultdict(int)
for i in range(n):
    edge_nums[union.root(i)] += len(union.g[i])
    node_nums[union.root(i)] += 1


result = 0
for i in range(n):
    result += math.comb(node_nums[i], 2) - edge_nums[i]
print(result)
