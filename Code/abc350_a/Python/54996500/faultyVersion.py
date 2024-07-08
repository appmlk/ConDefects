import sys
S = sys.stdin.read().strip()
if int(S[-3:]) <=349 and int(S[-3:]) != 316:
  print("Yes")
else:
  print("No")