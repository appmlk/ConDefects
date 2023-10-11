X = str(input())

if len(X) <= 2:
  print(X)
else:
  d = -9
  a0 = int(X[0])
  for i in range(d, 10):
    num = ""
    for j in range(len(X)):
      if 0 <= a0 + i * j <= 9:
        num += str(a0 + i * j)
    if int(num) >= int(X):
      print(num)
      exit()
  
  a0 += 1
  d = -9
  for i in range(d, 10):
    num = ""
    for j in range(len(X)):
      if 0 <= a0 + i * j <= 9:
        num += str(a0 + i * j)

    if int(num) >= int(X):
      print(num)
      exit()

