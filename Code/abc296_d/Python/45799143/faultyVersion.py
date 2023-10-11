import math
from math import ceil

n, m = map(int, input().split())
ans = n ** 2 + 2

if m > n ** 2:
  print(-1)
  exit()

for i in range(1, 10 ** 6 + 1):
  p = ceil(m / i)
  if 1 <= p <= n and m <= i * p <= n ** 2:
    ans = min(ans, i * p)
if ans == n ** 2 + 2:
  print(-1)
else:
  print(ans)