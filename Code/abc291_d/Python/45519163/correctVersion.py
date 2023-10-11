import sys
input = sys.stdin.buffer.readline

N = int(input())
MOD = 998244353

dp = [[0] * 2 for _ in range(N)]
dp[0] = [1,1]

for i in range(N):
  if i == 0:
    old_a, old_b = map(int, input().split())
  else:
    A, B = map(int, input().split())

    if old_a != A:
      dp[i][0] += dp[i-1][0]
    if old_b != A:
      dp[i][0] += dp[i-1][1]
    if old_a != B:
      dp[i][1] += dp[i-1][0]
    if old_b != B:
      dp[i][1] += dp[i-1][1]
    
    dp[i][0] %= MOD
    dp[i][1] %= MOD
    
    old_a, old_b = A, B

print(sum(dp[N-1]) % MOD)