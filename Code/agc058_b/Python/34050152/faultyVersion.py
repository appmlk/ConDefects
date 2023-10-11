MOD = 10**9+7
n = int(input())
arr = list(map(lambda x:n-int(x), input().split()))
dp = [[0]*n for i in range(n)]
dp[0][arr[0]] = 1
dp1 = [0]*n
dp1[0] = 1
for i in range(1, n):
    a = arr[i]
    j = i-1
    while j >= 0 and arr[j] > a:
        j -= 1
    j += 1
    if j == 0:
        dp[0][a] = 1
        dp1[0] += 1
        j += 1
    for k in range(j, i):
        dp[k][a] = dp1[k-1]
        dp1[k] += dp1[k-1]
        dp1[k] %= MOD
    dp[i][a] = dp1[i-1]
    if a:
        dp[i][0] = c = dp[i-1][0]
    for k in range(1, a):
        if dp[i-1][k]:
            c = (c+dp[i-1][k]) % MOD
            dp[i][k] = c
    dp1[i] = sum(dp[i])%MOD
print(dp1[-1])
    