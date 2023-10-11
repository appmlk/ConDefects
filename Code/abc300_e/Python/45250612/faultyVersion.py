N=int(input())
cnt=[0]*3
div=[2,3,5]
for i in range(3):
    x=div[i]
    while True:
        if N%x:
            break
        cnt[i]+=1
        N//=x
if N>1:
    print(0)
    
else:
    a,b,c=cnt
    dp=[[[0]*(c+1) for j in range(b+1)] for i in range(a+1)]
    mod=998244353
    dp[0][0][0]=1
    inv=pow(5,mod-2,mod)
    for i in range(a+1):
        for j in range(b+1):
            for k in range(c+1):
                dp[i][j][k]%=mod
                if i+1<=a:
                    dp[i+1][j][k]+=dp[i][j][k]*inv%mod
                if j+1<=b:
                    dp[i][j+1][k]+=dp[i][j][k]*inv%mod
                if i+2<=a:
                    dp[i+2][j][k]+=dp[i][j][k]*inv%mod
                if k+1<=c:
                    dp[i][j][k+1]=dp[i][j][k]*inv%mod
                if i+1<=a and j+1<=b:
                    dp[i+1][j+1][k]+=dp[i][j][k]*inv%mod
    print(dp[a][b][c])