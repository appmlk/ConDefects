n, m = 9, 3
a = [list(map(int, input().split())) for _ in range(n)]

row = [set() for _ in range(n)]
col = [set() for _ in range(n)]
block = [[set() for j in range(m)] for i in range(m)]
for i in range(n):
    for j in range(n):
        row[i].add(a[i][j])
        col[j].add(a[i][j])
        block[i//m][j//m].add(a[i][j])

if (
    all(len(r) == 9 for r in row)
    and all(len(c) == 9 for c in col)
    and all(all(len(b) == 9 for b in br) for br in block)
):
    print("Yes")
else:
    print("No")