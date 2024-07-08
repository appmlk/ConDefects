s = input()

num = int(s[3:6])
if num > 349:
  print("No")
elif num == 316:
  print("No")
elif s[0:3] != "ABC":
  print("No")
else:
  print("Yes")