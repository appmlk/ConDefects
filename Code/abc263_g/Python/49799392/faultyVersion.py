from collections import deque
import sys
sys.setrecursionlimit(1000000)
input = sys.stdin.readline

class Dinic:
  def __init__(self,V):
    self.V = V
    self.E = [[] for i in range(V)]
    self.P = [0 for i in range(V)]
  
  def add_edge(self,u,v,cap):
    self.E[u].append((v,cap,self.P[v]))
    self.E[v].append((u,0,self.P[u]))
    self.P[u] += 1
    self.P[v] += 1

  def flow(self,s,t):
    G = self.E
    P = self.P
    def bfs(s):  #始点から各頂点への最短距離をBFSで求める。
      dist = [-1 for i in range(self.V)]
      dist[s] = 0
      Q = deque()
      Q.append(s)
      while len(Q) > 0:
        u = Q.popleft()
        for v,cap,rev in G[u]:
          if cap > 0 and dist[v] < 0:
            dist[v] = dist[u] + 1
            Q.append(v)
      return dist

    def dfs(u,t,f,removed,dist):
      if u == t:
        return f
      while removed[u] < P[u]:
        v,cap,rev = G[u][removed[u]]
        if cap > 0 and dist[u] < dist[v]:
          ff = dfs(v,t,min(f,cap),removed,dist)
          if ff > 0:
            G[u][removed[u]] = (v,cap-ff,rev)
            u,Cap,Rev = G[v][rev]
            G[v][rev] = (u,Cap+ff,Rev)
            return ff
        removed[u] += 1
      return 0

    f = 0
    while True:
      dist = bfs(s)
      if dist[t] < 0:
        return f
      removed = [0 for i in range(self.V)]
      while True:
        ff = dfs(s,t,10000000000,removed,dist)
        if ff == 0:
          break
        f += ff

def Eratosthenes(N):
  is_prime = [1 for i in range(N+1)]
  is_prime[0] = is_prime[1] = 0
  P = []
  for p in range(2,N+1):
    if is_prime[p] == 0:
      continue
    P.append(p)
    for d in range(2,N//p+1):
      q = p*d
      is_prime[q] = 0
  return is_prime

inf = 10**18
N = int(input())
X,Y = [],[]
gf = Dinic(N+2)
gf_ = Dinic(N+2)
P = Eratosthenes(3*(10**7))
for i in range(N):
  a,b = map(int,input().split())
  if a % 2 == 1:
    X.append((a,b))
  else:
    Y.append((a,b))

nx = len(X)
ny = len(Y)
C = 0
for i in range(nx):
  a,b = X[i]
  gf.add_edge(0,i+1,b)
  if a == 1:
    C = b
    continue
  gf_.add_edge(0,i+1,b)
for j in range(ny):
  a,b = Y[j]
  gf.add_edge(j+nx+1,N+1,b)
  gf_.add_edge(j+nx+1,N+1,b)
for i in range(nx):
  u = i + 1
  a,b = X[i]
  for j in range(ny):
    v = j + nx + 1
    aa,bb = Y[j]
    if P[a+aa]:
      gf.add_edge(u,v,inf)
      gf_.add_edge(u,v,inf)

Fc = gf.flow(0,N+1)
F0 = gf_.flow(0,N+1)
ans = Fc + (C - (Fc - F0))//2
print(Fc)