x, y, z = map(int, input().split())
s = input()

dp = [[0]*(len(s)) for _ in range(2)]
if s[0] == "A":
    dp[0][0] = z+x
    dp[1][0] = y
else:
    dp[0][0] = z+y
    dp[1][0] = x
for j in range(1, len(s)):
    if s[j] == "A":
        dp[0][j] = min(dp[0][j-1]+x, dp[1][j-1]+z+x)

        dp[1][j] = min(dp[0][j-1]+z+y, dp[1][j-1]+y)

    else:
        dp[0][j] = min(dp[0][j-1]+y, dp[1][j-1]+z+y)
        dp[1][j] = min(dp[0][j-1]+z+x, dp[1][j-1]+x)
print(min(dp[0][-1], dp[1][-1]))