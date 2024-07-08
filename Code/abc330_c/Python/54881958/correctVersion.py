import math
D = int(input())
n = int(math.sqrt(D))
ans = 10**10

index = 0
for i in range(n, 0, -1):
  for j in range(index, i+1):
    ans = min(ans, abs(D-(i**2 + j**2)))
    if i**2 + j**2 >= D:
      index = j
      break
print(ans)