N = int(input())
A,B=[],[]
for i in range(N):
  A.append(input())
for i in range(N):
  B.append(input())
  
for i in range(N):
  if A[i] != B[i]:
    for j in range(N):
      if A[i][j] != B[i][j]:
        print(i,j)