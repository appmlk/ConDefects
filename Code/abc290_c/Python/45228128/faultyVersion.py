n,k=(int(x) for x in input().split())
a=set([])
A=input().split()
for i in range(n):
  if not A[i] in a:
    a.add(int(A[i]))

if k>=10:
  k=10
ans=0
for i in range(k):
  if i in a:
    ans+=1
  else:
    break
print(ans)