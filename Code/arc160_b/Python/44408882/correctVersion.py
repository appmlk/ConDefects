from sys import stdin

readline = stdin.readline
t = int(readline())
mod = 998244353
for _ in range(t):
    n = int(readline())
    m = int(n**0.5)
    ans = pow(m, 3, mod)
    j = m
    for i in range(m, 0, -1):
        x = n // i
        ans += 3 * (x - j) * i**2
        ans %= mod
        j = x
    print(ans)
