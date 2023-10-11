A,B = map(int,input().split())
if A%B == 0:
  print(int(A/B))
else:
  print(int(A//B)+1)