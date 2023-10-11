import sys
input = lambda: sys.stdin.readline().rstrip()
ii = lambda: int(input())
mi = lambda: map(int, input().split())
li = lambda: list(mi())
INF = 2**63-1
mod = 998244353


def solve():
    n = ii()
    a = li()
    b = li()

    if a == b:
        return 'Yes'

    c = []

    for i in range(n):
        if not c or c[-1] != b[i]:
            c.append(b[i])
    if len(c) == n:
        return 'No'

    if len(set(b)) == 1 and b[0] in a:
        return 'Yes'

    for i in range(n):
        c = []
        for i in range(len(a)):
            if not c or c[-1] != a[i]:
                c.append(a[i])
        d = []
        for i in range(len(b)):
            if not d or d[-1] != b[i]:
                d.append(b[i])
        cnt = 0
        f = True
        n = len(d)
        m = len(c)
        for j in range(len(d)):
            if not f:
                break
            while d[j] != c[cnt]:
                cnt += 1
                if cnt >= len(c):
                    f = False
                    break
        if f:
            return 'Yes'
        b = b[1:] + [b[0]]
    return 'No'

for _ in range(ii()):
    print(solve())