MOD = 998244353
N, X = map(int, input().split())
dp = [[[0] * (1 << 2 * X - 1) for _ in range(N + 1)] for _ in range(N + 1)]
dp[0][0][0] = 1
P = [1]
for i in range(N): P.append(P[i] * (i + 1) % MOD)

for i in range(1, N + 1):
    for j in range(i):
        for bit in range(1 << 2 * X - 1):
            # i項目を決める
            for k in range(2 * X - 1):
                if 1 <= i - X + 1 + k <= N and not bit >> k & 1:
                    dp[i][j + 1][(bit | 1 << k) >> 1] += dp[i - 1][j][bit]

            # i項目を決めない
            dp[i][j][bit >> 1] += dp[i - 1][j][bit]

print(sum(sum(dp[N][j]) * P[N - j] * pow(-1, j % 2) for j in range(N + 1)))