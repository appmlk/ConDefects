from collections import defaultdict

N, M, T = map(int, input().split())
A = list(map(int, input().split()))
XY = [list(map(int, input().split())) for _ in range(M)]

d = defaultdict(int)
for x, y in XY:
    d[x] = y

now = 1
while now != N:
    T -= A[now-1]
    now += 1

    if T <= 0:
        print('No')
        exit()
    
    T += d[now]
    # print(T)

print('Yes')