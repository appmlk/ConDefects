k = int(input())
if k >= 60:
  k = k-60
  if k < 10:
    print("22:0"+str(k))
  else:
    print("22:"+ str(k))
else:
  if k < 10:
    print("21:0"+str(k))
  else:
    print("21:"+ str(k))