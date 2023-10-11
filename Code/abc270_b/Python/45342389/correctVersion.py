x, y, z = map(int, input().split())

if y < 0 < x or y > 0 > x:
    print(abs(x))
elif 0 < x < y or 0 > x > y:
    print(abs(x))
elif 0 < y < x or 0 > y > x:
    if 0 < y < z or 0 > y > z:
        print(-1)
    elif 0 < z < x or 0 > z > x:
        print(abs(x))
    else:
        print(abs(x) + abs(z) * 2)
