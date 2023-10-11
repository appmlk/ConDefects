H, W = map(int, input().split())
As = [list(map(int, input().split())) for _ in range(H)]

for i in range(H - 1):
    for j in range(W - 1):
        tmp = As[i][j]
        for di in range(2):
            for dj in range(2):
                As[i + di][j + dj] -= tmp


def sgn(x):
    if x > 0:
        return 1
    elif x < 0:
        return -1
    else:
        return 0


for i in range(H - 1):
    parity_i = -1 if (H - 1 - i + 1) % 2 else 1
    for j in range(W - 1):
        parity_j = -1 if (W - 1 - j + 1) % 2 else 1
        elems = {As[i][W - 1] * parity_i, As[H - 1]
                 [j] * parity_j}
        s = {sgn(e) for e in elems}
        if len(s) == 1:
            t = s.pop()
            delta = min((t * e for e in elems))
            As[i][j] -= delta * t * parity_i * parity_j
            As[i][W - 1] -= delta * t * parity_i
            As[H - 1][j] -= delta * t * parity_j
            As[H - 1][W - 1] -= delta * t


s = 0
for row in As:
    s += sum((abs(e) for e in row))

print(s)
for row in As:
    print(*row)
