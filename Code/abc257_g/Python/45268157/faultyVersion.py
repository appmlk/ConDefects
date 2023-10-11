mod=67280421310721
class segtree:
  def __init__(self,n):
    self.size=1
    self.height=0
    while self.size<n:
      self.size*=2
      self.height+=1
    self.dat=[10**10]*(self.size*2)                                        
    self.lazy=[10**10]*(self.size*2)
  def update(self,l,r,a):
    l+=self.size
    r+=self.size
    while l<r:
      if l&1:
        self.lazy[l]=min(self.lazy[l],a)
        l+=1
      if r&1:
        r-=1
        self.lazy[r]=min(self.lazy[l],a)                           
      l//=2
      r//=2
  def querry(self,l,r):
    l+=self.size
    r+=self.size
    score=10**20
    while l<r:
      if l&1:
        w=min(self.dat[l],self.lazy[l])
        score=min(score,w)
        l+=1
      if r&1:
        r-=1
        w=min(self.dat[r],self.lazy[r])
        score=min(score,w)
      l//=2
      r//=2
    return score
  def propagate(self,x):
    x+=self.size
    for h in range(self.height,0,-1):
      y=x>>h
      self.lazy[2*y]=min(self.lazy[2*y],self.lazy[y])
      self.lazy[2*y+1]=min(self.lazy[2*y+1],self.lazy[y])
      self.dat[y]=min(self.dat[y],self.lazy[y])
      self.lazy[y]=10**10
  def bottom(self,x):
    x+=self.size
    while x>1:
      x//=2
      self.dat[x]=min(min(self.dat[2*x],self.lazy[2*x]),min(self.dat[2*x+1],self.lazy[2*x+1]))
S=input()
T=input()
N=len(T)
M=len(S)
if S[0]!=T[0]:
  print(-1)
  exit()
dp=[10**10]*N
dp[0]=0
x100=[1]*(N+1)
for i in range(1,N+1):
  x100[i]=x100[i-1]*100
  x100[i]%=mod
ra=[0]*M
rb=[0]*N
Z=segtree(N+1)
Z.propagate(0)
Z.update(0,1,0)
Z.bottom(0)
for i in range(M):
  x=ord(S[i])-ord('a')+1
  ra[i]=ra[i-1]*100+x
  ra[i]%=mod
for i in range(N):
  x=ord(T[i])-ord('a')+1
  rb[i]=rb[i-1]*100+x
  rb[i]%=mod
for i in range(N):
  Z.propagate(i)
  x=Z.querry(i,i+1)
  if x>=10**10:
    continue
  if T[i]==S[0]:
    l=i
    r=N-1
    while True:
      if l==r:
        break
      m=(l+r+1)//2
      if m-i+1>M:
        r=m-1
      else:
        a=ra[m-i]
        if i==0:
          b=rb[m]
        else:
          b=rb[m]-rb[i-1]*x100[m-i+1]
          b%=mod
        if a==b:
          l=m
        else:
          r=m-1
  Z.propagate(i+1)
  Z.propagate(l+1)
  Z.update(i+1,l+2,x+1)
  Z.bottom(i+1)
  Z.bottom(l+1)
Z.propagate(N)
result=Z.querry(N,N+1)
if result>=10**10:
  print(-1)
else:
  print(result)