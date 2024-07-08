MOD = 998244353

class ml(list):
    def __setitem__(self, key, value):
        super().__setitem__(key, value%MOD)

n = int(input())
a = list(map(int, input().split()))
dp = [ml([0] * (1<<10)) for _ in range(n+1)]
dp[0][0] = 1

for i in range(n):
    inv = pow(a[i], -1, MOD)
    for bit in range(1<<10):
        dp[i+1][bit] += dp[i][bit] * max(0, a[i] - 10) * inv
        for plus in range(1, min(11, a[i]+1)):
            nex = ((bit * (2 ** plus) + 2 ** (plus - 1)) % (1 << 10))
            dp[i+1][bit|nex] += dp[i][bit] * inv

ans = 0
for bit in range(1<<10):
    if bit & (1<<9): ans = (ans + dp[n][bit]) % MOD
print(ans)
