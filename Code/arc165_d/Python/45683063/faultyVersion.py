import sys
input = sys.stdin.readline


def scc_decomposition(G):
    n = len(G)
    G_rev = [[] for _ in range(n)]
    for u in range(n):
        for v in G[u]:
            G_rev[v].append(u)

    # dfs
    vs = []
    visited = [False] * n
    used = [False] * n
    for u in range(n):
        if visited[u]:
            continue
        stack = [u]
        while stack:
            v = stack.pop()
            if used[v]:
                continue
            if not visited[v]:
                visited[v] = True
            else:
                vs.append(v)
                used[v] = True
                continue
            stack.append(v)
            for c in G[v]:
                if not visited[c]:
                    stack.append(c)

    # reverse dfs
    visited = [False] * n
    component = [-1] * n
    k = 0
    for u in vs[::-1]:
        if visited[u]:
            continue
        stack = [u]
        while stack:
            v = stack.pop()
            visited[v] = True
            component[v] = k
            for c in G_rev[v]:
                if not visited[c]:
                    stack.append(c)
        k += 1

    return component


class UnionFind:
    def __init__(self, N):
        self.par = [-1] * N

    def find(self, x):
        r = x
        while self.par[r] >= 0:
            r = self.par[r]
        while x != r:
            tmp = self.par[x]
            self.par[x] = r
            x = tmp
        return r

    def unite(self, x, y):
        x = self.find(x)
        y = self.find(y)
        if x == y:
            return
        if self.par[x] > self.par[y]:
            x, y = y, x
        self.par[x] += self.par[y]
        self.par[y] = x

    def same(self, x, y):
        return self.find(x) == self.find(y)

    def size(self, x):
        return -self.par[self.find(x)]


N, M = map(int, input().split())
constraints = [tuple(map(lambda x: int(x)-1, input().split()))
               for _ in range(M)]


new_constraints = []
for a, b, c, d in constraints:
    if a == c:
        if b >= d:
            print("No")
            exit()
    else:
        new_constraints.append((a, b, c, d))
constraints = new_constraints

uf = UnionFind(N)
while constraints:

    G = [[] for _ in range(N)]
    for a, b, c, d in constraints:
        G[c].append(a)
    comp = scc_decomposition(G)

    groups = [[] for _ in range(N)]
    for i in range(N):
        groups[comp[i]].append(i)
    for k in range(N):
        for j in range(len(groups[k])-1):
            uf.unite(groups[k][j], groups[k][j+1])

    new_constraints = []
    upd = False
    for a, b, c, d in constraints:
        ok = False
        if uf.same(a, c):
            upd = True
        while uf.same(a, c):
            if c == d:
                print("No")
                exit()
            if a == b:
                ok = True
                break
            a += 1
            c += 1
        if not ok:
            new_constraints.append((a, b, c, d))
    constraints = new_constraints

    if not upd:
        break

print("Yes")
