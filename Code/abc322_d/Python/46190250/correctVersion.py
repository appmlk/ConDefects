import itertools
import sys

P = []
for i in range(3):
    p = []
    for j in range(4):
        p.append(list(map(lambda x: int(x == '#'), input())))
    P.append(p)

cnt = 0
for p in P:
    cnt += sum(itertools.chain.from_iterable(p))
if cnt != 16:
    print('No')
    sys.exit()


def rotate(piece: list, n):
    if n == 0:
        return piece
    if n == 1:
        return list(map(list, zip(*piece[::-1])))
    if n == 2:
        return list(reversed([p[::-1] for p in piece]))
    if n == 3:
        return list(map(list, zip(*piece)))[::-1]


R = []
for p in P:
    R.append([rotate(p, i) for i in range(4)])


def slide(p, n):
    rotated = R[p][n // 49]
    row = (n % 49) // 7 - 3
    col = (n % 49) % 7 - 3
    result = [[0] * 4 for _ in range(4)]

    for i, r in enumerate(rotated):
        for j, e in enumerate(r):
            if 0 <= i + row <= 3 and 0 <= j + col <= 3:
                result[i + row][j + col] = e
            elif e:
                return None
    return result


def can_merge(p1, p2, p3):
    for i in range(4):
        for j in range(4):
            if p1[i][j] + p2[i][j] + p3[i][j] != 1:
                return False
    return True


for i in range(196):
    p1 = slide(0, i)
    if p1 is None:
        continue
    for j in range(196):
        p2 = slide(1, j)
        if p2 is None:
            continue
        for k in range(196):
            p3 = slide(2, k)
            if p3 is None:
                continue
            if can_merge(p1, p2, p3):
                print('Yes')
                # print(*p1, sep='\n')
                # print()
                # print(*p2, sep='\n')
                # print()
                # print(*p3, sep='\n')
                sys.exit()
print('No')
