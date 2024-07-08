MOD = 998244353
N, M = map(int, input().split())
ans = 0
for i in range(60):
    if (M >> i) & 1:
        ans += M // (1 << (i + 1)) * (1 << i) + max(0, (M % (1 << (i + 1))) - (1 << i) + 1)
        ans %= MOD
print(ans)
