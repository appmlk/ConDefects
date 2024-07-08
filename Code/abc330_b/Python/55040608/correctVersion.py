N, L, R = map(int, input().split())
A = [*map(int, input().split())]

for a in A:
    if a <= L:
        print(L, end=' ')
    elif a >= R:
        print(R, end=' ')
    else:
        print(a, end=' ')
