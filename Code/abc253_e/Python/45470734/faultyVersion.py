N, M, K = map(int, input().split())
mod = 998244353

dp = [[0]*M for _ in range(N)]
for i in range(M):
    dp[0][i] = 1

for i in range(N-1):
    s = [0]
    for j in range(M):
        s.append(s[-1]+dp[i][j])
    for j in range(M):
        dp[i+1][j] += s[max(j-K+1, 0)] - s[0]
        dp[i+1][j] += s[M] - s[min(j+K, M)]
        dp[i+1][j] %= mod

print(sum(dp[N-1])%mod)
