N = int(input())
P = 0
i = 0

while (N >= P):
  P += 2 ** i
  i += 1
print(i)