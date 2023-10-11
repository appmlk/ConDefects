import sys
input = lambda: sys.stdin.readline().rstrip()
ii = lambda: int(input())
mi = lambda: map(int, input().split())
li = lambda: list(mi())
INF = 2 ** 63 - 1
mod = 998244353

n, m = mi()

a = [[0] * (m) for _ in range(n)]


for i in range(n):
    for j in range(m):
        a[i][j] = (i // 23 + j // 23 + i + j) % 23 + 1

for v in a:
    print(*v)
