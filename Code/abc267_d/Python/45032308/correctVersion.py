N, M = [int(x) for x in input().split()]
A = [int(x) for x in input().split()]

inf = 10**18
dp = [ [-inf] * (M + 1) for _ in range(N + 1) ]
for i in range(N + 1):
    dp[i][0] = 0

for i in range(1, N + 1):
    a = A[i - 1]
    maxm = min(M, i)
    for j in range(1, maxm + 1):
        dp[i][j] = max(dp[i][j], j * a + dp[i - 1][j - 1])
        dp[i][j] = max(dp[i][j], dp[i - 1][j])

print(dp[N][M])
