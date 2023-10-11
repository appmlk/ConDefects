n,d = map(int,input().split())
a = [0] + list(map(int,input().split()))
dp = [[0] * (1<<(2*d+1)) for _ in range(n+1)]
dp[0][0] = 1
mod = 998244353
for i in range(n):
    for s in range(1<<(2*d+1)):
        if a[i+1] != -1:
            if ((s>>1) & (1<<(a[i+1]-(i+1)+d))) == 0:
                dp[i+1][(s>>1) | (1<<(a[i+1]-(i+1)+d))] += dp[i][s]
                dp[i+1][(s>>1) | (1<<(a[i+1]-(i+1)+d))] %=mod
        else:
            for k in range(-d,d+1):
                if 1 <= i+1 + k <= n and ((s>>1) & (1<<(k+d))) ==0:
                    dp[i+1][(s>>1)|(1<<(k+d))] += dp[i][s]
                    dp[i+1][(s>>1)|(1<<(k+d))] %=mod
ans = 0
for s in range(1<<(2*d+1)):
    ans += dp[n][s]
    ans %=mod
print(ans)

