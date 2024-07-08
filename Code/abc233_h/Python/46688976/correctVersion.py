n=int(input())
X=10**5
p=[]
for i in range(n):
  x,y=map(int,input().split())
  p+=[(x-y+X,x+y)]
q=int(input())
b=[]
k=[0]*q
for i in range(q):
  x,y,k[i]=map(int,input().split())
  b+=[(x-y+X,x+y)]
ok=[2*X+1]*q
ng=[-1]*q
from atcoder import fenwicktree
while any(ok[i]-ng[i]>1 for i in range(q)):
  m=[(ok[i]+ng[i])//2 for i in range(q)]
  d0=[[] for i in range(2*X+1)]
  d1=[[] for i in range(2*X+1)]
  d2=[[] for i in range(2*X+1)]
  for i in range(n):
    x,y=p[i]
    d1[y]+=[i]
  for i in range(q):
    if ok[i]-ng[i]>1:
      x,y=b[i]
      d0[max(0,y-m[i])]+=[i]
      d2[min(2*X,y+m[i])]+=[i]
  c=[0]*q
  st=fenwicktree.FenwickTree(2*X+1)
  for i in range(2*X+1):
    for j in d0[i]:
      x,y=b[j]
      l=m[j]
      c[j]=st.sum(max(0,x-l),min(2*X,x+l)+1)
    for j in d1[i]:
      x,y=p[j]
      st.add(x,1)
    for j in d2[i]:
      x,y=b[j]
      l=m[j]
      c[j]=st.sum(max(0,x-l),min(2*X,x+l)+1)-c[j]
  for i in range(q):
    if c[i]>=k[i]:
      ok[i]=m[i]
    else:
      ng[i]=m[i]
print(*ok,sep="\n")