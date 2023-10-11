#!/usr/bin/env python3

x, y, z = [int(x) for x in input().split()]
if (0 < x - y < x and x > 0) or (x < x - y < 0 and x < 0):
    if 0 < y - z < y:
        print(abs(x))
    elif (y - z < 0 and y > 0) or (y - z > 0 and y < 0):
        print(-1)
    else:
        print(abs(z) * 2 + abs(x))

else:
    print(abs(x))
