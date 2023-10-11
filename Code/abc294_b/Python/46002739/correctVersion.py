H,W = map(int,input().split())
A = [[int(i) for i in input().split()]for j in range(H)]
ans = [["." for h in range(W)] for w in range(H)]

for i in range(H):
    for j in range(W):
        if A[i][j] == 0:
            continue
        ans[i][j] = chr(64 + A[i][j])

for i in range(H):
    for j in range(W):
        if W - 1 == j:
            print(ans[i][j])
        else:
            print(ans[i][j],end="")