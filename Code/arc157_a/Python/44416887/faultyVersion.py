import math
N,A,B,C,D=(int(x) for x in input().split())
n=math.floor(N/2)
if abs(B-C)<=1 and B<=n and C<=n and B+C>0:
  print("Yes")
else:
  print("No")