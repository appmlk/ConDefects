grid = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
a = list(map(int, input().split()))
for i in range(3):
  for j in range(2):
    for k in range(2):
      if (a[k%2] == grid[i][j] and a[(k+1)%2] == grid[i][j+1]) or (a[k%2] == grid[j][i] and a[(k+1)%2] == grid[j+1][i]):
        exit(print("Yes"))
print("No")