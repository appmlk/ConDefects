from sys import setrecursionlimit, exit, stdin
from math import ceil, floor, sqrt, pi, factorial, gcd, log
from collections import Counter, deque, defaultdict
from heapq import heapify, heappop, heappush
from bisect import bisect, bisect_left, bisect_right
def iinput(): return int(input())
def imap(): return map(int, input().split())
def ilist(): return list(imap())
def sinput(): return input()
def smap(): return input().split()
def slist(): return list(smap())
MOD = 10 ** 9 + 7
#MOD = 998244353
INF = 10 ** 18

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
    
    def unite(self, x, y):
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

n,m,q = imap()
edge = [list() for _ in range(m)]
query = [list() for  _ in range(q)]
uf = UnionFind(n)

for i in range(m):
    a,b,c = imap()
    a,b = a-1, b-1
    edge[i] = [c,a,b]
for i in range(q):
    u,v,w = imap()
    u,v = u-1, v-1
    query[i] = [w,u,v,i]

edge.sort()
query.sort()

epos = 0
qpos = 0
ans = [False] * (q)

while True:
    if epos == m and qpos == q:
        break
    elif epos == m:
        w,u,v,i = query[qpos]
        if not uf.same(u,v):
            ans[i] = True
        qpos += 1
    elif qpos == q:
        break
    else:
        if edge[epos][0] < query[qpos][0]:
            c,a,b = edge[epos]
            if not uf.same(a,b):
                uf.unite(a,b)
            epos += 1
        else:
            w,u,v,i = query[qpos]
            if not uf.same(u,v):
                ans[i] = True
            qpos += 1

for i in range(q):
    if ans[i]:
        print('Yes')
    else:
        print('No')
