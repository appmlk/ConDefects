from collections import defaultdict
from collections import deque
class UnionFind():
    def __init__(self, n):
        self.n = n
        self.root = [-1]*n
        self.rank = [0]*n

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
for i in range(N):
  P[i] -= 1

M = int(input())
uf = UnionFind(N)
E = []
for i in range(M):
  a,b = map(int,input().split())
  a -= 1
  b -= 1
  uf.unite(a,b)
  E.append((a,b))

R = uf.roots()
n = len(R)
V = [[] for i in range(n)]
d = {}
for i in range(n):
  d[R[i]] = i
for v in range(N):
  V[d[uf.find(v)]].append(v)

ans = []
for i in range(n):
  X = []
  for v in V[i]:
    X.append(P[v])
  X.sort()
  # 無理な時
  if X != V[i]:
    print(-1)
    exit()

  r = R[i]
  EE = []
  for j in range(M):
    u,v = E[j]
    if uf.find(u) == r:
      EE.append(j)
  if len(EE) == 0:
    continue
  
  uff = UnionFind(N)
  G = [[] for i in range(N)]
  D = [-1 for i in range(N)]
  for v in V[i]:
    D[v] = 0
  # 連結部分で木を作る
  for j in EE:
    u,v = E[j]
    if uff.same(u,v):
      continue
    else:
      uff.unite(u,v)
      G[u].append((v,j+1))
      G[v].append((u,j+1))
      D[u] += 1
      D[v] += 1
  # 次数が１のものから順に
  todo = []
  for v in V[i]:
    if D[v] == 1:
      todo.append(v)
  while len(todo):
    v = todo.pop()
    X = [-1 for i in range(N)]
    visit = deque([v])
    X[v] = 0
    if P[v] == v:
      u = v
      visit = []
    # vの位置を探す
    while len(visit):
      u = visit.popleft()
      for r,j in G[u]:
        if X[r] >= 0:
          continue
        X[r] = j
        visit.append(r)
        if P[r] == v:
          u = r
          break
      if P[u] == v:
        break
    # vにもってくる
    while u != v:
      j = X[u]
      p,q = E[j-1]
      r = p
      if p == u:
        r = q
      P[u],P[r] = P[r],P[u]
      ans.append(j)
      u = r

    for u,j in G[v]:
      G[u].remove((v,j))
      D[u] -= 1
      if D[u] == 1:
        todo.append(u)

print(len(ans))
print(*ans)