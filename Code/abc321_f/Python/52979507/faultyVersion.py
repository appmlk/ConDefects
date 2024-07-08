import sys
input = lambda: sys.stdin.readline().rstrip()
mod = 9982444353

#  -----------------------  #

Q, K = map(int, input().split())
dp = [0] * (K+1)
dp[0] = 1

for i in range(Q):
  com, x = input().split()
  x = int(x)
  if com == '+':
    for j in range(K, x-1, -1):
      dp[j] += dp[j-x]
      dp[j] %= mod
  else:
    for j in range(x, K+1):
      dp[j] -= dp[j-x]
      dp[j] %= mod
  print(dp[K])
