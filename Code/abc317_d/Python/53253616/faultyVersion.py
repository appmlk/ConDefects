n = int(input())
x, y, z = [None] * n, [None] * n, [None] * n
for i in range(n):
    x[i], y[i], z[i] = map(int, input().split())
v = [None] * n
for i in range(n):
    v[i] = max(0, y[i] - (x[i] + y[i]) // 2)
m = sum(z)
dp = [[10**10] * (m + 1) for _ in range(n + 1)]
dp[0][0] = 0
for i in range(1, n + 1):
    for j in range(m + 1):
        dp[i][j] = dp[i - 1][j]
        if j - z[i - 1] >= 0:
            dp[i][j] = min(dp[i][j], dp[i - 1][j - z[i - 1]] + v[i - 1])
print(min([dp[i][j] for j in range(m // 2 + 1, m + 1)]))
