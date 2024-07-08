N=int(input())
D=list(map(int,input().split()))

count=0
for i in range(1,N+1):
  j=i%10
  if i==j or i==j*10+j:
    for k in range(1,D[i-1]+1):
      if k==j or k==j*10+j:
        count=count+1
print(count)
