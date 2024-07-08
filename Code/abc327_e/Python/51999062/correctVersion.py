N = int(input())
P = list(map(int, input().split()))

dp = [[-1<<60] * (N + 1) for _ in range(N + 1)]
dp[0][0] = 0

for i in range(1, N + 1):
  p = P[i - 1]
  for j in range(i + 1):
    dp[i][j] = max(dp[i][j], dp[i - 1][j])
    if j - 1 >= 0:
      dp[i][j] = max(dp[i][j], dp[i - 1][j - 1] * 0.9 + p)

base = 1
ans = -1<<60
for i in range(1, N + 1):
  val = dp[-1][i] / base - 1200/(i**0.5)
  ans = max(ans, val)
  base *= 0.9
  base += 1
print(ans)