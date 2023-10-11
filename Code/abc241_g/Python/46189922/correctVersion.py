from collections import deque
 
 
class Dinic:
    def __init__(self, n):
        self.n = n
        self.links = [[] for _ in range(n)]
        self.depth = None
        self.progress = None
 
    def add_link(self, _from, to, cap):
        self.links[_from].append([cap, to, len(self.links[to])])
        self.links[to].append([0, _from, len(self.links[_from]) - 1])
 
    def bfs(self, s):
        depth = [-1] * self.n
        depth[s] = 0
        q = deque([s])
        while q:
            v = q.popleft()
            for cap, to, rev in self.links[v]:
                if cap > 0 and depth[to] < 0:
                    depth[to] = depth[v] + 1
                    q.append(to)
        self.depth = depth
 
    def dfs(self, v, t, flow):
        if v == t:
            return flow
        links_v = self.links[v]
        for i in range(self.progress[v], len(links_v)):
            self.progress[v] = i
            cap, to, rev = link = links_v[i]
            if cap == 0 or self.depth[v] >= self.depth[to]:
                continue
            d = self.dfs(to, t, min(flow, cap))
            if d == 0:
                continue
            link[0] -= d
            self.links[to][rev][0] += d
            return d
        return 0
 
    def max_flow(self, s, t):
        flow = 0
        while True:
            self.bfs(s)
            if self.depth[t] < 0:
                return flow
            self.progress = [0] * self.n
            current_flow = self.dfs(s, t, float('inf'))
            while current_flow > 0:
                flow += current_flow
                current_flow = self.dfs(s, t, float('inf'))
N,M=map(int,input().split())
win=[0]*(N+1)
lose=[0]*(N+1)
used={}
for i in range(M):
  a,b=map(int,input().split())
  win[a]+=1
  lose[b]+=1
  if a>b:
    a,b=b,a
  used[a*N+b]=1
x=max(win)
result=[]
count=N*(N-1)//2-M
z=sum(win)
for i in range(1,N+1):
  rest=N-1-win[i]-lose[i]
  y=win[i]+rest
  x=0
  for j in range(1,N+1):
    if j==i:
      continue
    x=max(x,win[j])
  if y<=x:
    continue
  r=N**2
  Z=Dinic(r+N+2)
  e=0
  for p in range(1,N):
    for q in range(p+1,N+1):
      if p==i or q==i:
        continue
      if p*N+q in used:
        continue
      Z.add_link(0,p*N+q,1)
      e+=1
      Z.add_link(p*N+q,r+p,1)
      Z.add_link(p*N+q,r+q,1)
  for p in range(1,N+1):
    Z.add_link(r+p,r+N+1,y-1-win[p])
  c=Z.max_flow(0,r+N+1)
  if c==e:
    result.append(i)
print(*result)