from itertools import accumulate
T=int(input())
for _ in range(T):
  D,K,X=map(int,input().split())
  tmp=[1]
  for i in range(D):
    tmp.append(pow(K,i+1))
  tmp=list(accumulate(tmp))
  for i in range(D+1):
    if tmp[i]>=X: id=i; break
  ans=10**20
  for i in range(id,D+1):
    if i<D: tmp2=1
    else: tmp2=0
    amari=tmp[i]-X
    for j in range(i-1,-1,-1):
      if amari==0: break
      tmp2+=min(K,amari//tmp[j])
      amari-=tmp[j]*min(K,amari//tmp[j])
    ans=min(ans,tmp2)
  print(ans)