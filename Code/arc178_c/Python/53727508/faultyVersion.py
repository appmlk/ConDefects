N, L = map(int, input().split())
la = map(int, input().split())

from math import inf
dp = [inf] * (200005)
dp[0] = 0

for i in range(1, L-1):
  w = i * (L-i)
  for j in range(w, 200005):
    dp[j] = min(dp[j], dp[j-w]+1)

for i in la:
  if dp[i]==inf: print(-1)
  else: print(dp[i])