import sys

input = sys.stdin.readline

n = int(input())
dp = [0] * (n + 1)
for i in range(1, n + 1):
    tmp = 0
    for j in range(1, 7):
        if j < dp[i - 1]:
            tmp += dp[i - 1] / 6
        else:
            tmp += j / 6
    dp[i] = tmp

print(dp)
