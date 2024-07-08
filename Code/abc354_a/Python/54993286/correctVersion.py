H = int(input())
now = 0
day = 0

while now <= H:
  now+= 1<<day
  day += 1

print(day)