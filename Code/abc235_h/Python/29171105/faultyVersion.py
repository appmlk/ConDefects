class UnionFind():
    def __init__(self, n):
        self.n = n
        self.PA = [-1] * n
    def root(self, a):
        L = []
        while self.PA[a] >= 0:
            L.append(a)
            a = self.PA[a]
        for l in L:
            self.PA[l] = a
        return a
    def unite(self, a, b):
        ra, rb = self.root(a), self.root(b)
        if ra != rb:
            if self.PA[rb] >= self.PA[ra]:
                self.PA[ra] += self.PA[rb]
                self.PA[rb] = ra
            else:
                self.PA[rb] += self.PA[ra]
                self.PA[ra] = rb
    def same(self, a, b):
        return 1 if self.root(a) == self.root(b) else 0
    def size(self, a):
        return -self.PA[self.root(a)]
    def groups(self):
        G = [[] for _ in range(self.n)]
        for i in range(self.n):
            G[self.root(i)].append(i)
        return [g for g in G if g]
    def groups_index(self):
        G = [[] for _ in range(self.n)]
        for i in range(self.n):
            G[self.root(i)].append(i)
        cnt = 0
        GG = []
        I = [-1] * self.n
        for i in range(self.n):
            if G[i]:
                GG.append(G[i])
                I[i] = cnt
                cnt += 1
        for i in range(self.n):
            I[i] = I[self.root(i)]
        return GG, I
    def group_size(self):
        G = [[] for _ in range(self.n)]
        for i in range(self.n):
            G[self.root(i)].append(i)
        return [len(g) for g in G if g]
    def roots(self):
        return [i for i in range(self.n) if self.PA[i] < 0]

class UnionFindDict():
    def __init__(self):
        self.PA = {}
    def root(self, a):
        L = []
        while self.PA[a] >= 0:
            L.append(a)
            a = self.PA[a]
        for l in L:
            self.PA[l] = a
        return a
    def addNode(self, a):
        if a not in self.PA:
            self.PA[a] = -1
    def unite(self, a, b):
        self.addNode(a)
        self.addNode(b)
        ra, rb = self.root(a), self.root(b)
        if ra != rb:
            if self.PA[rb] >= self.PA[ra]:
                self.PA[ra] += self.PA[rb]
                self.PA[rb] = ra
            else:
                self.PA[rb] += self.PA[ra]
                self.PA[ra] = rb
    def same(self, a, b):
        self.addNode(a)
        self.addNode(b)
        return 1 if self.root(a) == self.root(b) else 0
    def size(self, a):
        self.addNode(a)
        return -self.PA[self.root(a)]
    def groups(self):
        G = {}
        for i in self.PA:
            rt = self.root(i)
            if rt in G:
                G[rt].append(i)
            else:
                G[rt] = [i]
        return {g: G[g] for g in G}
    def groups_index(self):
        G = {}
        for i in self.PA:
            rt = self.root(i)
            if rt in G:
                G[rt].append(i)
            else:
                G[rt] = [i]
        cnt = 0
        GG = []
        # I = [-1] * self.n
        I = {}
        for i in self.PA:
            if i in G:
                GG.append(G[i])
                I[i] = cnt
                cnt += 1
        for i in self.PA:
            I[i] = I[self.root(i)]
        return GG, I
    def group_size(self):
        G = [[] for _ in range(self.n)]
        for i in range(self.n):
            G[self.root(i)].append(i)
        return [len(g) for g in G if g]

P = 998244353
def convolve(a, b, k = -1):
    n0 = len(a) + len(b) - 1
    if k >= 0:
        n0 = min(n0, k)
    ret = [0] * n0
    if len(a) > len(b): a, b = b, a
    for i, aa in enumerate(a):
        for j, bb in enumerate(b):
            if i + j < n0:
                ret[i+j] = (ret[i+j] + aa * bb) % P
    return ret

import sys
input = lambda: sys.stdin.readline().rstrip()
N, M, K = map(int, input().split())
I = []
S = set()
for _ in range(M):
    a, b, c = map(int, input().split())
    a, b = a-1, b-1
    I.append((a, b, c))
    S.add(c)

SS = sorted(S)
n = len(SS)
D = {a: i for i, a in enumerate(SS)}
E = [[] for _ in range(n)]
for a, b, c in I:
    E[D[c]].append((a, b))


X = [[1, 1] for _ in range(N)]
uf = UnionFind(N)
for EE in E:
    ufd = UnionFindDict()
    for a, b in EE:
        ra = uf.root(a)
        rb = uf.root(b)
        if ra != rb:
            ufd.unite(ra, rb)
    for a, b in EE:
        ra = uf.root(a)
        rb = uf.root(b)
        uf.unite(ra, rb)

    G = ufd.groups()
    for g, L in G.items():
        nrt = uf.root(g)
        c = 0
        A = [1]
        for i in L:
            c += 1
            A = convolve(A, X[i], K + 3)
        if c < len(A):
            A[c] = (A[c] - 1) % P
        
        A[1] = (A[1] + 1) % P
        X[nrt] = A

A = [1]
for i in uf.roots():
    A = convolve(A, X[i], K + 1)
print(sum(A))