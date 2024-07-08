MOD = 998244353 
n = int(input())
RT_N = int(n ** 0.5) + 1
a = list(map(int, input().split()))

dp_black, dp_white = [0] * n, [0] * n
dp_black[0] = 1
lump = [[0] * n for _ in range(RT_N)]

for i in range(n - 1):
  
  if a[i] < RT_N:
    lump[a[i]][i] = (lump[a[i]][i] + dp_black[i]) % MOD
  
  else:
    for j in range(i + a[i], n, a[i]):
      dp_black[j] = (dp_black[j] + dp_black[i]) % MOD
  
  dp_white[i+1] = (dp_black[i] + dp_white[i]) % MOD
  
  for step in range(1, min(RT_N, n - i)):
    dp_black[i+step] = (dp_black[i+step] + lump[step][i]) % MOD
    lump[step][i+step] = lump[step][i]

print((dp_black[-1] + dp_white[-1]) % MOD)