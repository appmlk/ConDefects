import sys
from math import *
from bisect import *

def solve():
	N = int(input())
	A = list(map(int, input().split()))
	dp = []
	Len = [0 for i in range(N)]
	for i in range(N):
		j = bisect_left(dp, A[i])
		Len[i] = j + 1
		if j < len(dp):
			dp[j] = A[i]
		else:
			dp.append(A[i])
	O = max(Len)
	Max = [-1 for i in range(N + 2)]
	Max[O + 1] = 10000000000
	ans = []
	for i in range(N - 1, -1, -1):
		if Max[Len[i] + 1] > A[i]:
			ans.append(i + 1)
			Max[Len[i]] = max(Max[Len[i]], A[i])
	print(len(ans))
	ans = ans[::-1]
	print(*ans)
for test in range(int(input())):
	solve()