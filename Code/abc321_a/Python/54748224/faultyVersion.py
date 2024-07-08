n = input()
last = 10
for c in n:
  if 1 <= int(c) < last:
      last = int(c)
  else:
      print("No")
      exit()
print("Yes")