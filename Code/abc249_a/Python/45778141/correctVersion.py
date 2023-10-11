a,b,c,d,e,f,x = map(int, input().split())
xbk = x
y, z = 0, 0
y += x // (a + c) * a * b
x -= x // (a + c) * (a + c)
if x - a >= 0:
    y += a * b
else:
    y += x * b

x = xbk
z += x // (d + f) * d * e
x -= x // (d + f) * (d + f)
if x - d >= 0:
    z += d * e
else:
    z += x * e

print("Takahashi" if y > z else "Aoki" if y < z else "Draw")