n,m=map(int,input().split())
A=list(map(int,input().split()))
dp=[[-(10**13) for i in range(m+1)]for j in range(n+1)]
nowmax=2
for i in range(1,n+1):
  a=A[i-1]
  before=dp[i-1]
  for j in range(1,nowmax):
    if j==1:
      if before[j]<a:
        dp[i][j]=a
      else:
        dp[i][j]=before[j]
    else:
      if before[j]<before[j-1]+a*j:
        dp[i][j]=before[j-1]+a*j
      else:
        dp[i][j]=before[j]
    if nowmax<m+1:
      nowmax+=1
print(dp[-1][-1])