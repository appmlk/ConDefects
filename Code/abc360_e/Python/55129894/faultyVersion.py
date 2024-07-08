N, K = map(int, input().split())
MOD = 998244353

dp = [0]*(K+1)
dp[0] = 1
a = (N**2-2*N)%MOD*pow(N, -2, MOD)
b = 2*pow(N, -2, MOD)
for k in range(1, K+1):
    dp[k] = (dp[k-1]*a+b)%MOD

if N == 1:
    ans = 1
else:
    ans = dp[K] + (1-dp[K])*(N+2)//2

print(ans%MOD)
