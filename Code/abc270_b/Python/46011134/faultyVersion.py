x, y, z = map(int, input().split())

if x*y < 0:
  print(abs(x))
else:
  if abs(y) > abs(x):
    print(abs(x))
  else:
    if x*z <0:
      print(abs(x)+2*abs(z))
    else:
      if abs(z) < abs(x):
        print(abs(x))
      else:
        print(-1)