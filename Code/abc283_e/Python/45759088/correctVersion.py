H,W = map(int,input().split())
A = [list(map(int,input().split())) for _ in range(H)]
maze = [[1]*4 for _ in range(H)]
for i in range(H):
    for j in range(W):
        F = True
        if j >= 1:
            if A[i][j] == A[i][j-1]:
                F = False
        if j <= W-2:
            if A[i][j] == A[i][j+1]:
                F = False
        if F:
            if i == 0:
                x = A[i][j]^A[i+1][j]
                if x == 1:
                    maze[i][0],maze[i][2] = 0,0
                else:
                    maze[i][1],maze[i][3] = 0,0
            elif i == H-1:
                x = A[i][j]^A[i-1][j]
                if x == 1:
                    maze[i][0],maze[i][1] = 0,0
                else:
                    maze[i][2],maze[i][3] = 0,0
            else:
                x,y = A[i][j]^A[i-1][j],A[i][j]^A[i+1][j]
                maze[i][3^((x<<1)+y)] = 0
inf = 10**12
dp = [[inf]*4 for _ in range(H+1)]
dp[0][1],dp[0][3] = 1,1
dp[0][0],dp[0][2] = 0,0
for i in range(1,H+1):
    for j in range(4):
        for k in range(4):
            if (k&1)^(j>>1&1) == 1:
                continue
            if maze[i-1][j^k]:
                if i == H:
                    dp[i][j] = min(dp[i][j],dp[i-1][k])
                else:
                    dp[i][j] = min(dp[i][j],dp[i-1][k]+(j&1))
print(min(dp[-1]) if min(dp[-1]) < inf else -1)