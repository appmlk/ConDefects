N = int(input())
XYZ = [[int(x) for x in input().split()] for _ in range(N)]
lst = []
total = 0
for x, y, z in XYZ:
    lst.append((max((y-x)//2+1, 0), z))
    total += z
# dp[i][j]: i個目までの選挙区を見て、j議席を獲得するときに必要な最小コスト
dp = [[10**18]*(total+1) for _ in range(N+1)]
dp[0][0] = 0
for i, (c, w) in enumerate(lst):
    for j in range(total+1):
        dp[i+1][j] = min(dp[i+1][j], dp[i][j])
        if j+w <= total:
            dp[i+1][j+w] = min(dp[i+1][j+w], dp[i][j]+c)
print(min(dp[N][total//2+1:]))
