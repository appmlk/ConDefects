import sys

n = int(input())
ans = []
for i in range(1, n + 1):
	a, b = map(int, sys.stdin.readline().strip().split())
	sum = a + b
	res = a / (sum)
	ans.append([res, -i])

ans.sort(reverse = True)
for i in range (0, len(ans)):
	print(-ans[i][1], end = " ")