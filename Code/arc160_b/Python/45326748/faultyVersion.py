import math
MOD=998244353
t=int(input())
mul = lambda a,b: (a*b)%MOD
add = lambda a,b : (a+b)%MOD
for _ in range(t):
  n=int(input())
  s=int(math.sqrt(n))
  ans = mul(s, mul(s,s))
  for x in range (1, s+1):
    v = n//x
    ans= add(ans, mul(x, max(0, v-s-1)))
  print(ans)