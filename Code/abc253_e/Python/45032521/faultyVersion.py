N, M, K = map(int, input().split())
MOD = 998244353
if K == 0:
    print(pow(N, M, MOD))
    exit()

dp = [1] * M

for _ in range(N - 1):
    nxt = [0] * M
    cum_sum = [0] * (M + 1)
    for i in range(M):
        cum_sum[i + 1] = cum_sum[i] + dp[i]
        cum_sum[i] %= MOD
    for i in range(M):
        if 0 <= i - K:
            nxt[i] += cum_sum[i - K + 1]
        if i + K <= M:
            nxt[i] += cum_sum[M] - cum_sum[i + K]
        nxt[i] %= MOD
    dp = nxt[:]
print(sum(dp) % MOD)