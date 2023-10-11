from itertools import accumulate
mod = 998244353
N, M, K = map(int, input().split())
dp = [[0]*(M+1) for _ in range(N)]
for a in range(1, M+1):
    dp[0][a] = 1
for i in range(1, N):
    ac0 = list(accumulate(dp[i-1]))
    ac1 = list(accumulate(reversed(dp[i-1])))
    ac1.reverse()
    for a in range(1, M-K+1):
        dp[i][a+K] = ac0[a] % mod
    for a in range(K+1, M+1):
        dp[i][a-K] = (dp[i][a-K] + ac1[a]) % mod
print(sum(dp[N-1]) % mod)