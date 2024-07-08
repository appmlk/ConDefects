N, T = [int(x) for x in input().split()]
A = [int(x) - 1 for x in input().split()]
boolss = []
for _ in range(N):
    bools = N * [False]
    boolss.append(bools)
bingo = False
for turn, a in enumerate(A):
    i = a // N
    j = a % N
    boolss[i][j] = True
    if all(boolss[i]):
        bingo = True
    vertical = True
    for n in range(N):
        if boolss[i][n] == False:
            vertical = False
            break
    if vertical:
        bingo = True
    if i == j:
        diagonal = True
        for n in range(N):
            if boolss[n][n] == False:
                diagonal = False
                break
        if diagonal:
            bingo = True
    if i == N - j - 1:
        diagonal = True
        for n in range(N):
            if boolss[n][N - n - 1] == False:
                diagonal = False
                break
        if diagonal:
            bingo = True
    if bingo:
        print(turn + 1)
        break
if not bingo:
    print(-1)