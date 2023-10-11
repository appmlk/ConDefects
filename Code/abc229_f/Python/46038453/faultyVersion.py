N = int(input())
A = list(map(int, input().split()))
B = list(map(int, input().split()))
INF = 10**18
# dp[i][j][k]: 頂点i番目まで見たときに、i番目が色j(0or1)で、頂点1が色k(0or1)の時の総和の最小値
# 頂点0 は色0 で固定する
dp = [[[INF] * 2 for _ in range(2)] for _ in range(N + 1)]
dp[0][0][0] = 0
dp[0][0][1] = 0
for i in range(N):
    if i == 0:
        # 頂点1が0と同じ色の場合、辺を消す
        dp[i+1][0][0] = A[0]
        dp[i+1][1][1] = 0
        continue

    dp[i+1][0][0] = min(dp[i][0][0] + A[i] + B[i-1], dp[i][1][0] + A[i])
    dp[i+1][0][1] = min(dp[i][0][1] + A[i] + B[i-1], dp[i][1][1] + A[i])
    dp[i+1][1][0] = min(dp[i][0][0] + B[i-1], dp[i][1][0] + B[i-1])
    dp[i+1][1][1] = min(dp[i][0][1], dp[i][1][1] + B[i-1])

# N における辺A は調査済み
ans = dp[N][0][0] + B[-1]
ans = min(ans, dp[N][0][1])
ans = min(ans, dp[N][1][0])
ans = min(ans, dp[N][1][1] + B[-1])
print(ans)