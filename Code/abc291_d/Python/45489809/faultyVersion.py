n=int(input())
mod=998244353 
c=[]

for i in range(n):
    a,b=map(int,input().split())
    c.append((a,b))
if n==1:
    print(0)
else:
    dp=[[0]*2 for i in range(n)]
    for j in range(2):
        if c[1][j]!=c[0][j]:
            dp[1][j]+=1
        if c[1][j]!=c[0][(j+1)%2]:
            dp[1][j]+=1
    
    for i in range(1,n-1):
        for j in range(2):
            if c[i+1][j]!=c[i][j]:
                dp[i+1][j]+=dp[i][j]
                dp[i+1][j]%=mod 
            if c[i+1][j]!=c[i][(j+1)%2]:
                dp[i+1][j]+=dp[i][(j+1)%2]
                dp[i+1][j]%=mod

    ans=dp[n-1][0]+dp[n-1][1]
    print(ans%mod)