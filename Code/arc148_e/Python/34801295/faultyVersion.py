N,K=map(int,input().split())
A=[int(x) for x in input().split()]
mod=998244353
A.sort()
d=1
from collections import Counter
C=Counter(A)
for c in C:
  for i in range(C[c]):
    d*=i+1
    d%=mod
    
dp=[1,0]
t=0
j=N-1
for i in range(N):
  while j>=i:
    if A[j]+A[i]>=K:
      t+=1
    else:
      break
    j-=1 
  if i==j:
    break    
  dp2=[0,0]
  dp2[0]=dp[0]*t*(t-1)%mod
  dp2[1]=(dp[0]*t*2 +dp[1]*(t*(t-1)+t*2)) %mod
  dp=dp2.copy()

  t-=1
  
ans1=dp[0]
for i in range(t+1):
  ans1*=i+1
  ans1%=mod
ans2=dp[1]
for i in range(t+1):
  ans2*=i+1
  ans2%=mod  
ans=ans1+ans2  
ans*=pow(d,mod-2,mod)
print(ans%mod)
         
  
