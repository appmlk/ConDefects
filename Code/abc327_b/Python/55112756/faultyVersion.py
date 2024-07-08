n = int(input())
for i in range(n+1):
  if i**i > n: break
  if i**i == n:
    print(i)
    exit()
print(-1)