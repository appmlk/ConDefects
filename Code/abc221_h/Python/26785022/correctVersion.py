import sys
input = sys.stdin.readline

N,M=map(int,input().split())
mod=998244353

# ヤング図形を用いて考えるのが重要
# ヤング図形に置き換えた後はDP

DP=[[0]*(N+5) for i in range(N+5)]

# DP[i][j]で、今までの総和がi、今まで使った段差がj個。

# DP[i+j][j]+=DP[i][j] : 今までの段差全てに+1
# DP[i+k+j][j+k] += DP[i][j] : 今までの段差全てに+1し、さらにk<=M個一段のものを加える。


DP[1][1]=1
DP[1+M][1+M]=-1

for i in range(1,N+5):
    for j in range(1,N+5):
        DP[i][j]+=DP[i-1][j-1]
        DP[i][j]%=mod

        if i+j<N+5:
            DP[i+j][j]+=DP[i][j]
            DP[i+j][j]%=mod

        if i+M+1+j<N+5 and j+M+1<N+5:
            DP[i+M+1+j][j+M+1]-=DP[i][j]
            DP[i+M+1+j][j+M+1]%=mod


print(*DP[N][1:N+1])