H = int(input())
now = 0
day = 0

while now <= H:
  now = 2**day
  day += 1
  
print(day)