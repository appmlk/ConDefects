n,m = map(int,input().split())
B = [list(map(int,input().split())) for _ in range(n)]

ok = True
for i in range(n):
    for j in range(m):
        if j+1 < m and B[i][j]+1 != B[i][j+1]:
            ok = False
        if i+1 < n and B[i][j]+7 != B[i+1][j]:
            ok = False
        if B[i][j]%7 == 0 and j+1 != m:
            ok = False

print('Yes' if ok else 'No')
