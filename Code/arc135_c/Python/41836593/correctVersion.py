N=int(input())
A=list(map(int,input().split()))
v=[0]*30
for i in range(N):
  x=A[i]
  for k in range(30):
    if (x>>k)&1:
      v[k]+=1
result=sum(A)
y=result
for i in range(N):
  w=0
  x=A[i]
  for k in range(30):
    if (x>>k)&1:
      w-=v[k]*2**k
      w+=(N-v[k])*2**k
  result=max(result,y+w)
print(result)