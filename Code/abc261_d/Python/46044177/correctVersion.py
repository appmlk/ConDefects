n,m=map(int,input().split())
x=list(map(int,input().split()))
s=[list(map(int,input().split())) for i in range(m)]
ans=0
d=[0]*(n+1)
for i in range(m):
  d[s[i][0]]=s[i][1]
dp=[[0]*(n+1) for i in range(n+1)]
for i in range(n):
  for j in range(1,i+2):
    dp[i+1][j]=dp[i][j-1]+x[i]+d[j]
  dp[i+1][0]=max(dp[i])
print(max(dp[-1]))