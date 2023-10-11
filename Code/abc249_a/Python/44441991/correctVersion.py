def d(a, b, c, x):
    t = x // (a + c)
    return t * (a * b) + min(a, (x - ((a + c) * t))) * b


A, B, C, D, E, F, X = map(int, input().split())

if d(A, B, C, X) > d(D, E, F, X):
    print("Takahashi")
elif d(A, B, C, X) < d(D, E, F, X):
    print("Aoki")
else:
    print("Draw")