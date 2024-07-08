n,m,k=map(int,input().split())
a=list(map(int,input().split()))
mod=998244353

cnt=[0]*(m+1)

for i in a:
  cnt[i]+=1
  
q=pow(m,-cnt[0],mod)
 
p=[1]
pr=[1]

for i in range(1,n+1):
  p.append(p[-1]*i%mod)
  pr.append(pow(p[-1],-1,mod))
  
now=0  
ans=0
for i in range(1,m+1):
  if now>=k:
    break
  for j in range(k-now):
    ans+=p[cnt[0]]*pr[cnt[0]-j]%mod*pr[j]*pow(i-1,j,mod)%mod*pow(m-i+1,cnt[0]-j,mod)%mod
    ans%=mod
  now+=cnt[i]  
  
print(ans*q%mod)