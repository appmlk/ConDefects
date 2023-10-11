N,K=map(int,input().split())
A=sorted([int(x) for x in input().split()])
mod=998244353
d=1
from collections import Counter
C=Counter(A)
for c in C:
  for i in range(C[c]):
    d*=i+1
    d%=mod    
x=pow(d,mod-2,mod)
t=1
j=N-1
for i in range(N):
  while j>i:
    if A[j]+A[i]>=K:
      t+=1
    else:
      break
    j-=1 
  if i==j:
    break
  x*=t*(t-1)
  x%=mod  
  t-=1
for i in range(t+1):
  x*=i+1
  x%=mod
print(x)
         
  

