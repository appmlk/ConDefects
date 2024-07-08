s=input()

n=len(s)
mod=998244353

dp=[[0]*27 for _ in range(n+1)]
dp2=[0]*(n+1)
dp[0][0]=1

check=[0]*26
cnt=0

x=ord('A')

for i in range(n):
  if s[i]=='?':
    for j in range(27):
      dp[i+1][j]+=dp[i][j]*26
      dp2[i+1]+=dp[i][j]*j
      dp[i+1][j]%=mod
      dp2[i+1]%=mod
    for j in range(26):
      dp[i+1][j+1]+=dp[i][j]*(26-j)
      dp[i+1][j+1]%=mod
  elif s[i].isupper():
    if check[ord(s[i])-x]:
      dp2[i+1]=sum(dp[i])%mod
      break
    for j in range(26):
      if j<cnt: 
        continue
      if j==0:
        dp[i+1][j+1]=dp[i][j]
      else:
        dp[i+1][j+1]+=dp[i][j]*(25-j+1)*pow(26-cnt,-1,mod)%mod
        dp2[i+1]+=dp[i][j]*(1-(25-j+1)*pow(26-cnt,-1,mod))%mod
    dp2[i+1]+=dp[i][-1]
    check[ord(s[i])-x]+=1
    cnt+=1
  else:
    for j in range(27):
      dp[i+1][j]=dp[i][j]
      
dp3=[[0,0] for _ in range(n+1)]

dp3[-1][0]=1

for i in reversed(range(n)):
  if s[i]=='?':
    dp3[i][0]+=dp3[i+1][0]*26
    dp3[i][1]+=dp3[i+1][0]*26
    dp3[i][1]+=dp3[i+1][1]*26
    dp3[i][0]%=mod
    dp3[i][1]%=mod
  elif s[i].isupper():
    dp3[i][1]=sum(dp3[i+1])%mod
  else:
    dp3[i][0]=dp3[i+1][0]

ans=sum(dp[-1])%mod

for i in range(n+1):
  ans+=dp2[i]*sum(dp3[i])
  ans%=mod

print(ans)