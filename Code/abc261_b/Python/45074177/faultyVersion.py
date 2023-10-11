N = int(input())
A = [list(input()) for _ in range(N)]

for i in range(N):
	for j in range(N):
		if i != j and A[i][j] == 'W' and A[j][i] == 'L':
			continue
		elif i != j and A[i][j] == 'D' and A[j][i] == 'D':
			continue
		elif i == j:
			continue
		else:
			print('incorrect')
			exit()
print('correct')
		