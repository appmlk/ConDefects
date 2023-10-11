X, A, D, N = map(int, input().split())

if D == 0:
    print(abs(X-A))
    exit()

if D > 0:
    if X < A:
        print(A-X)
    elif A+D*(N-1) < X:
        print(X-(A+D*(N-1)))
    else:
        print(min(abs((X-A)%D), D-abs((X-A)%D)))
else:
    if X > A:
        print(X-A)
    elif A+D*(N-1) > X:
        print((A+D*(N-1))-X)
    else:
        print(min(abs((X-A)%D), abs((X-A)%D)-D))