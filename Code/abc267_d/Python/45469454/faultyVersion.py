N, M = map(int, input().split())
A_L = list(map(int, input().split()))
inf = -10**18
dp = [[inf] * (M + 10) for i in range(N + 10)]
dp[0][0] = 0

for i in range(N):
    for j in range(M):
        if dp[i][j] == inf:
            continue

        dp[i + 1][j + 1] = max(dp[i + 1][j + 1], dp[i][j] + (j + 1) * A_L[i])
        dp[i + 1][j] = max(dp[i + 1][j], dp[i][j])
ans = 0
for i in range(N+1):
    ans = max(ans, dp[i][M])
print(ans)
