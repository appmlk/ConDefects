class UnionFind:
    def __init__(self, n):
        self.parent = list(range(n))
        self.count = n

    def find(self, i):
        if self.parent[i] == i:
            return i
        self.parent[i] = self.find(self.parent[i])
        return self.parent[i]

    def unite(self, i, j):
        i, j = self.find(i), self.find(j)
        if i == j:
            return
        if i > j:
            i, j = j, i
        self.parent[j] = i
        self.count -= 1

    def same(self, i, j):
        return self.find(i) == self.find(j)


MOD = 998244353
H, W = map(int, input().split())
S = [input() for _ in range(H)]
uf = UnionFind(H * W)
dot = 0
for i in range(H):
    for j in range(W):
        if S[i][j] == ".":
            dot += 1
            continue
        if i + 1 < H and S[i + 1][j] == "#":
            uf.unite(i * W + j, (i + 1) * W + j)
        if j + 1 < W and S[i][j + 1] == "#":
            uf.unite(i * W + j, i * W + j + 1)
num = 0
den = 0
cnt = uf.count - dot
for i in range(H):
    for j in range(W):
        if S[i][j] == "#":
            continue
        den += 1
        s = set()
        if i - 1 >= 0 and S[i - 1][j] == "#":
            s.add(uf.find((i - 1) * W + j))
        if i + 1 < H and S[i + 1][j] == "#":
            s.add(uf.find((i + 1) * W + j))
        if j - 1 >= 0 and S[i][j - 1] == "#":
            s.add(uf.find(i * W + j - 1))
        if j + 1 < W and S[i][j + 1] == "#":
            s.add(uf.find(i * W + j + 1))
        num += cnt + 1 - len(s)
print(num * pow(den, MOD - 2, MOD) % MOD)
