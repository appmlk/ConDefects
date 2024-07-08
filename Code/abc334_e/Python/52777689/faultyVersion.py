class UnionFind():
    def __init__(self, n):
        self.par = [-1] * n
        self.rank = [0] * n
        self.siz = [1] * n
    def find(self, x):
        if self.par[x] == -1: return x 
        else:
          self.par[x] = self.find(self.par[x]) 
          return self.par[x]
    def same(self, x, y):
        return self.find(x) == self.find(y)
    def union(self, x, y):
        rx = self.find(x)
        ry = self.find(y)
        if rx == ry: return False 
        if self.rank[rx] < self.rank[ry]: 
            rx, ry = ry, rx
        self.par[ry] = rx 
        if self.rank[rx] == self.rank[ry]:
            self.rank[rx] += 1
        self.siz[rx] += self.siz[ry]
        return True
    def size(self, x):
        return self.siz[self.find(x)]
    
H, W = map(int, input().split())
vers = [[0] * W for i in range(H)]
cnt = 0
color = [list(input()) for i in range(H)]
for i in range(H):
    for j in range(W):
        vers[i][j] = cnt
        cnt += 1
        
uf = UnionFind(H*W)

for i in range(H):
    for j in range(W):
        if color[i][j] == ".":continue
        for ii, jj in [(1, 0),(-1, 0),(0, 1),(0, -1)]:
            if 0 <= i + ii < H and 0 <= j + jj < W:
                if color[i+ii][j+jj] == "#":
                    uf.union(vers[i][j], vers[i+ii][j+jj])

greenset = set()
greencnt = 0
bunbo = 0
for i in range(H):
    for j in range(W):
        if color[i][j] == "#":
            greenset.add(uf.find(vers[i][j]))
        else:
            bunbo += 1
greencnt = len(greenset)

ans = 0
for i in range(H):
    for j in range(W):
        if color[i][j] == "#":continue
        se = set()
        for ii, jj in [(1, 0),(-1, 0),(0, 1),(0, -1)]:
            if 0 <= i + ii < H and 0 <= j + jj < W:
                if color[i+ii][j+jj] == "#":
                    se.add(uf.find(vers[i+ii][j+jj]))
        ans +=  greencnt - len(se) + 1
mod = 998244353
print(ans)
print(bunbo)
print(ans * pow(bunbo, -1, mod) % mod)