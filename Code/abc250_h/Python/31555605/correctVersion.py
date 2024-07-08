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
        return {r: self.members(r) for r in self.roots()}

    def __str__(self):
        return '\n'.join('{}: {}'.format(r, self.members(r)) for r in self.roots())

from heapq import heapify, heappop as pop, heappush as push
N, M, K = map(int, input().split())
edge = [[] for _ in range(N)]
for _ in range(M):
    a, b, c = map(int, input().split())
    a -= 1; b -= 1
    edge[a].append((c, b))
    edge[b].append((c, a))

dist = [10**18]*N
visited = [False]*N
for i in range(K):
    dist[i] = 0
q = [(0, i) for i in range(K)]
heapify(q)

while q:
    d, n = pop(q)
    if visited[n]:
        continue
    visited[n] = True
    for ed, e in edge[n]:
        if visited[e]:
            continue
        if dist[n]+ed < dist[e]:
            dist[e] = dist[n]+ed
            push(q, (dist[e], e))

new_edges = []
for i, temp in enumerate(edge):
    for d, j in temp:
        new_edges.append((dist[i]+dist[j]+d, i, j))
new_edges.sort()
new_edges.append((10**20, -1, -1))

Q = int(input())
query = []
for i in range(Q):
    x, y, t = map(int, input().split())
    query.append((t, x-1, y-1, i))
query.sort()

ans = [None]*Q

uf = UnionFind(N)
next_edge_idx = 0
for t, x, y, i in query:
    while new_edges[next_edge_idx][0] <= t:
        d, a, b = new_edges[next_edge_idx]
        uf.union(a, b)
        next_edge_idx += 1
    if uf.same(x, y):
        ans[i] = "Yes"
    else:
        ans[i] = "No"

print(*ans, sep="\n")