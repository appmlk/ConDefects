N,K = map(int,input().split())
S = input()
X, M = 0, 0
l = -1
r = N
for m in range(1,N+1):
  if N % m != 0:
    continue
  d = [[0 for i in range(26)] for j in range(m)]
  c = [0 for i in range(m)]
  for i in range(N):
    s = S[i]
    n = ord(s) - 97
    d[i%m][n] += 1
    c[i%m] += 1
  k = 0
  for i in range(m):
    k += (c[i] - max(d[i]))
  if k <= K:
    ans = m
    break
print(ans)