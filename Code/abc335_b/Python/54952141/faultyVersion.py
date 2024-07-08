n = int(input())
for i in range(n+1):
  for j in range(n+1):
    for k in range(n+1):
      if i + j + k <= 3:
        print(i,j,k)