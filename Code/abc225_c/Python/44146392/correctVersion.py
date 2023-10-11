N, M = map(int, input().split())
B = [ list(map(int, input().split())) for _ in range(N) ]
flag = True
lim = 10**100*7+7
j0 = (B[0][0]-1)%7
if j0 + M-1 >= 7:
    flag = False
i0 = (B[0][0])//7
for i in range(N):
    for j in range(M):
        if B[i][j] != B[0][0] + i*7 + j or B[i][j] > lim:
            flag = False
if flag:
    print("Yes")
else:
    print("No")