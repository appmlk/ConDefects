x = int(input())

if x % 10 == 0:
  print(int(x / 10))
else:
  print(int(x // 10 + 1))