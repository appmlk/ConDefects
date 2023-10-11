n, m, t = map(int, input().split())
a = list(map(int, input().split()))
pos = 0

for i in range(m):
    x, y = map(int, input().split())
    t -= sum(a[pos : x - 1])
    if t <= 0:
        print("No")
        exit()
    else:
        t += y
        pos = x - 1
t -= sum(a[pos:])
if t < 0:
    print("No")
else:
    print("Yes")
