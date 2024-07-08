
from collections import Counter

mod = 998244353
n,k = map(int,input().split())
a = list(map(int,input().split()))
a_c = Counter(a)
v = [0] * (n+1)
for i, c in a_c.items():
	v[c] += 1
for i in range(n-1,0,-1):
	v[i] += v[i+1]

dp = [[0] * (n+1) for i in range(n+1)]
dp[0][1] = 1
i = 1
for g in range(1, n+1):
	l = v[g]
	for s in range(l):
		for j in range(1, n+1):
			dp[i][j] = ((j-1+g) * dp[i-1][j] + (i-j+2-g) * dp[i-1][j-1]) % mod
		i += 1

print(dp[n][k+1])