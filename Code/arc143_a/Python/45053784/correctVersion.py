x,y,z = map(int, input().split())
print(-1 if x > y + z or y > x + z or z > x + y else max(x, y, z))