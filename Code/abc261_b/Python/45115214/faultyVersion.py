N = int(input())
A = [list(input()) for _ in range(N)]
count = 0
for i in range(N):
  for k in range(N):
    if (i == k) and (A[i][k] == '-') :
      count += 1
    if (i != k) and (A[i][k] == 'W') and (A[k][i] == 'L'):
      count += 1
    if (i != k) and (A[k][i] == 'W') and (A[i][k] == 'L'):
      count += 1
if count == N**2:
  print("correct")
else:
  print("incorrect")
  