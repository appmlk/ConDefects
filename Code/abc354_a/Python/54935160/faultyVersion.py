H = int(input())

height = 0
days = 1
while height <= H:
  height += 2 ** days
  days += 1
print(days)