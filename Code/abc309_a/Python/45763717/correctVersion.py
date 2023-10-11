a,b=map(int,input().split())
if set([a,b]) not in [{3,4},{6,7}] and abs(a-b)==1:
  print("Yes")
else:
  print("No")