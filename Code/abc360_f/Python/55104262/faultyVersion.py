n=int(input())
z=[0,1]
q=[]
for i in range(n):
  l,r=map(int,input().split())
  z+=[l,l-1,l+1,r,r-1,r+1]
  q+=[(l+1,0,l-1,-1),(r,0,l-1,1),(r+1,l+1,r-1,-1)]
z=sorted(set(z))
d={v:i for i,v in enumerate(z)}
from atcoder import lazysegtree
st=lazysegtree.LazySegTree(min,(0,10**10),lambda f,x:(x[0]+f,x[1]),lambda g,f:g+f,0,[(0,v) for v in z])
ans=[(0,0,1)]
q.sort()
q.reverse()
while len(q)>0:
  y=q[-1][0]
  while len(q)>0 and q[-1][0]==y:
    _,l,r,p=q.pop()
    st.apply(d[l],d[r]+1,p)
  p1,p2=st.all_prod()
  if 0<=p2<y:
    ans+=[(p1,p2,y)]
_,l,r=min(ans)
print(l,r)