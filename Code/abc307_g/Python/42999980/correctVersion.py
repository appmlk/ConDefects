n = int(input())
aaa = list(map(int, input().split()))
x, y = divmod(sum(aaa), n)

INF = 1 << 60
dp = [INF] * (y + 1)
dp[0] = 0

tmp_sum = 0
for i in range(n):
    ndp = [INF] * (y + 1)
    diff = tmp_sum - i * x
    a = aaa[i]
    for j in range(min(i, y) + 1):
        d = diff - j  # i+1 への調整残し
        x0 = x - (a + d)
        ndp[j] = min(ndp[j], dp[j] + abs(x0))
        if j < y:
            ndp[j + 1] = min(ndp[j + 1], dp[j] + abs(x0 + 1))

    tmp_sum += aaa[i]
    dp = ndp

print(dp[y])
