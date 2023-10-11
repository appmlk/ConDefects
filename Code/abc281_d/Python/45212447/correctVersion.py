n, k, d = map(int, input().split())
a = list(map(int, input().split()))

dp = [[[-1]* d for _ in range(k+1)] for i in range(n+1)]
dp[0][0][0] = 0

for i in range(n):
    for j in range(k+1):
        for l in range(d):
            if dp[i][j][l] == -1:
                continue
            dp[i+1][j][l] = max(dp[i+1][j][l], dp[i][j][l])
            if j+1 <= k:
                dp[i+1][j+1][(l+a[i])%d] = max(dp[i+1][j+1][(l+a[i])%d], dp[i][j][l] + a[i])

print(dp[n][k][0])