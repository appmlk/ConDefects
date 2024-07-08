N,M=map(int,input().split())
G=[[] for i in range(N)]
into=[0]*N
b=[0]*N
E=[[] for i in range(N)]
for i in range(M):
  s,t=map(int,input().split())
  s-=1
  t-=1
  into[t]+=1
  G[s].append(t)
  E[t].append(s)
  b[t]+=1
from collections import deque
S=deque()
keep=[0]*N
K=[[] for i in range(N+1)]
result=[0]*N
dp=[0]*N
for i in range(N):
  l,r=map(int,input().split())
  K[l].append(i)
  keep[i]=r*10**10+i
  dp[i]=r
for i in range(N):
  if b[i]==0:
    S.append(i)
while S:
  x=S.popleft()
  for y in E[x]:
    b[y]-=1
    dp[y]=min(dp[y],dp[x])
    if b[y]==0:
      S.append(y)
topo=[False]*N
ran=[False]*N
from heapq import heappush,heappop
S=[]
for i in range(N):
  if into[i]==0:
    topo[i]=True
for l in range(1,N+1):
  for y in K[l]:
    ran[y]=True
    if topo[y]==True:
      heappush(S,dp[y]*10**10+y)
  if len(S)==0:
    print('No')
    exit()
  w=heappop(S)
  r=w//(10**10)
  pos=w%(10**10)
  if r<l:
    print('No')
    exit()
  result[pos]=l
  for x in G[pos]:
    into[x]-=1
    if into[x]==0:
      topo[x]=True
      if ran[x]==True:
        heappush(S,dp[x]*10**10+x)
print('Yes')
print(*result)