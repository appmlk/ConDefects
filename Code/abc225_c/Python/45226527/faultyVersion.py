n, m = map(int, input().split())
b = [None for _ in range(n)]
for i in range(n):
  b[i] = list(map(int, input().split()))

def check():
  c = list(map(list, zip(*b)))
  for i in range(m):
    for j in range(n - 1):
      if c[i][j + 1] != c[i][j] + 7:
        return False
      
  temp = b[0]
  for i in range(m - 1):
    if ((temp[i + 1] + 6) % 7) != ((temp[i] + 6) % 7) + 1:
      return False


  return True

if check():
  print("Yes")
else:
  print("No")
  




    