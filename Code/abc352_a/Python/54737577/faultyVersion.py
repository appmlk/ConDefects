n, x, y, z = map(int, input().split())

if x<z<y or y<z<x:
  print("YES")
else:
  print("No")