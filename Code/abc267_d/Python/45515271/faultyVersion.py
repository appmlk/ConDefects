N,M = map(int,input().split())
a = [*map(int,input().split())]
dp = [[None]*(M+1) for n in range(N+1)]
dp[0][0]=0
for n in range(N):
    for m in range(M+1):
        if dp[n][m] is not None:
            dp[n+1][m] = max(dp[n+1][m],dp[n][m]) if dp[n+1][m] is not None else dp[n][m]
            if m<M : dp[n+1][m+1] = max(dp[n+1][m+1],dp[n][m] + a[n]*(m+1)) if dp[n+1][m+1] is not None else dp[n][m] + a[n]*(m+1)
print(max(dp[N]))