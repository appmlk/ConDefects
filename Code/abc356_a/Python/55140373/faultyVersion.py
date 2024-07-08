
N, L, R = list(map(int, input().split()))
A = list(range(1, N+1))
L-=1

A[L:R] = A[L:R][::-1]
print(A)
