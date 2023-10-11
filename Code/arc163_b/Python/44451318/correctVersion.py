n,m=map(int,input().split())
A=list(map(int,input().split()))
li=list(sorted(A[2:]))
ans=10**18

for i in range(2,n-m+1): 
  ans=min(ans,max(0,A[0]-li[i-2])+max(0,li[i+m-3]-A[1]))
  
print(ans)