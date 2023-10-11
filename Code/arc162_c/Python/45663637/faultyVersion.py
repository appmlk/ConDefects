t = int(input())
for _ in range(t):
	n, k = map(int, input().split())
	p = [ -1 ] + list(map(lambda x: int(x) - 1, input().split()))
	a = list(map(int, input().split()))
	for i in range(n):
		if a[i] == -1:
			a[i] = n + 1
	dp = [ [ 0 ] * (n + 2) for i in range(n) ]
	for i in range(n):
		dp[i][a[i]] += 1
	answer = False
	for i in range(n - 1, -1, -1):
		for j in range(n + 2):
			dp[p[i]][j] += dp[i][j]
		if dp[i][n + 1] <= 1:
			cnt = 0
			for j in range(k):
				if dp[i][j] == 0:
					cnt += 1
			if dp[i][k] == 0 and cnt <= 1:
				answer = True
	print('Alice' if answer else 'Bob')