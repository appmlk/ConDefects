N,D = map(int, input().split())
A =[int(i) for i in input().split()]


mod = 998244353
S = 1<<(2*D+2)
dp = [[0]*(S) for _ in range(N+1)]
dp[0][(1<<(D+1)) - 1] = 1
for i,a in enumerate(A):
    if a>-1:
        for s in range(S):
            if s&1==0:
                continue
            t = s>>1
            diff = a-(i+1) + D
            if t>>diff&1:
                continue
            dp[i+1][t|(1<<diff)] += dp[i][s]
            dp[i+1][t|(1<<diff)] %= mod
    else:
        for s in range(S):
            if s&1==0:
                continue
            t = s>>1
            for diff in range(0,2*D+1):
                if t>>diff&1:
                    continue
                dp[i+1][t|(1<<diff)] += dp[i][s]
                dp[i+1][t|(1<<diff)] %= mod
# for i in range(N+1):
#     print(dp[i][:30])
print(dp[-1][(1<<(D+1))-1])