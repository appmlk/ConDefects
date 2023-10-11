X, L, D, N = map(int, input().split())
R = L + D * (N-1)

if L > R:
    L, R = R, L
    D *= -1

if X < L:
    print(L - X)
elif R < X:
    print(X - R)
else:
    if D == 0:
        print(X-L)
    else:
        s = (X - R % D) % D
        print(min(s, D-s))
