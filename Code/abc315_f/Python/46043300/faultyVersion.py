import math

N = int(input())
XY = [[int(i) for i in input().split()] for _ in range(N)]

inf = 1e10
M = min(20, N)
dp = [[inf] * (M + 1) for _ in range(N + 1)]

dp[0][0] = 0.0
for i in range(N - 1):
    for c in range(M):
        if dp[i][c] == inf:
            continue
        for c2 in range(c, M):
            np = i + (c2 - c) + 1
            if np >= N:
                break
            di = math.dist(XY[np], XY[i])
            dp[np][c2] = min(dp[np][c2], dp[i][c] + di)

ans = dp[N - 1][c]
for c in range(1, M):
    ans = min(ans, dp[N - 1][c] + 2**(c - 1))
print(ans)
