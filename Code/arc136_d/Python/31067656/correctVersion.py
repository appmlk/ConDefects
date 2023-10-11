n = int(input())
a = list(map(int,input().split()))

dp = [0] * 1000000

for i in range(n):
	dp[999999-a[i]] += 1

for i in range(6):
	mask = 10**i
	for j in range(999999, -1, -1):
		if (j//mask)%10 != 9:
			dp[j] += dp[j+mask]

ans = 0
for i in range(n):
	mode = 1
	for j in range(6):
		if (a[i]//(10**j))%10 >= 5:
			mode = 0
	if mode:
		ans -= 1
	ans += dp[a[i]]

print(ans//2)