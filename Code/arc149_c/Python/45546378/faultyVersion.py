n = int(input())

if n == 3:
    print(5, 9, 1)
    print(3, 7, 8)
    print(6, 2, 4)
    exit()

if n == 4:
    print(9, 11, 13, 15)
    print(1, 3, 5, 7)
    print(8, 6, 10, 14)
    print(4, 2, 12, 16)
    exit()

if n == 5:
    print(1, 5, 7, 11, 13)
    print(17, 25, 23, 19, 3)
    print(9, 15, 21, 6, 12)
    print(18, 24, 14, 4, 8)
    print(10, 2, 16, 20, 22)



seq = [i for i in range(1, n*n+1) if i % 3 and i & 1]+[i for i in range(1, n*n+1) if i % 3 == 0 and i & 1] + \
    [i for i in range(1, n*n+1) if i % 3 == 0 and i & 1 == 0] + \
    [i for i in range(1, n*n+1) if i % 3 and i & 1 == 0]

ans = [[0]*n for _ in range(n)]

for i in range(n*n):
    x, y = divmod(i, n)
    ans[x][y] = seq[i]

for ai in ans:
    print(*ai)
