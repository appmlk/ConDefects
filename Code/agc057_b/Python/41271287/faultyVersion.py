N,X=map(int,input().split())
A=list(map(int,input().split()))
L=[]
u=max(A)
x=u
y=x
for i in range(N):
  l=A[i]
  r=A[i]
  if l==u:
    L.append([l,r])
    continue
  while True:
    l2=2*l
    r2=2*r+X
    if l2<=u<=r:
      L.append([u,u])
      break
    if l2>=u:
      L.append([r,l2])
      break
    l=l2
    r=r2
y=u
L.sort()
result=10**10
for i in range(N):
  l,r=L[i][0],L[i][1]
  result=min(result,y-l)
  y=max(y,r)
if result<X:
  print(0)
  exit()
print(result)