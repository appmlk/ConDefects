n,L=map(int,input().split())
a=list(map(int,input().split()))
a.append(10**18)
a=[-10**18]+a

ans=10**18

for i in range(n):
  r=n
  l=-1
  while r-l>1:
    v=(r+l)//2
    if a[v]>=L-a[i+1]:
      r=v
    else:
      l=v
  ans=min(ans,2*L+min(L-a[i+1]-a[r-1],a[r]-L+a[i+1])*2)
      
print(ans)