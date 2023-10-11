n,l,r=map(int,input().split())
ans=0
for i in range(60):
  if (n>>i)&1:
    ans+=min(r+1,(1<<(i+1)))-max(l,1<<i)
print(ans)