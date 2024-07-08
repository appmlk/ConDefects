# 初日を休日
# dp[i][j] := i日目を考える、j日平日が続いている

# 10
# 10 10
# 10 10 10
# 10 10 10 10
# 10 10 1 10 10
# 10 10 1 1 10 10

n = int(input())
base_productivity = list(map(int, input().split()))
productivity = [0]
for i in range(n):
    productivity.append(productivity[-1] + base_productivity[i//2])

INF = float('inf')
dp = [[-INF]*n for _ in range(n)]
dp[0][0] = 0

for i in range(n-1):
    for j in range(n):
        if j < n - 1:
            dp[i+1][j+1] = dp[i][j]
        if dp[i][j] != -INF:
            dp[i+1][0] = dp[i][j] + productivity[j]

#print(productivity)
#print(dp)
ans = 0
for i in range(n):
    ans = max(ans, dp[n-1][i] + productivity[i])
print(ans)