N, X, Y, Z = map(int, input().split())
if (X<Y and Y<Z) or (X>Y and Y>Z):
  print("Yes")
else:
  print("No")