N = int(input())
XY = [[int(i) for i in input().split()] for _ in range(N)]

INF = 10**13
maC = 30
dp = [INF]*((N)*(maC+1))
def pos2ind(n,c):
    return n*(maC+1) + c

dp[0] = 0
for i in range(1, N):
    x,y = XY[i]
    for j in range(maC+1):
        if j>i-1:
            break
        for k in range(1, j+2):
            # print(f'{i,j,k = }')
            # print(f'{i-k = }')
            xx, yy = XY[i-k]
            dist2 = (x-xx)**2 + (y-yy)**2
            dist = dist2**.5
            # print(f'{dist2 = }')
            # print(f'{dp[pos2ind(i,j)] = }')
            # print(f'{dp[pos2ind(i-k,j-(k-1))]+dist2 = }')
            dp[pos2ind(i,j)] = min(dp[pos2ind(i,j)], dp[pos2ind(i-k,j-(k-1))]+dist)
ans = INF
for j in range(maC+1):
    # print(f'{j, dp[pos2ind(N-1,j)] = }')
    ans = min(ans, dp[pos2ind(N-1,j)] + 2**(j-1))
print(ans)