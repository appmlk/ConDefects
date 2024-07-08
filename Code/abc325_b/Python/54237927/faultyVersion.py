#0509

N = int(input())
C = [0] * 24
for i in range(N):
    W, X = map(int, input().split())
    C[X] = W 
B = 0

for j in range(24):
    A = 0
    for k in range(9):
        A += C[(k + j) % 24]
    B = max(A, B)


print(B)
