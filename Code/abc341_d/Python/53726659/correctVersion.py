import math
n,m,k = (int(x) for x in input().split())
l = math.lcm(n,m)
low,high = 0,10**18
while high-low>1:
  mid = (low+high)//2
  if mid//n+mid//m-2*(mid//l) < k:
    low = mid
  else: high = mid
print(high)  
