x,y,z = map(int, input().split())
print(-1 if x == y == 0 and z == 1 or x == z == 0 and y == 1 or y == z == 0 and x == 1 else max(x, y, z))