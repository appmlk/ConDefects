import math
N,M=map(int,input().split())
mod=998244353
l=(int(math.log2(M))+1)
if N<=l:
  dp=[[0]*l for _ in range(N)]
  X=[]
  for i in range(l):
    if 2**(i+1)-1<M:
      X.append((2**i)%mod)
      dp[0][i]=X[-1]
    else:
      X.append((M-2**i+1)%mod)
      dp[0][i]=X[-1]
    if i>0:
      dp[0][i]+=dp[0][i-1]
      dp[0][i]%=mod

  for i in range(1,N):
    for j in range(i,l):
      dp[i][j]=(dp[i][j-1]+dp[i-1][j-1]*X[j])%mod
  print(dp[-1][-1])
else:
  print(0)