X,A,D,N = map(int,input().split())
X2 = X
X -= A

if D == 0:
    print(abs(X))
else:
    M = X // D
    P = X % D
    if 0 <= M < N-1:
        print(min(abs(P),abs(D-P)))
    elif N-1 <= M:
        print(X2 - (A + D*(N-1)))
    elif M < 0:
        print(abs(A - X2))