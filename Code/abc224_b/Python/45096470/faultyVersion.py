H, W = map(int, input().split())
A = [list(map(int, input().split())) for _ in range(H)]

flag = True

for h in range(H):
    for w in range(W):
        for i in range(h+1, H):
            for j in range(w+1, W):
                if A[h][w] + A[i][j] < A[i][w] + A[h][j] : flag = False

if flag : print("Yes")
else : print("No")
