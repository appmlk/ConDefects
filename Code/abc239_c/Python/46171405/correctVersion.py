p, q, r, s = [int(x) for x in input().split()]
d = (p - r) ** 2 + (q - s) ** 2
if d in (2, 4, 10, 16, 18, 20):
  print("Yes")
else:
  print("No")