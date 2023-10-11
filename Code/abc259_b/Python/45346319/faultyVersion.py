import math

a, b, c = map(int, input().split())


x = a * round(math.cos(math.radians(c)), 8) + b * round(math.sin(math.radians(c)), 8)
y = a * round(math.sin(math.radians(c)), 8) + b * round(math.cos(math.radians(c)), 8)

print(x, y)