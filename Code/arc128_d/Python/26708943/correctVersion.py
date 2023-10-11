mod = 998244353
n,*A = map(int,open(0).read().split())
A.append(A[-1])
C = [0]*(n+1)
ans = 1
l = r = 0
s = 0
c = 0
dp = [0]*n
for i in range(n):
  c += C[A[i]] == 0
  C[A[i]] += 1
  while r < i-2 and c >= 3:
    C[A[r]] -= 1
    c -= C[A[r]] == 0
    s += dp[r]
    s %= mod
    r += 1
  if i == l:
    t = 1
  else:
    t = sum(dp[max(l,i-2):i])+s
  t %= mod
  dp[i] = t
  if A[i] == A[i+1]:
    for j in range(l,i+1):
      C[A[j]] = 0
    l = r = i+1
    s = 0
    c = 0
    ans *= t
    ans %= mod
print(ans)