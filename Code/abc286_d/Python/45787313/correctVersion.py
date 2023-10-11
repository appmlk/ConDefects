n, x = map(int, input().split())
a = [0 for _ in range(n)]
b = [0 for _ in range(n)]
for i in range(n):
  a[i], b[i] = map(int, input().split())

dp = [[False for _ in range(x + 1)] for _ in range(n + 1)]
for i in range(n + 1):
  dp[i][0] = True

for i in range(n):
  for j in range(x + 1):
    for k in range(b[i] + 1):
      if dp[i][j]:
        if j + (k * a[i]) <= x:
            dp[i + 1][j + (k * a[i])] = True

if dp[-1][-1]:
  print("Yes")
else:
  print("No")  






