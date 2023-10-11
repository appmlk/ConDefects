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
    a = getList()
    u = a.index(1)
    if a[(u+1) % n] == 2:
        print(min(u, 2 + n-u))
    else:
        print(min(u+2, n-u+2))


for _ in range(t):
    solve()
