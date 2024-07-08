n = int(input())

for i in range(920):
  if i <= 99:
    continue
  elif i < n:
    continue
  else:
    if int(str(i)[0]) * int(str(i)[1]) == int(str(i)[2]):
      print(i)
      exit()
