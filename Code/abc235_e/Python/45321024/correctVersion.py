import sys, math, bisect, heapq, copy, decimal, random, fractions
from itertools import permutations, combinations, product, accumulate, groupby
from collections import defaultdict, deque, Counter
input = lambda: sys.stdin.readline().rstrip()
sys.setrecursionlimit(10 ** 8)
"""
n = int(input())
n, m = map(int, input().split())
a = list(map(int, input().split()))
a = [list(map(int, input().split())) for _ in range(n)]
s = input()
s = list(input())
s = [input() for _ in range(n)]
"""
class UnionFind():
    def __init__(self, n):
        self.n = n
        self.parents = [-1] * n

    def find(self, x):
        if self.parents[x] < 0:
            return x
        else:
            self.parents[x] = self.find(self.parents[x])
            return self.parents[x]

    def union(self, x, y):
        x = self.find(x)
        y = self.find(y)

        if x == y:
            return

        if self.parents[x] > self.parents[y]:
            x, y = y, x

        self.parents[x] += self.parents[y]
        self.parents[y] = x

    def size(self, x):
        return -self.parents[self.find(x)]

    def same(self, x, y):
        return self.find(x) == self.find(y)

    def members(self, x):
        root = self.find(x)
        return [i for i in range(self.n) if self.find(i) == root]

    def roots(self):
        return [i for i, x in enumerate(self.parents) if x < 0]

    def group_count(self):
        return len(self.roots())

    def all_group_members(self):
        group_members = defaultdict(list)
        for member in range(self.n):
            group_members[self.find(member)].append(member)
        return group_members

    def __str__(self):
        return '\n'.join(f'{r}: {m}' for r, m in self.all_group_members().items())

##########################
n, m, q = map(int, input().split())
E = [list(map(int, input().split())) for _ in range(m)]
Q = [list(map(int, input().split())) for _ in range(q)]

for i in range(m):
    E[i][0] -= 1
    E[i][1] -= 1
for i in range(q):
    Q[i][0] -= 1
    Q[i][1] -= 1
    Q[i].append(i) #index(3)

uf = UnionFind(n)

E.sort(key=lambda x: x[2])
Q.sort(key=lambda x: x[2])
que = deque(Q)

res = [""] * q

for v, w, c in E:
    while len(que) > 0 and que[0][2] <= c:
        V, W, C, I = que.popleft()

        if uf.same(V, W):
            res[I] = "No"
        else:
            res[I] = "Yes"
            
    uf.union(v, w)

while len(que) > 0:
    V, W, C, I = que.popleft()

    if uf.same(V, W):
        res[I] = "No"
    else:
        res[I] = "Yes"


for i in res: print(i)
