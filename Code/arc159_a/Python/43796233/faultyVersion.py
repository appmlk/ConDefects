from collections import deque

n,k=map(int,input().split())

a=[list(map(int,input().split())) for _ in range(n)]

q=int(input())

def root(s,t):
  d=deque([(s,0)])
  check=[0]*n
  if s==t:
    cnt=0
    while d:
      if cnt%100==0:
        print(cnt)
      cnt+=1
      v,w=d.popleft()
      for i in range(n):
        if a[v][i]==1:
          if i==t:
            return(w+1)
          if check[i]==0:
            check[i]=1
            d.append((i,w+1))
    return -1
  else:
    while d:
      v,w=d.popleft()
      check[v]=1
      for i in range(n):
        if a[v][i]==1:
          if i==t:
            return(w+1)
          if check[i]==0:
            check[i]=1
            d.append((i,w+1))
    return -1
  

for i in range(q):
  s,t=map(int,input().split())
  s-=1
  t-=1
  s%=n
  t%=n
  print(root(s,t))