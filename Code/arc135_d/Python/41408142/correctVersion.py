import sys
input = sys.stdin.readline

H, W = map(int, input().split())
A = [list(map(int, input().split())) for _ in range(H)]
rowsum = [0]*H
colsum = [0]*W
for i in range(H):
    for j in range(W):
        if (i+j) % 2:
            A[i][j] *= -1
        rowsum[i] += A[i][j]
        colsum[j] += A[i][j]

rowidx = list(range(H))
colidx = list(range(W))
rowidx.sort(key=lambda i: rowsum[i], reverse=True)
colidx.sort(key=lambda j: colsum[j], reverse=True)

rowsum.sort(reverse=True)
colsum.sort(reverse=True)

rows = [0]*2
cols = [0]*2
for i in range(H):
    if rowsum[i] >= 0:
        rows[0] += rowsum[i]
    else:
        rows[1] -= rowsum[i]
for j in range(W):
    if colsum[j] >= 0:
        cols[0] += colsum[j]
    else:
        cols[1] -= colsum[j]

transpose = False
if rows[1] > cols[1]:
    transpose = True
    H, W = W, H
    rowsum, colsum = colsum, rowsum
    rowidx, colidx = colidx, rowidx
    rows, cols = cols, rows
    A = [[A[j][i] for j in range(W)] for i in range(H)]

B = [[0] * W for _ in range(H)]
for i in range(H):
    for j in range(W):
        B[i][j] = A[rowidx[i]][colidx[j]]

r = H-1
c = W-1

while r >= 0 and c >= 0 and colsum[c] < 0:
    if rowsum[r] > 0 or colsum[c] >= rowsum[r]:
        for i in range(r):
            x = B[i][c]
            if x != 0:
                B[i][c] -= x
                B[i+1][c] += x
                B[i][c-1] += x
                B[i+1][c-1] -= x
        rowsum[r] -= colsum[c]
        c -= 1
    else:
        for j in range(c):
            x = B[r][j]
            if x != 0:
                B[r][j] -= x
                B[r][j+1] += x
                B[r-1][j] += x
                B[r-1][j+1] -= x
        colsum[c] -= rowsum[r]
        rowsum[r] = 0
        r -= 1

r = H-1
while r >= 0 and c >= 0:
    if colsum[c] <= rowsum[r]:
        for i in range(r):
            x = B[i][c]
            if x != 0:
                B[i][c] -= x
                B[i+1][c] += x
                B[i][c-1] += x
                B[i+1][c-1] -= x
        rowsum[r] -= colsum[c]
        c -= 1
    else:
        for j in range(c):
            x = B[r][j]
            if x != 0:
                B[r][j] -= x
                B[r][j+1] += x
                B[r-1][j] += x
                B[r-1][j+1] -= x
        colsum[c] -= rowsum[r]
        rowsum[r] = 0
        r -= 1

for i in range(H):
    for j in range(W):
        A[rowidx[i]][colidx[j]] = B[i][j]

if transpose:
    H, W = W, H
    A = [[A[j][i] for j in range(W)]for i in range(H)]
ans = 0
for i in range(H):
    for j in range(W):
        ans += abs(A[i][j])
        if (i+j) % 2:
            A[i][j] *= -1
print(ans)
for row in A:
    print(*row)
