
n, x, y = map(int, input().split())
a = list(map(int, input().split()))
X = []
Y = []

for i in range(n):
  if i %2 == 0:
    X.append(a[i])
  else:
    Y.append(a[i])

dp_x = [[False for _ in range(4 * (sum(X) + 1))] for _ in range(len(X) + 1)]
dp_y = [[False for _ in range(4 * (sum(Y) + 1))] for _ in range(len(Y) + 1)]
dp_x[0][2 * (sum(X) + 1)] = True
dp_y[0][2 * (sum(Y) + 1)] = True

for i in range(len(X)):
  for j in range(4 * (sum(X) + 1)):
    if dp_x[i][j]:
      if i != 0:
        dp_x[i + 1][j + X[i]] = True
        dp_x[i + 1][j - X[i]] = True
      else:
        dp_x[i + 1][j + X[i]] = True  

for i in range(len(Y)):
  for j in range(4 * (sum(Y) + 1)):
    if dp_y[i][j]:
      dp_y[i + 1][j + Y[i]] = True
      dp_y[i + 1][j - Y[i]] = True


if abs(x) > sum(X) or abs(y) > sum(Y):
  print("No")
else:
  if dp_x[-1][x + 2 * (sum(X) + 1)] and dp_y[-1][y + 2 * (sum(Y) + 1)]:
    print("Yes")
  else:
    print("No")  
