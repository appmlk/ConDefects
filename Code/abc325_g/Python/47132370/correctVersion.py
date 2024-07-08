s = input()
k = int(input())

n = len(s)
dp = [[0] * (n + 1) for _ in range(n + 1)]

for length in range(1, n + 1):
    for l in range(n - length + 1):
        r = l + length
        mi = length
        for mid in range(l + 1, r):
            mi = min(mi, dp[l][mid] + dp[mid][r])
        if s[l] == 'o':
            for mid in range(l + 1, r):
                if s[mid] == 'f' and dp[l + 1][mid] == 0:
                    mi = min(mi, dp[l + 1][mid] + max(0, dp[mid + 1][r] - k))
        dp[l][r] = mi

print(dp[0][n])
