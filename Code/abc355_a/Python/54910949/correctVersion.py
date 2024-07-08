A,B = map(int,input().split())
if A + B == 3:
  print("3")
elif A + B == 4 and A != B:
  print("2")
elif A + B == 5:
  print("1")
else:
  print("-1")