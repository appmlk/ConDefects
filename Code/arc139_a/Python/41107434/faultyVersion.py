n=int(input())
a=[0]+list(map(int,input().split()))
f=1
for i in range(1,n+1):
  if a[i]<a[i-1]:
    f|=1<<a[i]
  if a[i]>=a[i-1]:
    ok=1<<100
    ng=-1
    while ok-ng>1:
      m=(ok+ng)//2
      if (m<<(a[i]+1))+(1<<a[i])>f:
        ok=m
      else:
        ng=m
    f=(ok<<(a[i]+1))+(1<<a[i])
print(f)