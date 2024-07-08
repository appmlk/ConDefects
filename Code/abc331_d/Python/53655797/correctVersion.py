def calc_area(y, x):
    ret = score[N][N] * (x//N) * (y//N)
    ret += score[N][x%N] * (y//N)
    ret += score[y%N][N] * (x//N)
    ret += score[y%N][x%N]
    return ret

N, Q = map(int, input().split())
P = [list(input()) for i in range(N)]
score = [[0] * (N+1) for i in range(N+1)]
ABCD = [list(map(int, input().split())) for i in range(Q)]

for i in range(1, N+1):
    for j in range(1, N+1):
        if P[i-1][j-1] == "B":
            score[i][j] = 1

for i in range(N+1):
    for j in range(1, N+1):
        score[i][j] += score[i][j-1]

for j in range(N+1):
    for i in range(1, N+1):
        score[i][j] += score[i-1][j]

for a, b, c, d in ABCD:
    c += 1
    d += 1
    print(calc_area(c, d) -calc_area(a, d) -calc_area(c, b) + calc_area(a, b))