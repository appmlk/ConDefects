M=998244353
n,m,k=map(int,input().split())
q=[0]*(k+1)
q[0]=1
for i in range(n):
  nq=[0]*(k+1)
  for j in range(k):
    nq[j+1]+=q[j]
    if j+m+1<=k:
      nq[j+m+1]-=q[j]
  for j in range(k):
    nq[j+1]+=nq[j]
    nq[j+1]%=M
  q=nq
print(sum(q)%M)