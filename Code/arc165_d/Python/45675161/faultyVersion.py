class UnionFind:
    def __init__(self, n):
        self.n = n
        self.par = [-1] * n
        self.group_ = n

    def find(self, x):
        if self.par[x] < 0:
            return x
        lst = []
        while self.par[x] >= 0:
            lst.append(x)
            x = self.par[x]
        for y in lst:
            self.par[y] = x
        return x

    def unite(self, x, y):
        x = self.find(x)
        y = self.find(y)
        if x == y:
            return False

        if self.par[x] > self.par[y]:
            x, y = y, x

        self.par[x] += self.par[y]
        self.par[y] = x
        self.group_ -= 1
        return True

    def size(self, x):
        return -self.par[self.find(x)]

    def same(self, x, y):
        return self.find(x) == self.find(y)

    @property
    def group(self):
        return self.group_


class SCC:
    def __init__(self, n, edges=None):
        self.n = n
        if edges is None:
            self.edges = []
        else:
            self.edges = edges

    def add_edge(self, u, v):
        self.edges.append((u, v))

    def read_edges(self, m, indexed=1):
        for _ in range(m):
            u, v = map(int, input().split())
            u -= indexed
            v -= indexed
            self.add_edge(u, v)

    def build(self):
        start = [0] * (self.n + 1)
        elist = [0] * len(self.edges)
        for u, _ in self.edges:
            start[u + 1] += 1
        for i in range(1, self.n + 1):
            start[i] += start[i - 1]

        counter = start[:]
        for u, v in self.edges:
            elist[counter[u]] = v
            counter[u] += 1

        now_ord = 0
        group_num = 0
        visited = []
        low = [0] * self.n
        ord = [-1] * self.n
        ids = [0] * self.n
        bpos = [-1] * self.n

        def dfs(v):
            nonlocal now_ord, group_num

            visited.append(v)
            stack = [~v, v]
            while stack:
                pos = stack.pop()
                if pos >= 0:
                    if ord[pos] == -1:
                        low[pos] = ord[pos] = now_ord
                        now_ord += 1
                        visited.append(pos)
                        for i in range(start[pos], start[pos + 1]):
                            to = elist[i]
                            if ord[to] == -1:
                                stack.append(~to)
                                stack.append(to)
                                bpos[to] = pos
                            else:
                                low[pos] = min(low[pos], ord[to])
                else:
                    pos = ~pos
                    if low[pos] == ord[pos]:
                        while 1:
                            u = visited.pop()
                            ord[u] = self.n
                            ids[u] = group_num
                            if u == pos:
                                break
                        group_num += 1
                    if bpos[pos] != -1:
                        low[bpos[pos]] = min(low[bpos[pos]], low[pos])

        for i in range(self.n):
            if ord[i] == -1:
                dfs(i)

        for i in range(self.n):
            ids[i] = group_num - 1 - ids[i]

        return group_num, ids


n, m = map(int, input().split())
A = [0] * m
B = [0] * m
C = [0] * m
D = [0] * m
for i in range(m):
    A[i], B[i], C[i], D[i] = map(int, input().split())
    A[i] -= 1
    B[i] -= 1
    C[i] -= 1
    D[i] -= 1

nex = [0] * m
flg = True
UF = UnionFind(n)

while flg:
    flg = False
    G = SCC(n)
    for i in range(m):
        while UF.same(A[i] + nex[i], C[i] + nex[i]):
            if C[i] + nex[i] == D[i]:
                print("No")
                exit()
            elif A[i] + nex[i] == B[i]:
                break
            nex[i] += 1
        if not UF.same(A[i] + nex[i], C[i] + nex[i]):
            G.add_edge(A[i] + nex[i], C[i] + nex[i])

    _, res = G.build()
    ind = [-1] * (max(res) + 1)
    for i, r in enumerate(res):
        if ind[r] == -1:
            ind[r] = i
        else:
            flg = True
            UF.unite(ind[r], i)

print("Yes")
