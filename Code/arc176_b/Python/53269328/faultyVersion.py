import sys

input = lambda: sys.stdin.readline().strip()

for _ in range(int(input())):
    n, m, k = map(int, input().split())

    if m == k + 1:
        print(0)
    elif n < m:
        print((6, 2, 4, 8)[n % 4])
    else:
        print(((6, 2, 4, 8)[(n - k) % (m - k) % 4] if (n - k) % (m - k) else 1) * (6, 2, 4, 8)[k % 4] % 10)
