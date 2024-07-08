n,q=map(int,input().split())
X=list(map(int,input().split()))

ans=[0]*(n+1)
acc=[0]*(q+1)
add=[0]*(n+1)
se=set()
for i,x in enumerate(X):
  if x not in se:
    se.add(x)
    add[x]=i
    
  else:
    se.discard(x)
    ans[x]+=acc[i]-acc[add[x]]
    add[x]=-1

  acc[i+1]=acc[i]+len(se)

for i in range(1,n+1):
  ans[i]+=acc[-1]-acc[add[i]]
print(*ans[1:])