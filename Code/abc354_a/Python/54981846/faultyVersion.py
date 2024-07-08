H = int(input())
P = 0

for i in range(H):
  P += pow(2, i)
  if P > H:
    print(i + 1)
    break