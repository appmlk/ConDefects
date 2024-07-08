# import io
# import sys

# _INPUT = """\
# 5 998244353

# """
# sys.stdin = io.StringIO(_INPUT)

n, mod = map(int, input().split())
dp = [[0]*(n+2) for _ in range(n+2)]

dp[0][0] = 1
dp[1][0] = -1

ans = 0
for i in range(n+1):
  for j in range(n+1):
    if i!=0: 
      dp[i][j] += dp[i-1][j]
      dp[i][j] %= mod
    for k, d in ((1, 2), (10, 3), (100, 4), (1000, 5)):
      if i+k>n or j+d>n: break
      dp[i+k][j+d] += dp[i][j]*(26 if i==0 else 25)
      dp[i+k][j+d] %= mod
      dp[min(i+k*10, n+1)][j+d] -= dp[i][j]*(26 if i==0 else 25)
      dp[min(i+k*10, n+1)][j+d] %= mod
  

# print(dp)
print(sum(dp[n][:n])%mod)

