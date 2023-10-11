N = int(input())
A = list(map(int, input().split()))
B = list(set(A))
A.sort()
B.sort()

for i in range(4*N-1):
	if A[i] != B[i//4]:
		print(A[i]-1)
		exit()
print(A[-1])