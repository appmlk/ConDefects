n,k=map(int,input().split())
a=list(map(int,input().split()))
if(k%2==0):
  ans=0
  for i in range(1,k,2):
    ans+=(a[i]-a[i-1])
  print(ans)
else:

  pre=[0]*(k+1)
  after=[0]*(k+1)
  
  for i in range(1,k+1):
    pre[i]=pre[i-1]
    if(i%2==0):
      pre[i]+=a[i-1]-a[i-2]
  
  for i in range(k-2,-1,-1):
    after[i]=after[i+1]
    if((k-i)%2==0):
      after[i]+=a[i+1]-a[i]

  answer=2*10**5+1
  for i in range(k):
    answer=min(answer,pre[i]+after[i])
  print(answer)

