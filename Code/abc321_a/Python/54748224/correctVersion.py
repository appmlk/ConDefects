n = input()
last = 10
for c in n:
  if int(c) < last:
      last = int(c)
  else:
      print("No")
      exit()
print("Yes")