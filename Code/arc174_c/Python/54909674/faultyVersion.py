import sys
sys.setrecursionlimit(10**6)
def e(x):
    if dp[x] != -1:
        return dp[x]
    if x == 0:
        dp[x] = 0
        return 0
    if x == 1:
        dp[x] = n*(n-1)*pow(2*n-1,mod-2,mod)%mod
        return dp[x]
    inv = pow(2*n*x-x**2,mod-2,mod)
    res = inv*(x*(x-1)*e(x-2) + (2*n*x-2*x*x+x)*e(x-1) + (n-x))%mod
    dp[x] = res
    return res

n= int(input())
mod = 998244353
dp = [-1]*(n+1)
dp[0] = 0
e(n)
print(dp[n],dp[n-1])