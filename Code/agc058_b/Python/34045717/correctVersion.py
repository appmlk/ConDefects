mod=998244353
N=int(input())
P=list(map(int,input().split()))
L=[i for i in range(N)]
R=[i for i in range(N)]
for i in range(N):
  j=i
  while j>=0:
    if P[i]>=P[j]:
      j-=1
    else:
      break
  L[i]=j+1
  j=i
  while j<N:
    if P[i]>=P[j]:
      j+=1
    else:
      break
  R[i]=j

DP=[0]*(N+1)
DP[0]=1
for i in range(N):
  for j in range(L[i],R[i]):
    DP[j+1]+=DP[j]
    if DP[j+1]>=mod:
      DP[j+1]-=mod
print(DP[N])