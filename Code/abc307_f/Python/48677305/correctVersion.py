N,M=map(int, input().split())
E=[[] for _ in range(N+1)]
V=[-1]*(N+1)
V[0]=0;cost=[10**15]*(N+1);DD=[10**15]*(N+1)
import heapq
for i in range(M):
  a,b,t=map(int,input().split())
  E[a].append((t,b));E[b].append((t,a))

K=int(input())
A=list(map(int, input().split()))
L=int(input())
B=list(map(int, input().split()))
hq=[]
for a in A:
  E[0].append((0,a))
  E[a].append((0,0))

cost[0]=0;DD[0]=0
T=[]
for i in range(L+1):
  H=[]
  if i==0:
    heapq.heappush(hq,(0,0))
    H.append(0)
    ky=0
  else:
    ky=B[i-1]
    while T:
      c,now=heapq.heappop(T)
      if DD[now]!=c:
        continue 
      if c<=ky:
        heapq.heappush(hq,(c,now))
        H.append(now)
        cost[now]=c
      else:
        heapq.heappush(T,(c,now))
        break
  #print(i,DD,cost,hq)
  while hq:
    c,now=heapq.heappop(hq)
    if cost[now]!=c:
      continue
    for d,nex in E[now]:
      if c+d>ky:
        if DD[nex]>d:
          DD[nex]=d
          heapq.heappush(T,(d,nex))
      else:
        if cost[nex]>c+d:
          cost[nex]=c+d
          heapq.heappush(hq,(c+d,nex))
          H.append(nex)
  for h in H:
    cost[h]=0
    DD[h]=0
    V[h]=i

for v in V[1:]:
  print(v)
          