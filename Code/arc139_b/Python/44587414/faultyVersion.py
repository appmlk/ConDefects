import math

def solve(N, A, B, X, Y, Z):
    if N <= A * A:
        pts = [(y, (N - A * y) // B) for y in range(0, N // A + 1)]
        pts += [(y, 0) for y in range(0, N // A + 1)]
    elif N <= B * B:
        pts = [((N - B * z) // A, z) for z in range(0, N // B + 1)]
        pts += [(0, z) for z in range(0, N // B + 1)]
    else:
        pts = [(y, 0) for y in range(0, min(N // A, B + 1) + 1)]
        pts += [(y, (N - A * y) // B) for y in range(0, min(N // A, B + 1) + 1)]

        pts = [(y, 0) for y in range(max(0, N // A - B - 1), N // A + 1)]
        pts += [(y, (N - A * y) // B) for y in range(max(0, N // A - B - 1), N // A + 1)]

    res = math.inf
    for y, z in pts:
        res = min(res, (N - A * y - B * z) * X + Y * y + Z * z)
    
    print(res)
    

T = int(input())

for _ in range(T):
    N, A, B, X, Y, Z = map(int, input().split())
    solve(N, A, B, X, Y, Z)
