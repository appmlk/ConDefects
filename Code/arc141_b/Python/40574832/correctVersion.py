n,m=map(int,input().split())
binm=bin(m)[2:]
if n>len(binm):
    print(0)
    exit()
num=[0]*len(binm)
num[-1]=m-(1<<(len(binm)-1))+1
for i in range(len(binm)-1):
    num[i]=1<<i
dp=[[0]*len(binm) for i in range(n)]
dp[0]=num
for i in range(n-1):
    for j in range(len(binm)):
        for k in range(j+1,len(binm)):
            dp[i+1][k]+=dp[i][j]*num[k]
            dp[i+1][k]%=998244353
print(sum(dp[-1])%998244353)