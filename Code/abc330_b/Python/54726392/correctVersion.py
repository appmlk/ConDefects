N, L, R = map(int, input().split())
A = list(map(int, input().split()))

for a in A:
    if a <= L:
        print(L, end = ' ')
    elif L < a < R:
        print(a, end = ' ')
    else:
        print(R, end = ' ')