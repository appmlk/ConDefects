s = input()
for i in range(1,17,2):
  if s[i] != "0":
    print("No")
    break
else:
  print("Yes")