for _ in range(int(input())):
	N, A, B = map(int, input().split())
	if A <= N // 2:
		print("Yes" if B <= ((N + 1) // 2) * (N - A) else "No")
	elif A < N:
		print("Yes" if B <= (N - A) ** 2 else "No")
	else:
		print("No")