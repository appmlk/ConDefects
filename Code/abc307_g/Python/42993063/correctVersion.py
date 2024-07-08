inf = 10 ** 18

n = int(input())

a = list(map(int,input().split()))

dp = [[inf] * (n + 1) for _ in range(n + 1)]

dp[0][0] = 0
asumm = 0
s = sum(a)
one = s // n

for i in range(1, n + 1):
    asumm += a[i - 1]
    for j in range(n):
        if i == j == 0:
            continue
        dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j]) + abs(asumm - i * one - j)

print(dp[n][s % n])