mod = 998244353
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

N = int(input())
a = list(map(int,input().split()))
uf = UnionFind(N)
for i in range(N):
    if a[i] > 0:
        a[i] -= 1
        uf.union(i,a[i])
d = uf.all_group_members()
p = []
q = 0
ans = 0
n = 0
#print(d)
for x in d.keys():
    flag = False
    for y in d[x]:
        if a[y] == -1:
            flag = True
    if flag:
        n += 1
        p.append(len(d[x]))
    else:
        ans += 1
        q += len(d[x])
inv = pow(N,mod-2,mod)
#print(inv)
#print(n,p,q,ans)

fct = [1]
for i in range(1,n+1):
    fct.append((fct[-1]*i)%mod)
#print(fct)
for i in range(n):
    p[i] *= inv
    p[i] %= mod
#print(ans)

dp = [[0]*(n+1) for i in range(n+1)]
dp[0][0] = 1

for i in range(1,n+1):
    dp[i][0] = 1
    for k in range(1,i+1):
        dp[i][k] += dp[i-1][k]
        dp[i][k] %= mod
        dp[i][k] += (dp[i-1][k-1]*p[i-1])%mod
        dp[i][k] %= mod
    #print(dp[i])
for k in range(1,n+1):
    ans += (dp[-1][k]*fct[k-1])%mod
    ans %= mod
for _ in range(n):
    ans *= N
    ans %= mod
print(ans)