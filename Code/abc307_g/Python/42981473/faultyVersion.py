n = int(input())
a = list(map(int,input().split()))
pre = a[::1]
for i in range(1,n):
    pre[i] += pre[i-1]
sm = sum(a)

x = sm//n
y = (sm+n-1)//n

dp = [[10**18]*(n+1) for i in range(n+1)]
dp[0][0] = 0
for i in range(1,n):
    now = pre[i-1]
    for j in range(i+1):
        dif = abs(now - (x*j + y*(i-j)))
        dp[i][j] = min(dp[i-1][j-1], dp[i-1][j]) + dif


ans = 10**15
for i in range(n+1):
    if i*x + y*(n-i-1) == sm-y or i*x + y*(n-i-1) == sm-x:
        ans = min(ans, dp[n-1][i])

print(ans)