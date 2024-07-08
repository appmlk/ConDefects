s = input()
for i in range(2,2,16):
  if s[i] != "0":
    print("No")
    break
else:
  print("Yes")