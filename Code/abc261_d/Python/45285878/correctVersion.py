n,m = map(int, input().split())
xl = list(map(int, input().split()))
cyl = [list(map(int, input().split())) for _ in range(m)]
yl = [0] * n  
for c,y in cyl:
    yl[c-1] = y

dp = [[0] * (n) for i in range(n)]

for i in range(n):
    if i == 0:
        dp[i][0] = xl[0] + yl[0]
    else:
        for j in range(1,i+1):
            dp[i][j] = dp[i-1][j-1] + xl[i] + yl[j]
        if i != 1:
            dp[i][0] = max(dp[i-2]) + xl[i] + yl[0]
print(max(dp[n-1]))