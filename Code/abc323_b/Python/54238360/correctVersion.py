N = int(input())
S = [""] * N
for i in range(N):
    S[i] = input()

C = 0
A = [0] * N

for i in range(N):
    for j in range(N):
        if S[i][j] == "o":
            C += 1
    A[i] = [C, i+1]
    C = 0


Y = 0

for i in range(N):
    Y = max(Y, A[i][0])

for i in range(N):
    A[i][0] = Y - A[i][0]

A.sort(key=lambda d:(d[0], d[1]))

D = [0]*N

for k in range(N):
    D[k] = A[k][1]


print(*D)


