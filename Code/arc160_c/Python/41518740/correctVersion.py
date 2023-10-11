MOD = 998244353
N = int(input())
A = list(map(int,input().split()))
NX = 2 * 10 ** 5 + 100
X = [0] * NX
for a in A:
    X[a] += 1

dp =[1]
inc = 0
for i in range(1,NX):
    x = X[i]
    dp2 = [0] * ((x+inc)//2+1)
    tmp = 0
    for j in reversed(range((x+inc)//2+1)):
        if j <= x // 2:
            tmp += dp[0]
            tmp %= MOD
        else:
            tmp += dp[j*2-x]
            tmp %= MOD
        dp2[j] = tmp
    dp = dp2
    inc = (x + inc) //2
    #print(i,dp)

print(dp[0])