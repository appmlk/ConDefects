N = int(input())
mod = 998244353

dp = [[0]*11 for _ in range(N)]

for i in range(1,10):
    dp[0][i] = 1

for i in range(N-1):
    for j in range(1,10):
        dp[i+1][j] = (dp[i][j-1] + dp[i][j] + dp[i][j+1])%mod

print(sum(dp[-1])%mod)