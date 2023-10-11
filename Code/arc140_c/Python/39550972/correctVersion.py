from bisect import bisect_left
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


def lis(a):
    h = []
    for i in a:
        j = bisect_left(h, i)
        if j == len(h):
            h.append(i)
        else:
            h[j] = i
    return len(h)


def cost(a):
    p = [abs(i-j) for i, j in zip(a, a[1:])]
    return lis(p)


def solve():
    n, x = getList()
    # from itertools import permutations
    # best = [0] * (n+1)
    # for u in permutations(range(1, n+1)):
    #     u = list(u)
    #     best[u[0]] = max(best[u[0]], cost(u))
    #     if u[0] == 3 and best[u[0]] == n-2:
    #         print(u)
    #         return
    invert = 0
    if x > (n+1) // 2:
        invert = 1
        x = 1 + n - x
    res = [x]
    mark = [0] * (n+1)
    mark[x] = 1
    last = 0
    if x * 2 - 1 != n:
        it = (x+1+n-x+1) >> 1
        res.append(it)
        mark[it] = 1
    last = 1
    k = x * 2 == n and -1 or 1
    while 1:
        while 1 <= res[-1] + k * last <= n and mark[res[-1] + k * last]:
            last += 1
        if 1 <= res[-1] + k * last <= n:
            res.append(res[-1] + k * last)
            last += 1
        else:
            break
        k *= -1
    if invert:
        res = [1+n-x for x in res]
    print(*res)


for _ in range(t):
    solve()
