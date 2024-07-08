class CalcFactorial:
    def __init__(self, n, mod=998244353):
        self.n = n
        self.mod = mod
        self.fact = [0] * (n + 1)
        self.fact[0] = 1
        for i in range(n):
            self.fact[i + 1] = self.fact[i] * (i + 1) % mod

        # i! のmodにおける逆元
        self.inv = [0] * (n + 1)
        self.inv[-1] = pow(self.fact[-1], mod - 2, mod)
        for i in range(n, 0, -1):
            self.inv[i - 1] = self.inv[i] * i % mod

    def p(self, n, r):
        if n < r or r < 0:
            return 0
        else:
            return self.fact[n] * self.inv[n - r] % self.mod

    def c(self, n, r):
        if n < r or r < 0:
            return 0
        else:
            return self.fact[n] * self.inv[n - r] * self.inv[r] % self.mod


n, a, b, c = map(int, input().split())
mod = 998244353
cal = CalcFactorial(n + 2)
sm_a = sum([cal.c(n, i) for i in range(a + 1)]) % mod
sm_b = sum([cal.c(n, i) for i in range(b + 1)]) % mod
sm_c = sum([cal.c(n, i) for i in range(c + 1)]) % mod
ans = sm_a * sm_b % mod * sm_c % mod
inv2 = cal.inv[2]

for i in range(1, n):
    sm_a = (sm_a + cal.c(n - i, a)) * inv2 % mod
    sm_b = (sm_b + cal.c(n - i, b)) * inv2 % mod
    sm_c = (sm_c + cal.c(n - i, c)) * inv2 % mod
    ans += sm_a * sm_b % mod * sm_c % mod * cal.c(n, i) * ((-1) ** (i & 1))
    ans %= mod

print((ans + 1) % mod)
