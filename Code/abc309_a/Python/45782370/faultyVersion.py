
p = {1:(2, 4), 2:(1, 5, 3), 3:(2, 6), 4:(1, 5, 7), 5:(2, 4, 6, 8), 6:(3, 5, 9), 7:(4, 8), 8:(5, 7, 9), 9:(6, 8)}

A, B = map(int, input().split())

if B in p[A]:
    print("Yes")
else:
    print("No")