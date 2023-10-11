N, X = [int(x) for x in input().split()]
AB = [[int(i) for i in input().split()] for _ in range(N)]

inf = 10**18
dp = [[0, inf] for _ in range(N + 1)]

min_b = inf
for i, (a, b) in enumerate(AB):
    dp[i + 1][1] = min(dp[i][1], b)
    dp[i + 1][0] = dp[i][0] + a + b

ans = inf

for i in range(1, N + 1):
    second_play = dp[i][1]
    cur_time = dp[i][0] + max((X - i), 0)*second_play
    ans = min(ans, cur_time)

print(ans)
