MOD = 998244353
M,N=map(int,input().split())
X=list(map(int,input().split()))
canadd=[[] for _ in range(M)]

for i in range(M):
    canadd[X[i]-1].append(i)

dp=[[0]*2**M for _ in range(N+1)]
dp[0][2**M-1]=1
for i in range(N):
    for j in range(2**M):
        for k in range(M):
            if j&(1<<k):
                next=j
                next^=(1<<k)
                for c in canadd[k]:
                    next|=(1<<c)
                    
                dp[i+1][next]+=dp[i][j]
                dp[i+1][next]%=MOD

print(sum(dp[-1]))