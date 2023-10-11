A,B = map(int,input().split())
if A%B == 0:
  print(int((A+B-1)//B))
else:
  print(int(A//B)+1)