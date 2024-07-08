from collections import defaultdict
from random import randint

n = int(input())
a_list = [int(input()) for _ in range(n)]
m = randint(10 ** 32, 10 ** 33)

d = defaultdict(lambda: 0)

for a in a_list:
  d[a] += 1

ans = 0
for p in a_list:
  for q in a_list:
    ans += d[p * q % m]
print(ans)