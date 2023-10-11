N,Q=map(int,input().split())
sosuu=[]
v=[0]*(10**6+1)
for i in range(2,10**6):
  if v[i]==1:
    continue
  for j in range(2,10**6):
    if i*j>10**6:
      break
    v[i*j]=1
for x in range(2,10**6+1):
  if v[x]==0:
    sosuu.append(x)
G=[[] for i in range(10**6+1)]
for x in sosuu:
  for j in range(1,10**6):
    if x*j>10**6:
      break
    count=0
    n=x*j
    while n%x==0:
      n//=x
      count+=1
    count%=3
    if count>0:
      G[x*j].append((x,count))
v=[0]*(N+1)
v2=[0]*(N+1)
mod=998244353
x10=[1]*(10**6+1)
y59=[1]*(10**6+1)
mody=978902437
for i in range(1,10**6+1):
  x10[i]=x10[i-1]*10
  x10[i]%=mod
  y59[i]=y59[i-1]*59
  y59[i]%=mod
u=[0]*(10**6+1)
A=list(map(int,input().split()))
ans=0
ans2=0
for i in range(N):
  x=A[i]
  for B in G[x]:
    y,count=B[:]
    r=(u[y]+count)%3
    ans+=r*x10[y]
    ans-=u[y]*x10[y]
    ans%=mod
    ans2+=r*y59[y]
    ans2-=u[y]*y59[y]
    u[y]=r
  v[i+1]=ans
  v2[i+1]=ans2
for i in range(Q):
  l,r=map(int,input().split())
  ans1=v[l-1]
  ans2=v[r]
  ans3=v2[l-1]
  ans4=v2[r]
  if ans1==ans2 and ans3==ans4:
    print('Yes')
  else:
    print('No')