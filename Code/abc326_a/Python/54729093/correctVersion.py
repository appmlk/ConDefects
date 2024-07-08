x, y = map(int, input().split())
print("Yes" if 0 < y - x <= 2 or 0 < x - y <= 3 else "No")