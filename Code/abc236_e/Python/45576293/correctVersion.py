def check(arg, case):
    if case == 1:
        # 平均値をargにできるか
        B = [a - arg for a in A]
    else:
        # 中央値をargにできるか
        B = [1 if a >= arg else -1 for a in A]

    # dp[i][j]:= i番目まで見て、i+1番目を 選ぶ / 選ばない（1 / 0）ときの総和の最大値
    dp = [[0] * 2 for _ in range(n + 1)]
    for i, b in enumerate(B):
        dp[i + 1][0] = dp[i][1]
        dp[i + 1][1] = max(dp[i][0], dp[i][1]) + b

    return max(dp[-1]) > 0


n = int(input())
A = list(map(int, input().split()))

# 平均値の最大化

ok = 0
ng = 10**10

while abs(ok - ng) > 0.0001:
    mid = (ok + ng) / 2
    if check(mid, 1):
        ok = mid
    else:
        ng = mid
print(ok)

ok = 0
ng = 10**16
while abs(ok - ng) > 1:
    mid = (ok + ng) // 2
    if check(mid, 2):
        ok = mid
    else:
        ng = mid
print(ok)
