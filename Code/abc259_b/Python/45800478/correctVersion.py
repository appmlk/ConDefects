import math
a, b, d = map(int, input().split())
sind = math.sin(math.radians(d))
cosd = math.cos(math.radians(d))
ans = [cosd*a-sind*b, sind*a+cosd*b]
print(*ans)