MOD = 998244353
N = int(input())
ans = pow(2, N, MOD)
for i in range(N+2, N*2+1):
    ans *= i
    ans %= MOD
print(ans)