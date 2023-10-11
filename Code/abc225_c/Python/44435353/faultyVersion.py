N, M = map(int, input().split())
B = [[int(i) for i in input().split()] for _ in range(N)]

flg = True
for i in range(N):
  for j in range(M - 1):
    if B[i][j] + 1 != B[i][j + 1]:
      flg = False
    if B[i][j] % 7 + 1 != B[i][j + 1] % 7:
      flg = False

for j in range(M):
  for i in range(N - 1):
    if B[i][j] + 7 !=  B[i + 1][j]:
      flg = False

if flg:
  print('Yes')
else:
  print('No')
