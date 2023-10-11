n,m,t=map(int,input().split())
a=list(map(int,input().split()))
v=[0]*n
for i in range(m):
  x,y=map(int,input().split())
  v[x-1]=y
for i in range(n-1):
  t+=v[i]
  if t>i:
    t-=a[i]
  else:
    print('No')
    break
else:
  print('Yes')
    
    