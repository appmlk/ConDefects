N = int(input())
AB = [list(map(int, input().split())) for _ in range(N)]
mod = 998244353
dp = [[0] * 2 for _ in range(N)]
dp[0][0] = 1
dp[0][1] = 1
for i in range(1, N):
    if AB[i][0] != AB[i - 1][0]:
        dp[i][0] += dp[i - 1][0]
    if AB[i][0] != AB[i - 1][1]:
        dp[i][0] += dp[i - 1][1]
    if AB[i][1] != AB[i-1][0]:
        dp[i][1] += dp[i-1][0]
    if AB[i][1]!= AB[i-1][1]:
        dp[i][1] += dp[i-1][1]
    dp[i][0] %= mod
    dp[i][1] %= mod
# print(dp)
ans = sum(dp[-1])%mod
print(ans)
