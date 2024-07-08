import heapq

def prev(l,d,k,c,t):
  if l+d*(k-1)+c<=t: a=l+d*(k-1)
  elif t<l+c: a=-1
  else: a=l+d*((t-l)//d)
  return a

def dijkstra(P,T,v,s):
  H,T[v]=[],s
  heapq.heappush(H,(-s,v))

  while H:
    t,v=heapq.heappop(H)
    if T[v]>-t: continue
    for l,d,k,c,a in P[v]:
      s=prev(l,d,k,c,-t)
      if T[a]<s:
        T[a]=s
        heapq.heappush(H,(-s,a))

n,m=map(int,input().split())
x,P,T=0,[list() for _ in range(n)],[-1]*n
for i in range(m):
  l,d,k,c,a,b=map(int,input().split())
  P[b-1]+=[[l,d,k,c,a-1]]
  x=max(x,l+d*(k-1)+c)

dijkstra(P,T,n-1,x)
for i in range(n-1):
  print('Unreachable' if T[i]==-1 else T[i])
