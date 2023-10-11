N, K = map(int, input().split())

A = list(map(int, input().split()))
d = {}

for i in range(10):
    d[i] = 0

A.sort()

if A[0] != 0:
    print(0)


else:
    i = 1
    while i < N:
        if (A[i] >= K):
            print(A[i-1]+1)
            break
        if (A[i]-A[i-1] > 1):
            print(A[i-1]+1)
            break
        i += 1
    else:
        print(A[i-1])
