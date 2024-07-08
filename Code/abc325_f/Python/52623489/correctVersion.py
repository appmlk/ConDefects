N=int(input())
D=list(map(int, input().split()))
l1,c1,k1=list(map(int, input().split()))
l2,c2,k2=list(map(int, input().split()))

dp=[[10**5]*(k1+1) for _ in range(N+1)]
dp[0][0]=0
for i in range(1,N+1):
    K=-((-D[i-1])//l1)
    for j in range(k1+1):
        for k in range(K+1):
            if j+k>k1:
                break
            r=max(0,D[i-1]-k*l1)
            KK = -((-r)//l2)
            dp[i][j+k]=min(dp[i][j+k],dp[i-1][j]+KK)
ans=10**30
for i in range(k1+1):
    if dp[-1][i]>k2 or dp[-1][i]==10**5:
        continue
    ans=min(ans,i*c1+dp[-1][i]*c2)
if ans==10**30:
    print(-1)
else:
    print(ans)