T=int(input())
for o in range(T):
  N,K=map(int,input().split())
  for i in range(70):
    if 2**i+2>N:
      k=i
      break
  result=K
  x,y,z=[K-1,K-1],[K,K],[K+1,K+1]
  Q=[x,y,z]
  if x[0]%(2**k)==1 or x[0]%(2**k)==2**(k-1)+1 or x[0]==2**(k-1)+1:
    x[1]+=1
  elif x[0]%(2**k)==2 or x[0]%(2**k)==2**(k-1)+2 or x[0]==2**(k-1)+2:
    x[1]-=1
  if y[0]%(2**k)==1 or y[0]%(2**k)==2**(k-1)+1:
    if y[1]<N:
      y[1]+=1
  elif y[0]%(2**k)==2 or y[0]%(2**k)==2**(k-1)+2 or y[0]==2**(k-1)+2:
    y[1]-=1
  if z[0]%(2**k)==1 or z[0]%(2**k)==2**(k-1)+1:
    if z[1]<N:
      z[1]+=1
  elif z[0]%(2**k)==2 or z[0]%(2**k)==2**(k-1)+2 or z[0]==2**(k-1)+2:
    z[1]-=1
  c=2**k
  if K>=2**(k-1)+1:
    p=2**(k-1)+1
  else:
    p=1
  for i in range(k-1,0,-1):
    if (N-2)%(2**i)>=2**(i-1):
      w=p+2**(i-1)
      if z[0]==w:
        z[1]=z[0]+1
      if y[0]==w:
        y[1],z[1]=z[1],y[1]
      if x[0]==w:
        x[1],y[1]=y[1],x[1]
      if x[0]==w+1:
        x[1]=x[0]-1
      if y[0]>w:
        p=w
  if N>=4:
    print(y[1])
  elif N==2:
    v=[2,1]
    print(v[K-1])
  elif N==3:
    v=[2,3,1]
    print(v[K-1])
  
