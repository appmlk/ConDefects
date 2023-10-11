l1,r1,l2,r2=map(int,input().split())
ans=0
for i in range(0,100):
    if l1<i<=r1 and l2<i<=r2:
        ans+=1
print(ans)