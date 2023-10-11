from collections import defaultdict
mod = 998244353

kaijou = [1]
kaijou_ = [1]
for i in range(1,2*10**5+1):
  k = kaijou[-1]*i
  k %= mod
  kaijou.append(k)
  k = pow(k,mod-2,mod)
  kaijou_.append(k)
  
def comb(n,r):
  if n < r:
    return 0
  res = kaijou[n]
  res *= kaijou_[r]
  res %= mod
  res *= kaijou_[n-r]
  res %= mod
  return res

N,M,K = map(int,input().split())
d = defaultdict(int)
for i in range(M):
  a,b = map(int,input().split())
  d[a] ^= 1
  d[b] ^= 1

a = 0
for i in range(1,N+1):
  a += d[i]
b = N - a
ans = 0
for i in range(K+1):
  k = 2*i
  if k > K:
    break
  r = comb(a,k)
  r *= comb(b,K-k)
  r %= mod
  ans += r
  ans %= mod
print(ans)