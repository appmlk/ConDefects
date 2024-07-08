def solve(n, A):
	A.sort()
	main, cur = 0, 0
	A = [-1] + A
	for i in range(1, n + 1):
		if A[i] == A[i - 1]:
			if (A[i] % 2) == 1:
				cur += 1
		else:
			main += cur
			cur = 0
			if main < A[i] - 1:
				return "Yes"
			else:
				if (A[i]%2) == 1:
					cur = 1
	return "No"

T = int(input())
for _ in range(T):
	n = int(input())
	A = list(map(int, input().split()))
	print(solve(n, A))



