import sys
input = sys.stdin.readline
# Dinic's algorithm
from collections import deque
class Dinic:
    def __init__(self, N):
        self.N = N
        self.G = [[] for i in range(N)]

    def add_edge(self, fr, to, cap):
        forward = [to, cap, None]
        forward[2] = backward = [fr, 0, forward]
        self.G[fr].append(forward)
        self.G[to].append(backward)

    def add_multi_edge(self, v1, v2, cap1, cap2):
        edge1 = [v2, cap1, None]
        edge1[2] = edge2 = [v1, cap2, edge1]
        self.G[v1].append(edge1)
        self.G[v2].append(edge2)

    def bfs(self, s, t):
        self.level = level = [None]*self.N
        deq = deque([s])
        level[s] = 0
        G = self.G
        while deq:
            v = deq.popleft()
            lv = level[v] + 1
            for w, cap, _ in G[v]:
                if cap and level[w] is None:
                    level[w] = lv
                    deq.append(w)
        return level[t] is not None

    def dfs(self, v, t, f):
        if v == t:
            return f
        level = self.level
        for e in self.it[v]:
            w, cap, rev = e
            if cap and level[v] < level[w]:
                d = self.dfs(w, t, min(f, cap))
                if d:
                    e[1] -= d
                    rev[1] += d
                    return d
        return 0

    def flow(self, s, t):
        flow = 0
        INF = 10**9 + 7
        G = self.G
        while self.bfs(s, t):
            *self.it, = map(iter, self.G)
            f = INF
            while f:
                f = self.dfs(s, t, INF)
                flow += f
        return flow
n,m=map(int,input().split())
S=[]
for i in range(n):
  s=input().rstrip()
  S.append(s)
idx=[]
for i in range(n):
  l=[[] for _ in range(10)]
  for j in range(m):
    v=int(S[i][j])
    l[v].append(j)
  idx.append(l)
INF=10**9
ans=INF
from collections import defaultdict
for i in range(10):
  flg=True
  for j in range(n):
    if len(idx[j][i])==0:
      flg=False
      break
  if not flg:
    continue
  ng=0
  ok=n*m
  while ok-ng>1:
    mid=(ok+ng)//2
    inv=defaultdict(list)
    for j in range(n):
      cnt=0
      le=len(idx[j][i])
      while cnt<n:
        val=idx[j][i][cnt%le]+(cnt//le)*m
        if val>mid:
          break
        inv[val].append(j)
        cnt+=1
    size=len(inv)+n+10
    dinic = Dinic(size)
    for j in range(n):
      dinic.add_edge(0,j+1,1)
    fin=size-1
    now=n+1
    for j in inv:
      dinic.add_edge(now,fin,1)
      for k in inv[j]:
        dinic.add_edge(k+1,now,1)
      now+=1
    fl=dinic.flow(0,fin)
    if fl>=n:
      ok=mid
    else:
      ng=mid
  ans=min(ans,ok)
if ans==INF:
  print(-1)
else:
  print(ans)