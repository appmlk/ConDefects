N,A,B,P,Q=map(int,input().split())
mod=998244353

def sugoroku(X,start):
    dp=[[0 for _ in range(N+1)] for _ in range(N+1)]
    dp[0][start]=1
    for i in range(N):
        for j in range(N+1):
            for k in range(1,X+1):
                    denominator=pow(X,-1,mod)
                    if j+k>=N:
                        dp[i+1][N]+=dp[i][j]*denominator%mod
                    else:
                        dp[i+1][j+k]+=dp[i][j]*denominator%mod
    return dp

T_dp=sugoroku(P,A)
A_dp=sugoroku(Q,B)

answer=0
for i in range(N):
    answer+=(1-A_dp[i][-1])*T_dp[i+1][-1]
    answer%=mod

print(answer%mod)