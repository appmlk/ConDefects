a, b, c = map(int,input().split())
l = [a,b,c]
l_2 = sorted(l)
if b == l_2[1]:
  print("Yes")
else:
  print("no")