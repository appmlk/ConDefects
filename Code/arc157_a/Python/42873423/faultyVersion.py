n, a, b, c, d = map(int, input().split())

if abs(b-c) >= 2:
    print("No")
elif b + c == 0 and a*b != 0:
    print("No")
else:
    print("Yes")