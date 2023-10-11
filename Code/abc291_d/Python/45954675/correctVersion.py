N = int(input())
S = [list(map(int, input().split())) for _ in range(N)]
MOD = 998244353

# dp[i][j]: i番目まで見たときに、カードが表か裏か(j=0:表, j=1:裏)の時の数
dp = [[0] * 2 for _ in range(N + 1)]
dp[0][0] = 1
for i in range(N):
    if i == 0:
        dp[i+1][0] += dp[i][0]
        dp[i+1][1] += dp[i][0]
    else:
        preA, preB = S[i-1]
        nowA, nowB = S[i]
        if preA != nowA:
            dp[i+1][0] += dp[i][0]
        if preA != nowB:
            dp[i+1][1] += dp[i][0]
        if preB != nowA:
            dp[i+1][0] += dp[i][1]
        if preB != nowB:
            dp[i+1][1] += dp[i][1]
    
    dp[i+1][0] %= MOD
    dp[i+1][1] %= MOD

print(sum(dp[N]) % MOD)
