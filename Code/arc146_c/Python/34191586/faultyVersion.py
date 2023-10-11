MOD, MAXN = 998244353, 2 * pow(10, 5)
N = int(input())
p, inv = [1] * (MAXN + 10), [1] * (MAXN + 10)
p[1] = 2
for i in range(2, MAXN + 1):
    p[i] = p[i - 1] * 2 % MOD
    inv[i] = -(MOD // i) * inv[MOD % i] % MOD
ans, res = 2, 1
for i in range(1, N + 1):
    res = res * (p[N] - p[i - 1] + MOD) % MOD * inv[i] % MOD
    ans = (ans + res * (p[i] * inv[i + 1] + 1) % MOD) % MOD
print(ans)
