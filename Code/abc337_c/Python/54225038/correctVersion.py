N = int(input())
A = list(map(int, input().split()))

s = [i for i in range(1,N+1)]
d = dict(zip(A, s))

next = d[-1]
for _ in range(N-1):
    print(next, end = ' ')
    next = d[next]
print(next)