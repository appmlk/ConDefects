n = int(input())
for x in range(n+1):
  for y in range(n+1):
    for z in range(n+1):
      if x + y + z <= n:
        print(x, y, z)