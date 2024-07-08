n,k=map(int,input().split())
A=list(map(int,input().split()))

if k>0:
  A.sort()
  print("Yes")
  print(*A)

elif k<sum(A):
  A.sort(reverse=True)
  print("Yes")
  print(*A)
else:
  print("No")