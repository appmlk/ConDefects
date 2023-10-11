X, A, D, N = [int(x) for x in input().split()]

if D == 0:
    print(abs(X - A))
else:
    X -= A
    if D < 0:
        D = -D
        X = -X
    if 0 < X <= D*(N - 1):
        print(min(X % D, D - X % D))
    elif X < 0:
        print(-X)
    else:
        print(X - D*(N - 1))
