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
    b = getList()
    if sorted(a) != sorted(b):
        print("No")
        return
    if a == b:
        print("Yes")
    elif all(i & 1 == 1 for i in a):
        print("No")
    elif all(i & 1 == 0 for i in a):
        print("Yes")
    else:
        from itertools import groupby

        def isSortable(a):
            for i in range(n):
                if i + 2 < n and a[i] & 1 and a[i+1] & 1 == 0 and a[i+2] & 1:
                    return 1
                if i + 1 < n and a[i] & 1 and a[i+1] & 1:
                    return 1
        ok = 1
        if not isSortable(a) or not isSortable(b):
            for i, j in zip(a, b):
                ok &= (i & 1) == (j & 1)
                if i & 1:
                    ok &= i == j
            h = [list(v) for u, v in groupby(a, key=lambda x: x & 1) if u == 0]
            g = [list(v) for u, v in groupby(b, key=lambda x: x & 1) if u == 0]
            ok &= len(h) == len(g)
            for i, j in zip(h, g):
                ok &= sorted(i) == sorted(j)
                if len(i) < 3:
                    ok &= i == j
        else:
            u = [i for i in a if i & 1 == 0]
            v = [i for i in b if i & 1 == 0]
            if len(u) == 2:
                ok &= u == v
        print(ok and "Yes" or "No")


for _ in range(t):
    solve()
