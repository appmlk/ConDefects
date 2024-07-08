import math
n,m,k=map(int,input().split())
q=(n*m)/math.gcd(n,m)
min=0
max=int(2e18)
while (max-min)>1:
  x=(max+min)//2
  if x//n + x//m - x//q*2 >= k:
    max = x
  else:
    min = x
print(max)