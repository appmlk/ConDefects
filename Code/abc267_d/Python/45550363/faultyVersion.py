INT = lambda : int(input())
MI = lambda : map(int, input().split())
MI_DEC = lambda : map(lambda x : int(x)-1, input().split())
LI = lambda : list(map(int, input().split()))
LI_DEC = lambda : list(map(lambda x : int(x)-1, input().split()))

N, M = MI()
A = LI()

dp = [[-float('inf')]*(M+1) for i in range(N+1)]
dp[0][0] = 0

for i in range(N):
    for j in range(M+1):
        dp[i+1][j] = max(dp[i+1][j], dp[i][j])

        if j + 1 > M:
            continue

        dp[i+1][j+1] = max(dp[i+1][j+1], dp[i][j] + (j+1)*A[i])
            

ans = -1
for i in range(N+1):
    ans = max(ans, dp[i][M])

print(ans)