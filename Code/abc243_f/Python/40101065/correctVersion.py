mod = 998244353
#nCk
def com(n,mod):
  fact = [1,1]
  factinv = [1,1]
  inv = [0,1]
  for i in range(2,n+1):
    fact.append((fact[-1]*i)%mod)
    inv.append((-inv[mod%i]*(mod//i))%mod)
    factinv.append((factinv[-1]*inv[-1])%mod)
  return fact, factinv

f,fi = com(10000, mod) 

def ncr(n,r):
    return f[n] * fi[n-r] % mod * fi[r] % mod


n,m,k = map(int,input().split())

w = [int(input()) for i in range(n)]


dp = [[[0]*(k+1) for i in range(m+1)] for i in range(n+1)]

dp[0][0][0] = 1

for i in range(1, n+1):
    wi = w[i-1]
    for j in range(m+1):
        for kk in range(k+1):
            dp[i][j][kk] += dp[i-1][j][kk]
            for l in range(1,k+1):
                if kk >= l and j >= 1:
                    dp[i][j][kk] += dp[i-1][j-1][kk-l] * pow(wi, l, mod) * fi[l]
                    dp[i][j][kk] %= mod
                    
b = pow(sum(w), mod-2, mod)
print(pow(b, k ,mod)*dp[-1][-1][-1]*f[k]%mod)
