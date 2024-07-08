from sys import stdout
import sys
input = sys.stdin.readline
inf = 10**18


def read(dtype=int):
    return list(map(dtype, input().split()))


n, l, r = read()


def cout(*args, **kwargs):
    print(*args, **kwargs)
    stdout.flush()


def ask(x, y):
    cout("?", x, y)
    return read()[0]


N = 1 << n
p = [-1] * (N+1)
d = [-1] * (N+1)
g = [[] for _ in range(N+1)]
p[l] = l
d[l] = 0
que = [l]
for u in range(N):
    for j in range(n):
        if u % (1 << j) == 0:
            v = u + (1 << j)
            if v <= N:
                g[u].append(v)
                g[v].append(u)
ans = 0
for u in que:
    if u == r + 1:
        while u != l:
            v = p[u]
            diff = abs(u-v)
            k = diff.bit_length() - 1
            x = ask(k, min(u, v) // diff)
            if v < u:
                ans += x
            else:
                ans -= x
            u = v
        break
    for v in g[u]:
        if d[v] == -1:
            d[v] = d[u] + 1
            p[v] = u
            que.append(v)


cout("!", ans % 100)
