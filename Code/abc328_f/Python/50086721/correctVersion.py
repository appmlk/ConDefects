import sys
sys.setrecursionlimit(10 ** 7)
class UnionFind():
    def __init__(self, n):
        self.n = n
        self.parents = [-1] * n
        self.val = [0] * n

    def find(self, x):
        if self.parents[x] < 0:
            return x
        else:
            t = self.parents[x]
            self.parents[x] = self.find(t)
            self.val[x] = self.val[x] + self.val[t]
            return self.parents[x]

    def union(self, x, y, d): #[x] = [y] + d
        x2 = self.find(x)
        y2 = self.find(y)

        if x2 == y2:
            return

        if self.parents[x2] > self.parents[y2]:
            x2, y2 = y2, x2
            x,y = y,x
            d *= -1

        self.parents[x2] += self.parents[y2]
        self.parents[y2] = x2
        self.val[y2] = self.v(x) + d - self.v(y)
        
    def v(self,x):
        s = 0
        while x >= 0:
            s += self.val[x]
            x = self.parents[x]
        return s
n,q = map(int,input().split())
uf = UnionFind(n)
ans = []
for i in range(q):
    a,b,d = map(int,input().split())
    a -= 1
    b -= 1
    if uf.find(a) != uf.find(b):
        uf.union(a,b,d)
        ans.append(str(i + 1))
    else:
        if uf.v(b) - uf.v(a) == d:
            ans.append(str(i + 1))
print(" ".join(ans))