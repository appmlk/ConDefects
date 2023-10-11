n = int(input())
mod = 998244353
dp = [[0]*10 for _ in range(n)]
for i in range(1, 10):
    dp[0][i] = 1

for i in range(n):
    for j in range(1, 10):
        dp[i][j] += dp[i-1][j]
        dp[i][j] %= mod
        if j-1 >= 1:
            dp[i][j] += dp[i-1][j-1]
            dp[i][j] %= mod
        if j+1 <= 9:
            dp[i][j] += dp[i-1][j+1]
            dp[i][j] %= mod
print(sum(dp[n-1])%mod)