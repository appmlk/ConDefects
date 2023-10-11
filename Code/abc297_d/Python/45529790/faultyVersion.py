import math
a, b = list(map(int, input().split(' ')))

if a < b:
  tmp = b
  b = a
  a = tmp

ans = 0
while b > 0:
  ans += int(a / b)
  a = a % b
  tmp = a
  a = b
  b = tmp
print(ans-1)
