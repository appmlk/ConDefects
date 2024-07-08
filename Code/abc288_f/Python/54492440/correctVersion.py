n=int(input())
x=input()
mod=998244353

dp=0
s=1

for i in range(n):
  k=int(x[i])
  dp=(dp*10+s*k)%mod
  s+=dp
  s%=mod
  
print(dp)