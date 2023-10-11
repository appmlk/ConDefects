INT = lambda : int(input())
MI = lambda : map(int, input().split())
MI_DEC = lambda : map(lambda x : int(x)-1, input().split())
LI = lambda : list(map(int, input().split()))
LI_DEC = lambda : list(map(lambda x : int(x)-1, input().split()))
INF = float('inf')

X, Y, Z = MI()

if X * Y > 0:
    if abs(X) < abs(Y):
        print(abs(X))
        exit(0)

    elif Z * Y < 0:
        print(abs(X) + 2 * abs(Z))
        exit(0)

    elif abs(Y) < abs(Z):
        print(-1)
        exit(0)

    else:
        print(abs(X))
        
else:
    print(abs(X))