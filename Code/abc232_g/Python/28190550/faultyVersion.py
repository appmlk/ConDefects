N,M=map(int,input().split())
A=list(map(int,input().split()))
B=list(map(int,input().split()))
C=list()
for i in range(N):
  C.append([B[i]-M,i])
C.sort()
for i in range(N):
  if C[i][1]==0:
    st=i
  if C[i][1]==N-1:
    fin=i
G=[list() for i in range(N)]
for i in range(N):
  if C[i][1]==0:
    G[i].append([(i+2)%N,(C[(i+2)%N][0]-C[i][0])%M])
  else:
    G[i].append([(i+1)%N,(C[(i+1)%N][0]-C[i][0])%M])
  
K=list()
d=dict()
for i in range(N):
  K.append(-A[C[i][1]])
K.sort()
this=0
for i in range(N):
  if this==N:
    d[K[i]]=this
    continue
  while C[this][0]<K[i]:
    this+=1
    if this==N:
      break
  d[K[i]]=this
for i in range(N):
  to=d[-A[C[i][1]]]
  if to==N:
    to=0
  if C[to][1]!=0:
    G[i].append([to,(A[C[i][1]]+C[to][0])%M])
  else:
    G[i].append([(to+1)%N,(A[C[i][1]]+C[(to+1)%N][0])%M])

import heapq
INF=float('inf')
dist=[INF]*N
dist[st]=0
hq=[(0,st)]
heapq.heapify(hq)
while hq:
  #print(hq)
  cost1,a=heapq.heappop(hq)
  for to,cost in G[a]:
    if dist[to]>dist[a]+cost:
      dist[to]=dist[a]+cost
      heapq.heappush(hq,(dist[to],to))
print(dist[fin])