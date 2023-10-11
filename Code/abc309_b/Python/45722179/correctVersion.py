N = int(input())
A = [list(input()) for _ in range(N)]
rotate_list = []
for i in range(N):
    rotate_list.append(A[0][i])

for i in range(1, N):
    rotate_list.append(A[i][N-1])

for i in reversed(range(0, N-1)):
    rotate_list.append(A[N-1][i])

for i in reversed(range(1, N-1)):
    rotate_list.append(A[i][0])


cnt = -1
for i in range(N):
    A[0][i] = rotate_list[cnt]
    cnt += 1

for i in range(1, N):
    A[i][N-1] = rotate_list[cnt]
    cnt += 1
    

for i in reversed(range(0, N-1)):
    A[N-1][i] = rotate_list[cnt]
    cnt += 1

for i in reversed(range(1, N-1)):
    A[i][0] = rotate_list[cnt]
    cnt += 1
    

for i in range(N):
    for j in range(N):
        print(A[i][j], end="")
    print("")