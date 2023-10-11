N, M = map(int, input().split())

flag = [1] * N

for i in range(M):
    a, b = map(int, input().split())
    flag[b-1] = 0

if sum(flag) == 1:
    print(flag.index(1)+1)
else:
    print(-1)
