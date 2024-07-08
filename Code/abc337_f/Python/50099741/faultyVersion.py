N,M,K = map(int,input().split())
C = list(map(int,input().split()))
if K == 1:
  for i in range(N):
    print(M)
  exit()
C += C
m = 0
d = [0 for i in range(N)]
for i in range(N):
  d[C[i]-1] += 1
CC = [0 for i in range(N)]
use = [0 for i in range(N)]
ans = 0
for r in range(N):
  c = C[r] - 1
  CC[c] += 1
  if CC[c] % K == 1:
    m += 1
    use[c] += 1
    ans += min(d[c],use[c]*K)
  if m == M:
    break

print(ans)
for l in range(1,N):
  c = C[l-1] - 1
  CC[c] -= 1
  if CC[c] % K == 0:
    use[c] -= 1
    ans -= min(K,d[c] - use[c]*K)
    m -= 1
  for i in range(1,N+1):
    if m == M:
      break
    r += 1
    cc = C[r] - 1
    CC[cc] += 1
    if CC[cc] % K == 1:
      m += 1
      ans += min(K,d[cc] - K*use[cc])
      use[cc] += 1
    if m == M:
      break
    if r == l + N - 1:
      break
  print(ans)