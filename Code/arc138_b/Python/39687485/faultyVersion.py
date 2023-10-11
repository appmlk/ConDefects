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
    if a[0]:
        print("No")
    elif max(a) == 0:
        print("Yes")
    else:
        if a[0] == a[1] == 0:
            print("No")
            return
        j = 1
        while j < n and a[j] != a[j-1]:
            j += 1
        if j == n:
            print("Yes")
            return
        k = j
        while a[j+1] == a[k]:
            j += 1
        from itertools import groupby
        c = len(list(groupby(a[j+1:])))
        print("No" if c > k else "Yes")


for _ in range(t):
    solve()
