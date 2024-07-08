import heapq

n,m,k,l=map(int,input().split())
a=list(map(int,input().split()))
a=[0]+a
b=list(map(int,input().split()))
g=[[] for _ in range(n+1)]

for _ in range(m):
  u,v,c=map(int,input().split())
  g[u].append([v,c])
  g[v].append([u,c])

ans=[[[10**18,-1] for _ in range(2)] for _ in range(n+1)]

hq=[]

for i in range(l):
  heapq.heappush(hq,(0,b[i],a[b[i]]))
  ans[b[i]][0]=[0,a[b[i]]]
  
while hq:
  v,p,q=heapq.heappop(hq)
  bool=True
  for i in range(2):
    if ans[p][i][1]==q and ans[p][i][0]==v:
      bool=False
  if bool:
    continue
  for i,j in g[p]:
    bool=False
    for k in range(2):
      if ans[i][k][1]==q:
        bool=True
        if ans[i][k][0]>v+j:
          ans[i][k]=[v+j,q]
          if ans[i][0][0]<ans[i][1][0]:
            ans[i][0],ans[i][1]=ans[i][1],ans[i][0]
          heapq.heappush(hq,(v+j,i,q))
    if bool:
      continue
    if ans[i][0][0]>v+j:
      ans[i][1]=ans[i][0]
      ans[i][0]=[v+j,q]
      heapq.heappush(hq,(v+j,i,q))
    elif ans[i][1][0]>v+j:
      ans[i][1]=[v+j,q]
      heapq.heappush(hq,(v+j,i,q))
      
fans=[-1]*n
      
for i in range(1,n+1):
  for j in range(2):
    if ans[i][j][1]==-1:
      continue
    if ans[i][j][1]!=a[i]:
      fans[i-1]=ans[i][j][0]
      break
    
print(*fans)