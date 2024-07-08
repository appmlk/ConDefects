import sys
ni = lambda :int(input())
na = lambda :list(map(int,input().split()))
yes = lambda :print("yes");Yes = lambda :print("Yes");YES = lambda : print("YES")
no = lambda :print("no");No = lambda :print("No");NO = lambda : print("NO")
#######################################################################

from collections import defaultdict
 
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
n, m = na()
e = []
for i in range(m):
    u,v = na()
    u-=1
    v-=1
    e.append((u,v))

k = ni()
x = [i-1 for i in na()]
xx = [0] * m
for i in range(k):
    xx[x[i]] = 1
uf = UnionFind(n)
deg = [0] * n
for i in range(m):
    u,v = e[i]
    if xx[i] ^ 1:
        uf.union(u,v)
    else:
        deg[u]^=1
        deg[v]^=1


d = uf.all_group_members()
ans = 0
for i in d:
    r = 0
    for j in d[i]:
        r ^= deg[j]
    #print(d[i], r)
    ans += r
if ans <= 2:
    Yes()
else:
    No()
