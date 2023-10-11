
import sys
input = sys.stdin.readline
inf = float('inf')


def getInt():
    return int(input())


def getStr():
    return input().strip()


def getList(dtype=int, split=True):
    s = getStr()
    if split:
        s = s.split()
    return list(map(dtype, s))


t = 1


def solve():
    n = getInt()
    from math import gcd
    g = 0
    a = getList()
    for i in a:
        g = gcd(2 * (i-a[0]), g)
    res = a[0] % g
    print(res + a[-1] - a[0])


for _ in range(t):
    solve()
