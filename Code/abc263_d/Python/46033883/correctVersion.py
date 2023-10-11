N,L,R=map(int,input().split())
A=list(map(int,input().split()))
result=sum(A)
result=min(result,L*N,R*N)
v=[0]*N
for i in range(N):
  x=v[i-1]+A[i]
  if x>(i+1)*L:
    v[i]=(i+1)*L
  else:
    v[i]=x
for i in range(N-1,-1,-1):
  p=v[i]+(N-1-i)*R
  result=min(result,p)
print(result)