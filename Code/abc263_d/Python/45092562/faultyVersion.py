N,L,R=map(int,input().split())

A=list(map(int,input().split()))
A=[0]+A
f=[0]*(N+1)

for i in range(1,N+1):
  f[i]=min(f[i-1]+A[i],L*i)
  
A=A[::-1]
A=[0]+A

g=[0]*(N+1)

for i in range(1,N+1):
  g[i]=min(g[i-1]+A[i],R*i)
  
ans=10*15

for i in range(N+1):
  ans=min(ans,f[i]+g[N-i])
  
print(ans)