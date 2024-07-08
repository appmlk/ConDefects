from collections import defaultdict

class UnionFind():
    def __init__(self, n):
        self.n = n
        self.root = [-1]*(n+1)
        self.rank = [0]*(n+1)

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

N = int(input())
P = list(map(int,input().split()))
Q = list(map(int,input().split()))

mod = 998244353
dp = [[[0,0],[0,0]] for i in range(N+1)]
dp[1][0][0] = 1
dp[1][1][1] = 1
for i in range(1,N):
  dp[i+1][0][0] = dp[i][0][1]
  dp[i+1][0][1] = sum(dp[i][0]) % mod
  dp[i+1][1][0] = dp[i][1][1]
  dp[i+1][1][1] = sum(dp[i][1]) % mod

C = [0,1]
for i in range(2,N+1):
  c = sum(dp[i-1][0]) + sum(dp[i-1][1]) + dp[i-1][1][1]
  c %= mod
  C.append(c)

uf = UnionFind(N)
for i in range(N):
  uf.unite(P[i]-1,Q[i]-1)

d = {}
for i in range(N):
  r = uf.find(i)
  if r not in d:
    d[r] = 0
  d[r] += 1

ans = 1
for r in d:
  ans *= C[d[r]]
  ans %= mod
print(ans)