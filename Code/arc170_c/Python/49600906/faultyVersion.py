MOD = 998244353

n,m = map(int, input().split())
s = list(map(int, input().split()))

if m == 0:
    ok = True
    for i in range(n):
        if i == 0:
            if s[i] == 0:
                ok = False
                break
        else:
            if s[i] == 1:
                ok = False
                break
    
    if ok:
        print(1)
    else:
        print(0)
    exit()

dp = [[0 for i in range(n+1)] for i in range(n+1)]
dp[0][0] = 1

for i in range(n):
    if s[i] == 0:
        for j in range(n):
            dp[i+1][j] += dp[i][j] * j
            dp[i+1][j] %= MOD

            dp[i+1][j+1] += dp[i][j] * max(m-j, 0)
            dp[i+1][j+1] %= MOD
        
        dp[i+1][n] += dp[i][n] * n
        dp[i+1][n] %= MOD
    else:
        for j in range(n):
            dp[i+1][j+1] += dp[i][j]
    
    # print(dp)

ans = 0
for j in range(1,n+1):
    ans += dp[n][j]
    ans %= MOD

print(ans)