from collections import deque

n,m=map(int,input().split())
vuw=[list(map(int,input().split())) for i in range(m)]
INF=10**18
wf=[[INF]*n for i in range(n)]
for v,u,w in vuw:
  v-=1
  u-=1
  wf[v][u]=w
  
for i in range(n):
  wf[i][i]=0

for k in range(n):
  for i in range(n):
    for j in range(n):
      wf[i][j]=min(wf[i][j], wf[i][k]+wf[k][j])

dp=[[INF]*n for i in range(2**n)]
for i in range(n):
  dp[1<<i][i]=0

for k in range(2**n):
  for i in range(n):
    if not (k>>i)&1: continue
    for j in range(n):
      if (k>>j)&1: continue
      if wf[i][j]==INF: continue
      dp[k|(1<<j)][j]=min(dp[k|(1<<j)][j], dp[k][i]+wf[i][j])

ans=min(dp[(2**n)-1])
if INF<=ans: print("No")
else: print(ans)
