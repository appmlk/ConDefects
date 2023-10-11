X,A,D,N = map(int,input().split())

min = min(A,A + D*(N-1))
max = max(A,A + D*(N-1))

if X >= max:
    print(X-max)
    exit()

if X <= min:
    print(min-X)
    exit()

ans = abs(X-A)%abs(D)
li = [ans,D-ans]

if li[0] < li[1]:
    print(li[0])
else:
    print(li[1])