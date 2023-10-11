import sys

read = sys.stdin.read
readline = sys.stdin.readline
readlines = sys.stdin.readlines


N, *A = map(int, read().split())
inf = 1 << 60

ans = inf

dp = [[inf, inf] for _ in range(N + 1)]
dp[1][0] = 0
for i in range(1, N):
    dp[i + 1][0] = dp[i][1]
    dp[i + 1][1] = min(dp[i]) + A[i]

ans = min(ans, dp[N][1])

dp = [[inf, inf] for _ in range(N + 1)]
dp[1][1] = A[0]
for i in range(1, N):
    dp[i + 1][0] = dp[i][1]
    dp[i + 1][1] = min(dp[i]) + A[i]

ans = min(ans, min(dp[N]))

print(ans)
