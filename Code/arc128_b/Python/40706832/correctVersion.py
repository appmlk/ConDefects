from math import gcd
import sys
input = sys.stdin.readline
inf = float('inf')


def read(dtype=int):
    return list(map(dtype, input().split()))


t = int(input())


def diff(a):
    return [a[1]-a[0], a[2]-a[1], a[0]-a[2]]


n = 3
for _ in range(t):
    a = read()
    ans = inf
    for i in range(3):
        x = (i-1) % n
        y = (i+1) % n
        c = min(a[x], a[y])
        v = (max(a[y], a[x]) - c) // 3
        if (max(a[y], a[x]) - c) % 3 == 0:
            ans = min(ans, c + v*3)
    print(-1 if ans == inf else ans)
