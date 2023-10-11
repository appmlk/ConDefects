N, K = map(int, input().split())
A = set(map(int, input().split()))

M = 400001
fact = [1] * M
mod = 998244353
for i in range(1, M):
  fact[i] = fact[i-1]*i
  fact[i] %= mod

inv_fact = [1] * M
inv_fact[-1] = pow(fact[-1], mod-2, mod)
for i in range(M-1, 0, -1):
  inv_fact[i-1] = inv_fact[i]*i
  inv_fact[i-1] %= mod

def C(n, k):
  if k < 0:
    return 0
  value = fact[n]*inv_fact[k]*inv_fact[n-k]
  value %= mod
  return value

cnt = 0
total = 0
ans = 1
for i in range(M):
  if i in A:
    cnt += 1
    continue
  total += 1
  ans += C(K+cnt-1, i-1)
  ans %= mod
  if total == K:
    break

print(ans)