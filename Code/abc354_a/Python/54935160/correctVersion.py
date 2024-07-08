H = int(input())

height = 0
days = 0
while height <= H:
  height += 2 ** days
  days += 1
print(days)