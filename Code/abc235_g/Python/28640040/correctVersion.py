N, A, B, C = map(int,input().split())
mod = 998244353
fact = [1]*(N+1)
invf = [1]*(N+1)
for i in range(N):
  fact[i+1] = fact[i] * (i+1) % mod
invf[-1] = pow(fact[-1], mod-2, mod)
for i in range(N, 0, -1):
  invf[i-1] = invf[i] * i % mod
def comb(n, k):
  if n < 0 or k < 0 or n < k:
    return 0
  return fact[n] * invf[k] % mod * invf[n-k] % mod

f, g, h = 0, 0, 0
fb, gb, hb = 1, 1, 1
sign = pow(-1,N,mod)
S = 0
for k in range(N+1):
  S = (S+sign*comb(N,k)%mod * (fb - f) * (fb - g) * (fb - h) % mod)%mod
  f = (2*f+comb(k, A))%mod
  g = (2*g+comb(k, B))%mod
  h = (2*h+comb(k, C))%mod
  fb = 2*fb%mod
  sign = sign*(mod-1)%mod
print(S)