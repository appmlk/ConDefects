
p = {1:(2,), 2:(1, 3), 3:(2,), 4:(5,), 5:(6, 8), 6:(5,), 7:(8,), 8:(7, 9), 9:(8,)}

A, B = map(int, input().split())

if B in p[A]:
    print("Yes")
else:
    print("No")