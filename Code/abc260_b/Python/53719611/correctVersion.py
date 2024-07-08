N, X, Y, Z = map(int, input().split())
A = list(map(int, input().split()))
B = list(map(int, input().split()))

Li1 = []

for i in range(X):
    AM = A.index(max(A))
    Li1.append(AM+1)
    A[AM], B[AM] = -10, -10

for i in range(Y):
    BM = B.index(max(B))
    Li1.append(BM+1)
    A[BM], B[BM] = -10, -10
    
C = []

for i in range(len(A)):
    C.append(A[i] + B[i])

for i in range(Z):
    CM = C.index(max(C))
    Li1.append(CM+1)
    C[CM] = -10

print(*sorted(Li1), sep="\n")