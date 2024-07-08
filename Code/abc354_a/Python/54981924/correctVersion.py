H = int(input())

for i in range(10 ** 9):
  n = (2 ** i) - 1
  if H < n:
    print(i)
    break