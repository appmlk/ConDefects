n = int(input())

carp = [["#"]]
for k in range(1, n + 1):
    tmp = []
    for _ in range(3):
        for c in carp[k - 1]:
            tmp.append(c * 3)

    tmp = list(map(list, tmp))

    cen = 3**k // 2
    diff = 3 ** (k - 1) // 2
    for i in range(3**k):
        for j in range(3**k):
            if cen - diff <= i <= cen + diff and cen - diff <= j <= cen + diff:
                tmp[i][j] = "."
    carp.append(tmp)

for c in carp[n]:
    print(*c, sep="")
