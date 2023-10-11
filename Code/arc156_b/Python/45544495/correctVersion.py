n,k=map(int,input().split())
a=list(map(int,input().split()))
from collections import defaultdict as df
d=df(int)
for i in range(n):
  d[a[i]]+=1
mex=[0]*(k+1)
now=0
for i in range(k+1):
  while d[now]>0:
    now+=1
  mex[i]=now
  now+=1
ans=0
fib=[1]*(n+k)
mod=998244353
for i in range(n+k-1):
  fib[i+1]=fib[i]*(i+2)%mod
def comb(a,b):
  if b<=0:
    return 1
  elif a<b:
    return 0
  elif a==b:
    return 1
  return fib[a-1]*pow(fib[a-b-1],-1,mod)%mod*pow(fib[b-1],-1,mod)%mod
ans=0
for i in range(k+1):
  ans+=comb(mex[i]+k-i-1,k-i)
  ans%=mod
print(ans)