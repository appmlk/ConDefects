N,C = map(int,input().split())
op = [tuple(map(int,input().split())) for _ in range(N)]
L = [[0]*2 for _ in range(N+1)]
n = C.bit_length()
L[0][0] = 0
L[0][1] = (1<<n)-1
for i in range(N):
    t,a = op[i]
    for j in range(2):
        if t == 1:
            L[i+1][j] = L[i][j]&a
        if t == 2:
            L[i+1][j] = L[i][j]|a
        if t == 3:
            L[i+1][j] = L[i][j]^a
ans = C
for i in range(N):
    tmp = 0
    for j in range(n):
        if ans&(1<<j):
            tmp += L[i+1][1]&(1<<j)
        else:
            tmp += L[i+1][0]&(1<<j)
    print(tmp)
    ans = tmp