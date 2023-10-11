a, b, c, x = map(int, input().split())
if x <= a:
    print(1)
elif a < x <= b:
    print(c/(b-a+1))
else:
    print(0)
