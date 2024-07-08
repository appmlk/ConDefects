q, k = map(int, input().split())
mod = 998244353
dp = [0] * (k + 1)
dp[0] = 1
for i in range(q):
  t, x = map(str, input().split())
  x = int(x)
  if t == "+":
    for j in reversed(range(k+1-x)):
      dp[j+x] += dp[j]
      dp[j+x] %= mod
  else:
    for j in range(k+1-x):
      dp[j+x] -= dp[j]
      dp[j+x] %= mod
  print(dp[k])
  # print(dp)