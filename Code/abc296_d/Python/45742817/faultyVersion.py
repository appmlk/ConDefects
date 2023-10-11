n,m=map(int,input().split())
ans=float('inf')
for a in range(1,int(m**0.5)+1):
    b=-(-m//a)
    if a<=n and b<=n:
        ans=min(ans,a*b)
if ans==float('inf'):
    print(-1)
else:
    print(ans)


