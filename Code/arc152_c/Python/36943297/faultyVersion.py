n = int(input())
a = list(map(int,input().split()))
g = 0
from math import gcd
for i in range(n - 1):
  g = gcd(g, a[i] - a[i + 1])
if g % 2 != 0 and (a[0] - a[-1]) % 2 == 0:
  g *= 2
print(a[-1] - a[0] + a[0] % g)