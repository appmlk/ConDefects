import sys, os, io
input = io.BytesIO(os.read(0, os.fstat(0).st_size)).readline

def f(u, v, w):
    return u << 40 ^ v << 20 ^ w

def get_segment0(s, t):
    s, t = s ^ l1, t ^ l1
    while s <= t:
        if s & 1:
            yield s
            s += 1
        s >>= 1
        if not t & 1:
            yield t
            t -= 1
        t >>= 1

def get_segment(s, t):
    s, t = s ^ l1, t ^ l1
    u, v = [], []
    while s <= t:
        if s & 1:
            u.append(s)
            s += 1
        s >>= 1
        if not t & 1:
            v.append(t)
            t -= 1
        t >>= 1
    return u + v[::-1]

def update():
    i = 4
    while i:
        j = i >> 2
        if tree[i]:
            tree[i ^ 1], tree[i ^ 2] = tree[i ^ 2], tree[i ^ 1]
            if not j & l1:
                tree[i << 1] ^= 1
                tree[i << 1 ^ 4] ^= 1
            tree[i] = 0
        if j & l1:
            nx = j >> 1 << 2
        elif color[j << 1]:
            nx = i << 1
        elif color[j << 1 ^ 1]:
            nx = i << 1 ^ 4
        else:
            nx = j >> 1 << 2
        if nx < i:
            color[j] = 0
            if not j & l1:
                j, k = i << 1, i << 1 ^ 4
                sj, sk = tree[j ^ 3], tree[k ^ 3]
                for l in x:
                    u = tree[j ^ l] if not tree[j] else tree[j ^ l ^ 3]
                    v = tree[k ^ l] if not tree[k] else tree[k ^ l ^ 3]
                    lj, rj, mj = u >> 40, (u >> 20) & 0xfffff, u & 0xfffff
                    lk, rk, mk = v >> 40, (v >> 20) & 0xfffff, v & 0xfffff
                    mi = max(mj, mk, rj + lk)
                    li = lj if lj ^ sj else lj + lk
                    ri = rk if rk ^ sk else rk + rj
                    tree[i ^ l] = f(li, ri, mi)
        i = nx
    return

def get_ans():
    l0, r0, m0, s0 = 0, 0, 0, 0
    for i in seg:
        u = tree[i << 2 ^ 2] if not tree[i << 2] else tree[i << 2 ^ 1]
        li, ri, mi, si = u >> 40, (u >> 20) & 0xfffff, u & 0xfffff, tree[i << 2 ^ 3]
        m0 = max(m0, mi, r0 + li)
        l0 = l0 if l0 ^ s0 else l0 + li
        r0 = ri if ri ^ si else ri + r0
        s0 += si
    return m0

n, m = map(int, input().split())
s = list(input().rstrip())
s = [i & 1 for i in s]
x = [1, 2]
y = [0] * (n + 1)
ans = []
color = [0] * (n << 3)
color[0] = 1
for v in range((m >> 16) + min(m & 0xffff, 1)):
    u = set([0, n])
    q0 = [0] * (1 << 16)
    for i in range(v << 16, min((v + 1) << 16, m)):
        t, l, r = map(int, input().split())
        u.add(l - 1)
        u.add(r)
        q0[i & 0xffff] = f(t, l - 1, r)
    u = list(u)
    u.sort()
    z = [0] * len(u)
    l1 = pow(2, (len(u) + 1).bit_length())
    l2 = 2 * l1
    tree = [0] * (l2 << 2)
    for i in range(len(u) - 1):
        y[u[i]] = i
        now, c, m0, m1 = s[u[i]], 0, 0, 0
        l0, l1, r0, r1 = 0, 0, 0, 0
        for j in range(u[i], u[i + 1]):
            if s[j] ^ now:
                if l0 == l1 == 0:
                    if not now:
                        l0 = c
                    else:
                        l1 = c
                if not now and m0 < c:
                    m0 = c
                elif now and m1 < c:
                    m1 = c
                c = 0
                now ^= 1
            c += 1
        if not now:
            r0, m0 = c, max(m0, c)
        else:
            r1, m1 = c, max(m1, c)
        if l0 == l1 == 0:
            l0, l1 = r0, r1
        k = (i ^ (l2 >> 1)) << 2
        tree[k ^ 1], tree[k ^ 2], tree[k ^ 3] = f(l0, r0, m0), f(l1, r1, m1), u[i + 1] - u[i]
    y[n] = len(u) - 1
    l1 = l2 >> 1
    for i in range((l1 - 1) << 2, 0, -4):
        j, k = i << 1, i << 1 ^ 4
        sj, sk = tree[j ^ 3], tree[k ^ 3]
        tree[i ^ 3] = sj + sk
        for l in x:
            tj, tk = tree[j ^ l], tree[k ^ l]
            lj, rj, mj = tj >> 40, (tj >> 20) & 0xfffff, tj & 0xfffff
            lk, rk, mk = tk >> 40, (tk >> 20) & 0xfffff, tk & 0xfffff
            mi = max(mj, mk, rj + lk)
            li = lj if lj ^ sj else lj + lk
            ri = rk if rk ^ sk else rk + rj
            tree[i ^ l] = f(li, ri, mi)
    u0 = set()
    for q1 in q0:
        if not q1:
            break
        l, r = y[(q1 >> 20) & 0xfffff], y[q1 & 0xfffff]
        if q1 >> 40 == 1:
            if not l in u0:
                u0.add(l)
            else:
                u0.remove(l)
            if not r in u0:
                u0.add(r)
            else:
                u0.remove(r)
            z[l] ^= 1
            z[r] ^= 1
        else:
            u0 = list(u0)
            u0.sort()
            for i in range(len(u0) >> 1):
                for j in get_segment0(u0[2 * i], u0[2 * i + 1] - 1):
                    tree[j << 2] ^= 1
                    k = j >> 1
                    while not color[k]:
                        color[k] = 1
                        k >>= 1
            seg = get_segment(l, r - 1)
            for j in seg:
                k = j
                while not color[k]:
                    color[k] = 1
                    k >>= 1
            u0 = set()
            update()
            ans0 = get_ans()
            ans.append(ans0)
    for i in range(len(u) - 1):
        if not z[i]:
            continue
        for j in range(u[i], u[i + 1]):
            s[j] ^= 1
        z[i + 1] ^= 1
sys.stdout.write("\n".join(map(str, ans)))