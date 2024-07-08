import itertools
import sys, os, io
input = io.BytesIO(os.read(0, os.fstat(0).st_size)).readline

def get_root(s):
    if s ^ root[s]:
        root[s] = get_root(root[s])
        return root[s]
    return s

def unite(s, t):
    rs, rt = get_root(s), get_root(t)
    if not rs ^ rt:
        return
    root[rt] = rs
    return

def same(s, t):
    return True if get_root(s) == get_root(t) else False

def f(u, v):
    return u * pow2[m] + v

n, m = map(int, input().split())
pow2 = [1]
for _ in range(m):
    pow2.append(2 * pow2[-1])
d = [[]]
for x in range(1, 5):
    d0 = []
    for p0 in itertools.product([i for i in range(x)], repeat = x):
        p = list(p0)
        u, ok = -1, 1
        for i in p:
            if not i <= u + 1:
                ok = 0
                break
            u = max(u, i)
        if ok:
            d0.append(p)
    d.append(d0)
u1 = dict()
u = []
ro, si = [], []
for i in range(1, pow2[m]):
    v = []
    for j in range(m):
        if i & pow2[j]:
            v.append(j)
    c = 0
    la = -2
    for j in v:
        if (la + 1) ^ j:
            c += 1
        la = j
    for d0 in d[c]:
        u0 = [0] * m
        k = -1
        la = -2
        for j in v:
            if (la + 1) ^ j:
                k += 1
            la = j
            u0[j] = d0[k] + 1
        u.append(u0)
        x = 0
        for i in u0:
            x = 10 * x + i
        u1[x] = len(u) - 1
        root = [k for k in range(2 * m)]
        size = [1 for _ in range(2 * m)]
        for j in v:
            for k in v:
                if u0[j] == u0[k]:
                    unite(j + m, k + m)
        ro.append(root)
        si.append(size)
l = len(u)
s = [list(input().rstrip()) for _ in range(n)]
for _ in range(2):
    while not min(s[-1]) & 1:
        s.pop()
    s.reverse()
inf = pow(10, 9) + 1
dp = [inf] * l
s0 = s.pop()
for i in range(l):
    u0 = u[i]
    v = []
    ok, c = 1, 0
    for j, k in zip(s0, u0):
        if j & 1 and not k:
            ok = 0
            break
        elif not j & 1 and k:
            c += 1
    la, k = -2, 0
    for j in range(m):
        if u0[j] and (la + 1) ^ j:
            k += 1
        if u0[j] and u0[j] ^ k:
            ok = 0
            break
        if u0[j]:
            la = j
    if ok:
        dp[i] = c
r1, s1 = [[] for _ in range(l * pow2[m])], [[] for _ in range(l * pow2[m])]
while s:
    s0 = s.pop()
    c0 = s0.count(35)
    dp0 = [inf] * l
    mi = min(dp)
    for i in range(l):
        if dp[i] > mi + 5:
            continue
        ui = u[i]
        v = []
        for j in range(m):
            if ui[j]:
                v.append(j + m)
        for j in range(pow2[m]):
            ok = 1
            for k in range(m):
                if not j & pow2[k] and s0[k] & 1:
                    ok = 0
                    break
            if not ok:
                continue
            if not r1[f(i, j)]:
                root = list(ro[i])
                size = list(si[i])
                for k in range(m - 1):
                    if j & pow2[k] and j & pow2[k + 1]:
                        unite(k, k + 1)
                for k in range(m):
                    if j & pow2[k] and ui[k]:
                        unite(k, k + m)
                r1[f(i, j)], s1[f(i, j)] = root, size
            root, size = r1[f(i, j)], s1[f(i, j)]
            ok = 1
            for k in v:
                ok = 0
                for x in range(m):
                    if j & pow2[x] and same(x, k):
                        ok = 1
                if not ok:
                    break
            if not ok:
                continue
            u0 = [0] * m
            d1, z, c = dict(), 1, -c0
            for k in range(m):
                if j & pow2[k] and not get_root(k) in d1:
                    d1[get_root(k)] = z
                    z += 1
                if j & pow2[k]:
                    u0[k] = d1[get_root(k)]
                    c += 1
            x = 0
            for k in u0:
                x = 10 * x + k
            k = u1[x]
            dp0[k] = min(dp0[k], dp[i] + c)
    dp = dp0
ans = inf
for i in range(l):
    if max(u[i]) == 1:
        ans = min(ans, dp[i])
print(ans)