import numpy as np
from numba import i8, njit
from math import sqrt, atan2

@njit((i8, i8, i8, i8[:]), cache = True)
def add(p, x, size, data):
    p += 1
    while p <= size:
        data[p] += x
        p += p & -p

@njit(i8(i8, i8[:]), cache = True)
def _sum(r, data):
    s = 0
    while r > 0:
        s += data[r]
        r -= r & -r
    return s

def proc(r):
    point = []
    idx = 0
    for ai, bi, di in zip(a, b, d):
        if abs(di) >= r:
            continue
        t = sqrt(r * r - di * di)
        ab = sqrt(ai * ai + bi * bi)
        _x = ai / ab
        _y = bi / ab
        x = _x * di
        y = _y * di
        dx = t * _y
        dy = t * _x
        point.append(int(atan2(y - dy, x + dx) * inf) * n + idx)
        point.append(int(atan2(y + dy, x - dx) * inf) * n + idx)
        idx += 1
    point.sort()
    res = 0
    for i, p in enumerate(point):
        j = p % n
        if memo[j] == -1:
            memo[j] = i
            add(i, 1, size, data)
            continue
        else:
            res += _sum(i, data)
            res -= _sum(memo[j] + 1, data)
            add(memo[j], -1, size, data)
            memo[j] = -1
    return res

n, k = map(int, input().split())

a = [0] * n
b = [0] * n
d = [0] * n
for i in range(n):
    ai, bi, ci = map(int, input().split())
    a[i] = ai
    b[i] = bi
    d[i] = -ci / sqrt(ai * ai + bi * bi)

size = 2 * n
data = np.zeros(size + 1, np.int64)
memo = [-1] * n
inf = 1 << 40
ng = 0
ok = 1 << 24
for _ in range(40):
    mid = (ng + ok) / 2
    if proc(mid) >= k:
        ok = mid
    else:
        ng = mid
ans = ok
print(ans)
