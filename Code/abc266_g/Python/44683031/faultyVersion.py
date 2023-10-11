num = 3*10**6 + 1
frac = [1] * num
inv_frac = [1] * num
MOD = 998244353
for i in range(2, num):
    frac[i] = (frac[i-1] * i) % MOD

inv_frac[-1] = pow(frac[-1], MOD-2, MOD)
for i in range(num-1, 0, -1):
    inv_frac[i-1] = (inv_frac[i] * i) % MOD

def comb(n, r):
    if r < 0 or r > n:
        return 0
    
    return (frac[n] * (inv_frac[n-r] * inv_frac[r])%MOD) % MOD

R, G, B, K = map(int, input().split())
R -= K
G -= K

ans = 1
ans *= comb(R+B+K, B)
ans *= comb(R+B, K)
ans %= MOD
ans *= comb(B+K+1+G-1, G)
ans %= MOD

print(ans)

