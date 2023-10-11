import math
n=int(input())
a=list(map(int,input().split()))
g=0
for i in a:
  g=math.gcd(g,i)

ans=(a[-1]-a[0])+min(a[0]%(2*g),a[-1]%(2*g))
print(ans)