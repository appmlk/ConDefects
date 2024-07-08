n=int(input())
def pf(n):
 a,f={},2
 while f*f<=n:
  if n%f:f+=1
  else:a[f]=a.get(f,0)+1;n//=f
 if n>1:a[n]=a.get(n,0)+1
 return a
s=[i*i for i in range(1,n)if i*i<=n]
ans=0
for i in range(1,n+1):
  a=1
  for k,v in pf(i).items():
    if v%2:
      a*=k
  for j in s:
    if a*j<=n:
      ans+=1
    else:
      break
print(ans)