from collections import defaultdict

def pow2(x, n,mod):
    ans = 1
    while n:
        if n % 2:
            ans *= x
            ans %= mod
        x *= x
        x %= mod
        n >>= 1
    return ans

class UnionFind():

    def __init__(self, n):
        self.n = n
        self.root = [-1]*(n)
        self.rank = [0]*(n)

    def find(self, x):
        if(self.root[x] < 0):
            return x
        else:
            self.root[x] = self.find(self.root[x])
            return self.root[x]

    def unite(self, x, y):
        x = self.find(x)
        y = self.find(y)

        if(x == y):
            return
        elif(self.rank[x] > self.rank[y]):
            self.root[x] += self.root[y]
            self.root[y] = x
        else:
            self.root[y] += self.root[x]
            self.root[x] = y
            if(self.rank[x] == self.rank[y]):
                self.rank[y] += 1

    def same(self, x, y):
        return self.find(x) == self.find(y)

    def size(self, x):
        return -self.root[self.find(x)]

    def roots(self):
        return [i for i, x in enumerate(self.root) if x < 0]

    def group_size(self):
        return len(self.roots())

    def group_members(self):
        group_members = defaultdict(list)
        for member in range(self.n):
            group_members[self.find(member)].append(member)
        return group_members
N,M=map(int,input().split())
P=list(map(int,input().split()))
mod=998244353
uf=UnionFind(N)

for i in range(N):
  uf.unite(i,P[i]-1)

print((pow2(M,N,mod)-pow2(M,uf.group_size(),mod))*(pow(2,mod-2,mod)*1)%mod)
