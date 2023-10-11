h, w = map(int, input().split())
field = [list(map(int, input().split())) for _ in range(h)]
for i in range(h - 1):
    for j in range(w - 2):
        d = field[i][j]
        field[i][j] -= d
        field[i + 1][j] -= d
        field[i][j + 2] += d
        field[i + 1][j + 2] += d
for j in range(w - 1):
    for i in range(h - 2):
        d = field[i][j]
        field[i][j] -= d
        field[i][j + 1] -= d
        field[i + 2][j] += d
        field[i + 2][j + 1] += d

d = field[-2][-2]
field[-2][-2] -= d
field[-2][-1] -= d
field[-1][-2] -= d
field[-1][-1] -= d

for i in range(h - 1):
    for j in range(w - 1):
        di = (h - i) % 2
        dj = (w - j) % 2
        if di == 0:
            if dj == 0:
                if field[i][-1] > 0 and field[-1][j] > 0:
                    d = min(field[i][-1], field[-1][j])
                elif field[i][-1] < 0 and field[-1][j] < 0:
                    d = max(field[i][-1], field[-1][j])
                else:
                    continue
                field[i][j] -= d
                field[i][-1] -= d
                field[-1][j] -= d
                field[-1][-1] -= d
            else:
                if field[i][-1] > 0 and field[-1][j] < 0:
                    d = max(-field[i][-1], field[-1][j])
                elif field[i][-1] < 0 and field[-1][j] > 0:
                    d = min(-field[i][-1], field[-1][j])
                else:
                    continue
                field[i][j] -= d
                field[i][-1] += d
                field[-1][j] -= d
                field[-1][-1] += d
        else:
            if dj == 0:
                if field[i][-1] > 0 and field[-1][j] < 0:
                    d = min(field[i][-1], -field[-1][j])
                elif field[i][-1] < 0 and field[-1][j] > 0:
                    d = max(field[i][-1], -field[-1][j])
                else:
                    continue
                field[i][j] -= d
                field[i][-1] -= d
                field[-1][j] += d
                field[-1][-1] += d
            else:
                if field[i][-1] > 0 and field[-1][j] > 0:
                    d = max(-field[i][-1], -field[-1][j])
                elif field[i][-1] < 0 and field[-1][j] < 0:
                    d = min(-field[i][-1], -field[-1][j])
                else:
                    continue
                field[i][j] -= d
                field[i][-1] += d
                field[-1][j] += d
                field[-1][-1] -= d

ans = 0
for i in range(h):
    for j in range(w):
        ans += abs(field[i][j])
print(ans)
for row in field:
    print(*row)
