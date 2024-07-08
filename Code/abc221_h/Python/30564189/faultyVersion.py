import sys
readline = sys.stdin.readline

n,m = map(int,readline().split())
dp = [[0]*(n+1) for _ in range(n+1)]
acc = [[0]*(n+1) for _ in range(n+1)]
dp[0][0] = 1
acc[0] = [1]*(n+1)
MOD = 9998244353

for x in range(1,n+1):
    dpx = dp[x]
    for y in range(1,n+1):
        dpx[y] = (acc[x-y][y] - (0 if y-m-1 < 0 else acc[x-y][y-m-1]))%MOD
        acc[x][y] = (acc[x][y-1]+dpx[y])%MOD

print(*dp[n][1:],sep="\n")