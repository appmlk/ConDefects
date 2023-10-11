p = 998244353
N, M = map(int, input().split())
K = 60
if N > 60:
    print(0)
    exit()
dp = [[0] * (K + 1) for _ in range(N + 1)]
dp[0][0] = 1
for i in range(N):
    for j in range(K + 1):
        for jj in range(1, K + 1):
            jn = j + jj
            if jn > K:
                continue
            "jn桁となるのは 1 << (jn - 1) <= x < 1 << jn"
            if 1 << (jn - 1) <= M < 1 << jn:
                dp[i + 1][jn] += dp[i][j] * (M - (1 << (jn - 1)) + 1)
                dp[i + 1][jn] %= p
            elif M > 1 << jn:
                dp[i + 1][jn] += dp[i][j] * pow(2, jn - 1, p)
                dp[i + 1][jn] %= p

ans = 0
for j in range(K + 1):
    ans += dp[N][j]
    ans %= p
print(ans)
# for i in range(N + 1):
#     print(dp[i])