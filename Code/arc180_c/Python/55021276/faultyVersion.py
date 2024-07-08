MOD = 10**9 + 7

N = int(input())
A = list(map(int, input().split()))

dp = [1] + [0]*2000
res = [0]*21
for a in A:
  ndp = dp.copy()
  for s in range(-1000, 1001):
    if s == 0: continue
    ns = s + a
    if -1000 <= ns <= 1000:
      ndp[ns] += dp[s]
      ndp[ns] %= MOD
  for s in range(-10, 10):
    if s == 0: continue
    ns = s + a
    if -1000 <= ns <= 1000:
      ndp[ns] += res[s]
      ndp[ns] %= MOD
  if a != 0:
    res[a] = dp[0]
  dp = ndp.copy()

print(sum(dp) % MOD)