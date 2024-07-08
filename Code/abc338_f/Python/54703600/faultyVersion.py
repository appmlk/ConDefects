N,M=map(int,input().split())
INF=10**9
#Warshall-Froid
D=[[INF for _ in range(N)] for _ in range(N)]
for i in range(N):D[i][i]=0
for _ in range(M):
  u,v,d=map(int,input().split())
  D[u-1][v-1]=d
for j in range(N):
  for i in range(N):
    for k in range(N):
      D[i][k]=min(D[i][k],D[i][j]+D[j][k])
#dp[bit][i]:bitを訪問済、最後がiの最短距離
dp=[[INF for _ in range(N)] for _ in range(1<<N)]
for i in range(N):
  dp[1<<i][i]=0
for bit in range(1,1<<N):
  for j in range(N):
    if (bit>>j)&1:
      continue
    nbit=bit|(1<<j)
    for i in range(N):
      dp[nbit][j]=min(dp[nbit][j],dp[bit][i]+D[i][j])

ans=min(dp[-1])
print("No" if ans==INF else ans)