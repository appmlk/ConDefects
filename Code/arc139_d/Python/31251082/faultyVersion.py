from bisect import bisect_right
import sys
input = sys.stdin.readline

def comb_preprocess(n, mod):
    fact = [1] * (n+1)
    fact_inv = [1] * (n+1)
    for i in range(1, n+1):
        fact[i] = i * fact[i-1] % mod
    fact_inv[n] = pow(fact[n], mod-2, mod)
    for i in range(1, n+1)[::-1]:
        fact_inv[i-1] = i * fact_inv[i] % mod

    def comb(n, k):
        if k < 0 or n < k:
            return 0
        return fact[n] * fact_inv[k] * fact_inv[n-k] % mod

    return fact, fact_inv, comb

mod = 998244353
N, M, K, X = map(int, input().split())
X -= 1
A = list(map(int, input().split()))
A.sort()
fact, fact_inv, comb = comb_preprocess(K, mod)
ans = 0
for Y in range(1, M+1):
    x = bisect_right(A, Y)
    if x <= X:
        p = pow(M-Y, K, mod)
        q = Y * pow(M-Y, mod-2, mod) % mod
        for k in range(K+1):
            ans = (ans + min(x+k, X)*p*comb(K, k)) % mod
            p = p * q % mod
    else:
        p = pow(Y, K, mod)
        q = (M-Y) * pow(Y, mod-2, mod) % mod
        for k in range(K+1):
            ans = (ans + max(x-k, X)*p*comb(K, k)) % mod
            p = p * q % mod
ans = (pow(M, K, mod) * (M + 1) * N - ans) % mod
print(ans)