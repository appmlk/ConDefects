P=[1]*10**6
for i in range(2,10**6):
  if P[i]:
    for j in range(i+i,10**6,i):
      P[j]=0
P=[i for i in range(2,10**6) if P[i]]
n=int(input())
a=list(map(int,input().split()))
from random import randint
for _ in range(500):
  i=randint(0,n-1)
  j=randint(0,n-2)
  j+=i<=j
  A=abs(a[i]-a[j])
  p=[]
  for v in P:
    if v*v>A:
      break
    if A%v==0:
      if v==2:
        if A%4==0:
          p+=[4]
      else:
        p+=[v]
      while A%v==0:
        A//=v
  if A>2:
    p+=[A]
  for m in p:
    x=-1
    y=1
    for v in a:
      if x==v%m:
        y+=1
      else:
        y-=1
        if y==0:
          x=v%m
          y=1
    if sum(v%m==x for v in a)*2>n:
      print(m)
      exit()
print(-1)