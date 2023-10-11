import sys
input = lambda: sys.stdin.readline().rstrip()
ii = lambda: int(input())
mi = lambda: map(int, input().split())
li = lambda: list(mi())

n = ii()


k = n * (n - 1) // 2

if k % 3 != 0:
    print('No')
else:

    import random

    K = list(range(1, n))

    for _ in range(10 ** 6):
        A = [[] for _ in range(3)]
        for v in K:
            A[random.randint(0, 2)].append(v)
        if sum(A[0]) == sum(A[1]) == sum(A[2]):
            break
    else:
        print('No')
        exit()
    print('Yes')
    ans = [[0] * (n) for _ in range(n)]
    for i in range(n - 1):
        if i + 1 in A[0]:
            for j in range(i + 1):
                ans[i + 1][j] = 'W'
        if i + 1 in A[1]:
            for j in range(i + 1):
                ans[i + 1][j] = 'R'
        if i + 1 in A[2]:
            for j in range(i + 1):
                ans[i + 1][j] = 'B'
    for i in range(n - 1, 0, -1):
        print(''.join(ans[i][:i]))
        


