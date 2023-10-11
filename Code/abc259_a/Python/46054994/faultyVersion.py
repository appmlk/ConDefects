N, M, X, T, D = map(int, input().split())
h = T

if M >= X:
    print(T)
else:
    print(T + (X - M) * D)