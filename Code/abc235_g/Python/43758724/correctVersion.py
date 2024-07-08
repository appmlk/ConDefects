import sys, os, io
input = io.BytesIO(os.read(0, os.fstat(0).st_size)).readline

n, a, b, c = map(int, input().split())
mod = 998244353
l = n + 5
fact = [1] * (l + 1)
for i in range(1, l + 1):
    fact[i] = i * fact[i - 1] % mod
inv = [1] * (l + 1)
inv[l] = pow(fact[l], mod - 2, mod)
for i in range(l - 1, -1, -1):
    inv[i] = (i + 1) * inv[i + 1] % mod

def comb(n, r):
    return fact[n] * inv[r] % mod * inv[n - r] % mod if n >= r >= 0 else 0

a0, b0, c0 = 1, 1, 1
ans, d = 1, -1
for i in range(1, n + 1):
    a0 = (2 * a0 - comb(i - 1, a)) % mod
    b0 = (2 * b0 - comb(i - 1, b)) % mod
    c0 = (2 * c0 - comb(i - 1, c)) % mod
    ans += d * a0 * b0 % mod * c0 % mod * comb(n, i) % mod
    d *= -1
ans = ans % mod if not n % 2 else -ans % mod
print(ans)