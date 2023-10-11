import math

n=int(input())
a=list(map(int,input().split()))

a=list(set(a))
a.sort()
l=len(a)
x=a[0]

for i in range(l):
  a[i]-=x

x=a[-1]
for i in range(2,l):
  x = math.gcd(a[i],x)
  
if x==1:
  print(2)
else:
  print(1)
          
