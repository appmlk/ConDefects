N, X = map(int, input().split())
AB = [tuple(map(int, input().split())) for _ in [0]*N]

dp = [[0]*(N+1), [0]*(N+1)]

ans = 10**20
for i in range(N):
    dp[0][i+1] = dp[0][i] + AB[i][0]+AB[i][1]
    dp[1][i+1] = dp[0][i+1] + AB[i][1]*(max(0, X-i-1))
    ans = min(ans, dp[1][i+1])

print(ans)
