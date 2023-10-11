from math import gcd
l, r = map(int, input().split())
x = r - l
for i in range(x):
  for j in range(i + 1):
    gcd(l + j, x - i) == 1 and exit(print(x - i))