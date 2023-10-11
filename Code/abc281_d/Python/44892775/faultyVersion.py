n, k, d = map(int, input().split())
aa = list(map(int, input().split()))
inf = 2**63 - 1
dp = [[-inf] * d for _ in range(k + 1)]
dp[0][0] = 0
nx = [x[:] for x in dp]
for a in aa:
    for i in range(k):
        for j in range(d):
            nxj = (j + a) % d
            nx[i + 1][nxj] = max(nx[i + 1][nxj], dp[i][j] + a)
    dp = [x[:] for x in nx]

ans = dp[k][0]
print(ans if 0 < ans else -1)
