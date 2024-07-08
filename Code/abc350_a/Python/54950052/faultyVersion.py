s = input()
memo = int(s[3])*100+int(s[4])*10+int(s[5])
if memo>=0 and memo<=349 and memo!=316:
  print("Yes")
else:
  print("No")