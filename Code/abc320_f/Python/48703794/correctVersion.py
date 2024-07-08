n,h=map(int,input().split())
x=list(map(int,input().split()))+[0]
X=10**10
q=[[X]*(h+1) for i in range(h+1)]
for i in range(h+1):
  q[h][i]=0
from atcoder import lazysegtree
for i in range(n):
  nq=[[X]*(h+1) for j in range(h+1)]
  p,f=0,0
  if i<n-1:
    p,f=map(int,input().split())
  dx=x[i]-x[i-1]
  for j in range(h+1):
    st=lazysegtree.LazySegTree(
      min,
      X,
      min,
      min,
      X,
      [X]*(h+1)
    )
    for k in range(h+1):
      if q[j][k]<X:
        if j-dx>=0 and k+dx<=h:
          nq[j-dx][k+dx]=min(nq[j-dx][k+dx],q[j][k])
        if j-dx>=0 and k+dx<=h:
          nq[min(j-dx+f,h)][k+dx]=min(nq[min(j-dx+f,h)][k+dx],q[j][k]+p)
        if j-dx>=0 and k+dx<=h and k+dx-f>=0:
          nq[j-dx][k+dx-f]=min(nq[j-dx][k+dx-f],q[j][k]+p)
        if j-dx>=0 and k+dx==h:
          for l in range(h-f,h+1):
            nq[j-dx][l]=min(nq[j-dx][l],q[j][k]+p)
  q=nq
g=X
for i in range(h+1):
  g=min(g,q[i][i])
print(g if g<X else -1)