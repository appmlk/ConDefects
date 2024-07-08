MOD = 998244353

n, x, y, z = map(int, input().split())
x = abs(x)
y = abs(y)
z = abs(z)

def factorial(n):
    fact = [1] * (n + 1)
    ifact = [0] * (n + 1)
    for i in range(1, n + 1):
        fact[i] = fact[i-1] * i % MOD
    ifact[n] = pow(fact[n], MOD - 2, MOD)
    for i in range(n, 0, -1):
        ifact[i-1] = ifact[i] * i % MOD
    return fact, ifact

fact, ifact = factorial(n)
def comb(n, r):
    if r < 0 or r > n:
        return 0
    return fact[n] * ifact[r] % MOD * ifact[n-r] % MOD

def f(a, b):
    b = abs(b)
    if a < b or (a - b) % 2:
        return 0
    return comb(a, (a - b) // 2)

ans = 0
for i in range(x, n + 1, 2):
    t = comb(n, i) * f(i, x) % MOD
    t = t * f(n - i, y + z) % MOD
    t = t * f(n - i, y - z) % MOD
    ans = (ans + t) % MOD
print(ans)