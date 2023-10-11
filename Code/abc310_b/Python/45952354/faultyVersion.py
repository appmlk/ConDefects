N, M = map(int, input().split())
P = [None] * N
C = [None] * N
F = [None] * N
for i in range(N):
    tmp = list(map(int, input().split()))
    P[i], C[i], F[i] = tmp[0], tmp[1], set(tmp[2:])

flag = False
for i in range(N):
    for j in range(N):
        if P[i] >= P[i] and F[j] >= F[i] and (P[i] > P[j] or (F[i] - F[j])): 
            flag = True
            break
    if flag:
        break

if flag:
    print("Yes")
else:
    print("No")
