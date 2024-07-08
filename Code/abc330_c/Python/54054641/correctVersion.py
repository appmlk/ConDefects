d = int(input())
x = 0
out = d
while x**2 < d:
    y = int((d - x**2)**(0.5))
    out = min(out, d - x**2 - y**2, x**2 + (y+1)**2 - d)
    x += 1

print(out)