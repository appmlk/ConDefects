import heapq

n,m=map(int,input().split())
g=[[] for _ in range(n+1)]

for _ in range(m):
  u,v,w=map(int,input().split())
  g[u].append([v,w])
  g[v].append([u,w])
  
k=int(input())
a=list(map(int,input().split()))
ans=[-1]*(n+1)
d=[10**18]*(n+1)
hq=[]
new=[]
for i in range(k):
  ans[a[i]]=0
  d[a[i]]=0
  new.append(a[i])
  
k=int(input())
a=list(map(int,input().split()))

for i in range(k):
  while new:
    v=new.pop()
    for j,w in g[v]:
      if d[j]>w:
        d[j]=w
        heapq.heappush(hq,(w,j))
  hq2=[]
  new=[]
  s=set()
  while hq:
    w,j=hq[0]
    if w!=d[j]:
      heapq.heappop(hq)
      continue
    if w<=a[i]:
      heapq.heappush(hq2,(w,j))
      heapq.heappop(hq)
      d[j]=w
    else:
      break
  while hq2:
    w,j=heapq.heappop(hq2)
    if d[j]<w:
      continue
    s.add(j)
    d[j]=0
    for q,ww in g[j]:
      if ww+w<=a[i] and ww+w<d[q]:
        heapq.heappush(hq2,(ww+w,q))
        d[q]=ww+w
        
  for j in s:
    if ans[j]==-1:
      ans[j]=i+1
    new.append(j)
    
for i in range(1,n):
  print(ans[i])