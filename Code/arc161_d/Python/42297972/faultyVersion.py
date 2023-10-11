ii = lambda: int(input())
li = lambda: list(map(int, input().split()))
ldi = lambda: list(map(lambda x: int(x) - 1, input().split()))

n, d = li()

if n - 1 < 2 * d:
    print('No')
else:
    print('Yes')
    for i in range(n):
        for j in range(1, d + 1):
            print(i + 1, (i + d) % n + 1)