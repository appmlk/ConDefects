n, m = map(int, input().split())
grid = []

for _ in range(n):
  row = input().strip()
  grid.append(list(row))

ans = n

for bin in range(1 << n):
  exist = [False] * m
  cnt = 0
  for i in range(n):
    if (bin >> i) & 1:
      cnt += 1
      for j in range(m):
        if grid[i][j] == "o":
          exist[j] == True
  
  if all(exist):
    ans = min(ans, cnt)

print(ans)