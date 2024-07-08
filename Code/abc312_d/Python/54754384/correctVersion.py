s = input()
n = len(s)
mod = 998244353
dp = [[0 for i in range(3001)] for i in range(3000)]
if s[0] == ')':
    print(0)
else:
    dp[0][1] = 1
    for i in range(1,n):
        for j in range(i+2):
            if s[i] == '(':
                if j-1 >= 0:
                    dp[i][j] = dp[i-1][j-1]
            elif s[i] == ')':
                if j+1 <= i:
                    dp[i][j] = dp[i-1][j+1]
            else:
                if j-1 >= 0:
                    dp[i][j] = dp[i-1][j-1]
                if j+1 <= i:
                    dp[i][j] = (dp[i][j] + dp[i-1][j+1])%mod
    print(dp[n-1][0])