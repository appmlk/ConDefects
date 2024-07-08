# https://atcoder.jp/contests/abc297/tasks/abc297_f

height, width, num_blocks = map(int, input().split())
MOD = 998244353
n = 10**6

def g(h, w):
    return cmb(h*w, num_blocks, MOD)

def f(h, w):
    expected = g(h, w) - 2*g(h-1, w) - 2*g(h, w-1) + g(h-2, w) + g(h, w-2) + 4*g(h-1, w-1) - 2*g(h-2, w-1) - 2*g(h-1, w-2) + g(h-2, w-2)
    expected %= MOD
    return expected

def cmb(n, r, p):
    if (r < 0) or (n < r):
       return 0
    r = min(r, n - r)
    return fact[n] * factinv[r] % p * factinv[n-r] % p

fact = [1, 1]  # fact[n] = (n! mod MOD)
factinv = [1, 1]  # factinv[n] = ((n!)^(-1) mod MOD)
inv = [0, 1]  # factinv 計算用

for i in range(2, n + 1):
    fact.append((fact[-1] * i) % MOD)
    inv.append((-inv[MOD % i] * (MOD // i)) % MOD)
    factinv.append((factinv[-1] * inv[-1]) % MOD)

ans = 0
for h in range(1, height+1):
    for w in range(1, width+1):
        if h*w < num_blocks: continue
        ans += (height - h + 1)*(width - w + 1)*f(h, w)%MOD*h*w
        ans %= MOD
ans *= pow(g(height, width), -1, MOD)
ans %= MOD

print(ans)
