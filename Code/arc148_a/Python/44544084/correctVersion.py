from math import gcd
n = int(input())
a = list(map(int, input().split()))
for i in range(n - 1):
  if a[i] == a[i + 1]:
    exit(print(1))
g = abs(a[0] - a[1])
for i in range(1, n - 1):
  g = gcd(g, abs(a[i] - a[i + 1]))
print(2 if g == 1 else 1)