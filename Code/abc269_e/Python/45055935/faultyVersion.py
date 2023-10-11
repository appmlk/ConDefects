N = int(input())
A, B, C, D = 1, N, 1, N

count = N - 1
while B - A > 0:
    M = (A + B) // 2
    print(*["?", A, M, 1, N])
    print("\n")
    a = int(input())
    if a <= count // 2:
        B = M
        count = a
    else:
        A = M + 1
        count -= a

count = N - 1
while D - C > 0:
    M = (C + D) // 2
    print(*["?", 1, N, C, M])
    print("\n")
    a = int(input())
    if a <= count // 2:
        D = M
        count = a
    else:
        C = M + 1
        count -= a

print(*["!", A, D])
