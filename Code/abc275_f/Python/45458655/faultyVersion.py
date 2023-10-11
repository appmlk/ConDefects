N,M=map(int,input().split())
A=list(map(int,input().split()))
inf=1<<3
dp=[[[inf]*(M+1) for _ in range(2)] for _ in range(N+1)]
dp[0][1][0]=0
for i in range(N):
    for k in range(2):
        for j in range(M+1):
            if k==0:
                if dp[i][k][j]==inf:continue
                if i!=0:
                    dp[i+1][k][j]=min(dp[i][k][j],dp[i+1][k][j])
                else:
                    dp[i+1][k][j]=1

                if A[i]+j<=M:
                    dp[i+1][1][A[i]+j]=min(dp[i][0][j]+1,dp[i+1][1][A[i]+j])
                
            else:
                if dp[i][k][j]==inf:continue

                dp[i+1][0][j]=min(dp[i][k][j],dp[i+1][0][j])


                if A[i]+j<=M:
                    dp[i+1][1][A[i]+j]=min(dp[i][k][j],dp[i+1][1][A[i]+j])

                             
for x in range(1,M+1):
    ans=inf
    for i in range(1,N+1):
        if i!=N:
            ans=min(ans,dp[i][1][x]+1)
        else:
            ans=min(ans,dp[i][1][x])
    print(ans if ans!=inf else -1)