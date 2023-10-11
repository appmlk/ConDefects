N,M,S=map(int,input().split())
A=list(map(int,input().split()))
sums=0
C=[0]*N
for i in range(N-1,-1,-1):
  sums+=A[i]
  C[i]=sums/(N-i)
ans=0
#print(C)
for i in range(N):
  for j in range(N):
    #print(i,j,ans)
    if C[i]<C[j]:
      continue
    if S/(N-i)<=M:
      ans=max(ans,S*C[i])
    elif S/(N-j)<=M:
      ans=max(ans,S*C[j])
      if C[j]!=C[i]:
        t=(M-(S/(N-j)))*(N-i)*(N-j)/(i-j)
        #print(t,S-t,i,j)
        ans=max(ans,t*C[i]+(S-t)*C[j])
print(ans)