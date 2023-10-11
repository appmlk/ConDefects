import heapq
N,M,K=map(int,input().split())
A=list(map(int,input().split()))

maxbit=int(max(A)+M).bit_length()
# maxA=2**maxbit
ans=0
C=[0]*N
m=2**maxbit
for i in range(maxbit+1):
  B=[]
  for j in range(N):
    if (A[j]>>(maxbit-i))&1:
      B.append(C[j])
    else:
      B.append(ans+m-A[j]+C[j])
  B.sort()
  if sum(B[:K])<=M:
    ans+=m
    for j in range(N):
      if (A[j]>>(maxbit-i))&1:
        count=m-A[j]
        A[j]+=count
        C[j]+=count
  else:
    for j in range(N):
      if (A[j]>>(maxbit-i))&1:
        A[j]-=m
  m//=2

print(ans)