H = int(input())

for i in range(H):
  n = (2 ** i) - 1
  if H < n:
    print(i)
    break