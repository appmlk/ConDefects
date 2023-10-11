n,K,D,*a=map(int,open(0).read().split())
dp=[[[-1]*D for _ in range(K+1)]for _ in range(n+1)]
dp[0][0][0]=0
for i in range(1,n+1):
  for j in range(K+1):
    for k in range(D):
      dp[i][j][k]=dp[i-1][j][k]
      if j and dp[i-1][j-1][k]!=-1:
        nk=(k+a[i-1])%D
        dp[i][j][nk]=max(dp[i][j][nk],dp[i-1][j-1][k]+a[i-1])
print(dp[n][K][0])