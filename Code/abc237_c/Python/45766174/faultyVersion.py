s = input()
tmp1 = s.rstrip("a")
back_a = len(s)-len(tmp1)
tmp2 = tmp1.lstrip("a")
front_a = len(s)-len(tmp2)
if tmp2==tmp2[::-1] and front_a<=back_a:
  print("Yes")
else:
  print("No")