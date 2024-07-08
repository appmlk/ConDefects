B = int(input())

for x in range(1,18):
  if x**x == B:
    print(x)
    exit()
print(-1)