H,W,K=map(int, input().split())
y1,x1,y2,x2=map(int, input().split())

dp = [[0]*4 for i in range(K+1)]
mod=998244353
if x1==x2 and y1==y2:
    dp[0][3]=1
elif x1==x2:
    dp[0][1]=1
elif y1==y2:
    dp[0][2]=1
else:
    dp[0][0]=1

for i in range(K):
    dp[i+1][0]+=dp[i][1]*(W-1)%mod
    dp[i+1][0]%=mod
    dp[i+1][0]+=dp[i][2]*(H-1)%mod
    dp[i+1][0]%=mod
    dp[i+1][0]+=dp[i][0]*(H+W-4)%mod
    dp[i+1][0]%=mod

    dp[i+1][1]+=dp[i][3]*(H-1)%mod
    dp[i+1][1]%=mod
    dp[i+1][1]+=dp[i][0]%mod
    dp[i+1][1]%=mod
    dp[i+1][1]+=dp[i][1]*(H-2)%mod
    dp[i+1][1]%=mod



    dp[i+1][2]+=dp[i][3]*(W-1)%mod
    dp[i+1][2]%=mod
    dp[i+1][2]+=dp[i][0]%mod
    dp[i+1][2]%=mod
    dp[i+1][2]+=dp[i][2]*(W-2)%mod
    dp[i+1][2]%=mod

    dp[i+1][3]+=dp[i][1]%mod
    dp[i+1][3]%=mod
    dp[i+1][3]+=dp[i][2]%mod
    dp[i+1][3]%=mod
print(dp[-1][-1]%mod)