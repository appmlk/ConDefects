import math
l,r=map(int,input().split())
k=0
while True:
  flag=False
  for i in range(k):
    r2=r-i
    l2=l+k-i
    if math.gcd(l2,r2)==1:
      flag=True
  if flag:
    print (r-l-k)
    exit()
  k+=1
