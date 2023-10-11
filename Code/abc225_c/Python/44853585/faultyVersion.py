n,m = map(int,input().split())
A = [list(map(int,input().split())) for _ in range(n)]
start = A[0][0]
r = start- (start//7)*7
if r+m-1>7:
  print("No")
  exit()
else:
  for i in range(n):
    for j in range(m):
      if A[i][j] != start+7*i+j:
        print("No")
        exit()
  print("Yes")