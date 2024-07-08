import numpy as np
def dp(n,a):
  if n==1:
    print(a[1]-a[2],a[0]-a[1]+a[2],a[1]-a[0],end=' ')
    return [a[1]-a[2], a[1]-a[0],a[0]-a[1]+a[2]]
  else:
    x = 3**(n-1)
    a0 = a[:x]
    a1 = a[x:2*x]
    a2 = a[2*x:]
    print([a1-a2,a0-a1+a2,a1-a0])
    return [dp(n-1,a1-a2),dp(n-1,a0-a1+a2),dp(n-1,a1-a0)]
  
n = int(input())
a = np.array(list(map(int,input().split())))
ans = dp(n,a)
