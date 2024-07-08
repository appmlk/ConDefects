"""
cf. https://atcoder.jp/contests/abc288/editorial/5659
"""
INF = 10**12
def solve(n, m, a, c, x):
    x = [_-1 for _ in x]
    # i番目の商品までで、j個の商品を買ったときに、必要なコスト
    cost = [[INF] * n for i in range(n)]
    for i in range(n):
        cost[i][0] = c[i]
        for j in range(1, i+1):
            cost[i][j] = min(cost[i][j-1], c[i-j])

    # 購入が必須かどうか
    required = [False] * n
    for i in x:
        required[i] = True

    dp = [[INF] * (n+1) for i in range(n+1)] # (現在の商品, 購入した数) => 最小金額
    dp[0][0] = 0
    for i in range(n):
        for j in range(i+1):
            dp[i+1][j+1] = min(dp[i+1][j+1], dp[i][j] + a[i] + cost[i][j])
            if not required[i]: # 商品iを買わない
                dp[i+1][j] = min(dp[i+1][j], dp[i][j])

    return min(dp[n][m:])

n, m = map(int, input().split())
a = [*map(int, input().split())]
c = [*map(int, input().split())]
x = [*map(int, input().split())]
print(solve(n, m, a, c, x))