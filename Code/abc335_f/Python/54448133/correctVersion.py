n=int(input())
a=list(map(int,input().split()))
mod=998244353

b=500

dp1=[[0]*(b) for _ in range(n)]
dp2=[0]*n
dp2[0]=1

if a[0]<b:
  if a[0]<n:
    dp1[a[0]][a[0]]=1
else:
  for i in range(1,(n-1)//a[0]+1):
    dp2[i*a[0]]=1
    
for i in range(1,n):
  dp2[i]+=sum(dp1[i])
  dp2[i]%=mod
  for j in range(b):
    if i+j<n:
      dp1[i+j][j]+=dp1[i][j]
      dp1[i+j][j]%=mod
  if i+a[i]>=n:
    continue
  if a[i]<b:
    dp1[i+a[i]][a[i]]+=dp2[i]
    dp1[i+a[i]][a[i]]%=mod
  else:
    for j in range(1,(n-i-1)//a[i]+1):
      dp2[i+j*a[i]]+=dp2[i]
      dp2[i+j*a[i]]%=mod
  
print(sum(dp2)%mod)