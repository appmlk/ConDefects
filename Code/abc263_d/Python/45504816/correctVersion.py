N,L,R = map(int,input().split())
A = list(map(int,input().split()))

dpL=[0]*(N+1)
dpR=[0]*(N+1)

for i in range(N):
   dpL[i+1] = min(dpL[i]+A[i],L*(i+1))

for j in range(N-1,-1,-1):  
   dpR[j]= min(dpR[j+1]+A[j],R*(N-j))

ans=10**18
for i in range(N+1):
   ans = min(ans,dpL[i]+dpR[i])

print(ans)