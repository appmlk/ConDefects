N, M, v = map(int,input().split())
I = [[] for _ in range(2*N)]
R = [[] for _ in range(2*N)]
D = [0] * (2*N)
for _ in range(M):
  a, b, c = map(int,input().split())
  a -= 1
  b -= 1
  I[a].append((b + N, c))
  I[a + N].append((b, c))
  R[b].append((a + N, c))
  R[b + N].append((a, c))
  D[a] += 1
  D[a+N] += 1
  
inf = 1 << 60
cost = [inf] * (2*N)
from heapq import heappop, heappush
task = []
for i, d in enumerate(D):
  if d == 0:
    heappush(task, (0, i))
    
vis = [False] * (2*N)
vol = [0] * (2*N)
while task:
  c, p = heappop(task)
  if vis[p]: continue
  cost[p] = c
  vis[p] = True
  
  for q, c0 in R[p]:
    if q < N:
      D[q] -= 1
      vol[q] = max(c + c0, D[q])
      if D[q] == 0:
        heappush(task, (vol[q], q))
    
    else:
      heappush(task, (c + c0, q))
      
print(cost[v-1 + N] if cost[v-1 + N] < inf else "INFINITY")
    
    
  
  
    
    
  
  
  