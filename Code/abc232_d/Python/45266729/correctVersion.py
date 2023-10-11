H,W=map(int,input().split())
dp=[[-10**10]*W for i in range(H)]
S=[input() for i in range(H)]
dp[0][0]=1
for i in range(H):
  for j in range(W):
    if i==0 and j==0:
      continue
    if S[i][j]=='.':
      dp[i][j]=max(dp[i][j],dp[i-1][j]+1,dp[i][j-1]+1)
result=0
for i in range(H):
  w=max(dp[i])
  result=max(result,w)
print(result)