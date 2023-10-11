# 2023-05-20 12:43:31
n = int(input())

A = list(map(int, input().split()))


s = 4 * 10**5 + 10


B = [0] * s
mod = 998244353

for a in A:
    B[a] += 1

dp = [1]

for i in range(s):
    b = B[i]
    nl = b + (len(dp) - 1) // 2 + 1 + 1
    ndp = [0] * nl
    for j in range(len(dp)):
        ndp[b] += dp[j]
        ndp[b + j // 2 + 1] -= dp[j]

    for j in range(1, nl):
        ndp[j] += ndp[j - 1]
        ndp[j] %= mod
    dp = ndp[:-1]
print(dp[0])
