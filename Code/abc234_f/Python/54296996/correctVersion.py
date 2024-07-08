S = input()
N = len(S)

alpha = 'abcdefghijklmnopqrstuvwxyz'
mod = 998244353

alp = [0]*30
# def comb(n, k, mod):
#
#     fact = [0] * (n + 1)
#     inv = [0] * (n + 1)
#     finv = [0] * (n + 1)
#
#     fact[0], fact[1] = 1, 1
#     inv[1] = 1
#     finv[0], finv[1] = 1, 1
#
#     for i in range(2, n + 1):
#         fact[i] = (fact[i - 1] * i) % mod
#         inv[i] = mod - ((inv[mod % i] * (mod // i)) % mod)
#         finv[i] = (finv[i - 1] * inv[i]) % mod
#
#     return (fact[n] * finv[k] * finv[n-k]) % mod

for i in range(N):
    alp[alpha.index(S[i])] += 1

# print('alp:', alp)
# import math
# def comb(n, r):
#     return math.factorial(n) // (math.factorial(n - r) * math.factorial(r)) % mod
# # base = comb(5,3)
# # print(base) # 10

# from scipy.special import comb
F = [1]
Inv = [1]
for i in range(1, 5*10**3+10):
    F.append((F[-1] * i) % mod)
    Inv.append(pow(F[-1], -1, mod))

def comb(j, k, mod):
    res = F[j]
    res *= Inv[k]
    res %= mod
    res *= Inv[j - k]
    res %= mod
    return res

dp = [[0]*(5*10**3+10) for _ in range(30)]
dp[0][0] = 1
for i in range(26):

    for j in range(5 * 10 ** 3+1):
        if dp[i][j] == 0:
            continue
        for k in range(alp[i]+1):

            if j + k >= 5 * 10 ** 3 + 1:
                continue
            # print('j, k:', j, k)
            if j + k == 0:
                dp[i+1][k] += dp[i][j]
                continue
            dp[i + 1][j + k] += dp[i][j] * (comb(j + k, k, mod) % mod)
            dp[i + 1][j + k] %= mod
# print('dp[0]:', dp[0][:10])
# print('dp[26]:', dp[26][:10])
# ans = sum(dp[26][1:]) % mod
ans = 0
for i in range(1, len(dp[1])):
    ans += dp[26][i]
    ans %= mod
print(ans)
