import re
import functools
import copy
import bisect
import math
from collections import deque
from collections import defaultdict
from collections import Counter
from heapq import heapify, heappush, heappop, heappushpop, heapreplace

al = "abcdefghijklmnopqrstuvwxyz"
au = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

def ii():
    return int(input())

def gl():
    return list(map(int, input().split()))

def gs():
    return list(input().split())

class UnionFind():
    def __init__(self, n):
        self.n = n
        self.parents = [-1] * n

    def find(self, x):
        if self.parents[x] < 0:
            return x
        else:
            self.parents[x] = self.find(self.parents[x])
            return self.parents[x]

    def union(self, x, y):
        x = self.find(x)
        y = self.find(y)

        if x == y:
            return

        if self.parents[x] > self.parents[y]:
            x, y = y, x

        self.parents[x] += self.parents[y]
        self.parents[y] = x

    def size(self, x):
        return -self.parents[self.find(x)]

    def same(self, x, y):
        return self.find(x) == self.find(y)

    def members(self, x):
        root = self.find(x)
        return [i for i in range(self.n) if self.find(i) == root]

    def roots(self):
        return [i for i, x in enumerate(self.parents) if x < 0]

    def group_count(self):
        return len(self.roots())

    def all_group_members(self):
        group_members = defaultdict(list)
        for member in range(self.n):
            group_members[self.find(member)].append(member)
        return group_members

    def __str__(self):
        return '\n'.join(f'{r}: {m}' for r, m in self.all_group_members().items())


def glm(h,w):
    a = []
    for i in range(h):
        a.append(gl())
    return a

def gsm(h,w):
    a = []
    for i in range(h):
        a.append(input())
    return a

def kiriage(n, r):
    if n % r == 0:
        return n // r
    else:
       return (n // r) + 1

def next_perm(a):
    l = copy.copy(a)
    l = list(l)
    i = len(l) - 2
    while 0 <= i and l[i] >= l[i+1]:
        i -= 1
    if i == 1:
        return False
    j = len(l) - 1
    while not (l[i] < l[j]):
        j -= 1
    l[i], l[j] = l[j], l[i]
    return l[:i+1] + rev(l[i+1:])

def yaku(n):
    ans = []
    for i in range(1, int(math.sqrt(n)) + 1):
        if n % i == 0:
            ans.append(i)
            ans.append(n // i)
    return ans

def ketawa(n):
    ans = 0
    s = str(n)
    for i in s:
        ans += int(i)
    return ans

def rev(a):
    a = a[:]
    return list(reversed(a))

def lcm2(x, y):
    return (x * y) // math.gcd(x, y)

def lcm3(*ints):
    return functools.reduce(lcm2, ints)

def gcd3(*ints):
    return functools.reduce(math.gcd, ints)

def cntsep(a, b, k):
    r = a % k
    m = a - r
    ans = (b - m) // (k+1)
    if r > 0:
        ans -= 1
    return ans

def putedges(g, idx = 0):
    n = len(g)
    e = []
    cnt2 = 0
    for i in range(n):
        for j in g[i]:
            cnt2 += 1
            e.append((i, j))
    m = len(g)
    print(n, cnt2)
    for i in e:
        if idx == 0:
            print(*[i[0], i[1]])
        else:
            print(*[i[0] + 1, i[1] + 1])

def drev(d):
    newd = {}
    for k in rev(list(d.keys())):
        newd[k] = d[k]
    return newd

def dvsort(d):
    return dict(sorted(d.items(), key = lambda x: x[1]))

def dksort(d):
    return dict(sorted(d.items()))

def rmwh(a):
    while not '#' in a[0]:
        a = a[1:]
    while not '#' in a[-1]:
        a = a[:-1]
    ok = True
    while True:
        for y in range(len(a)):
            if a[y][0] == '#':
                ok = False
        if ok:
            for y in range(len(a)):
                a[y] = a[y][1:]
        else:
            break
    ok = True
    while True:
        for y in range(len(a)):
            if a[y][-1] == '#':
                ok = False
        if ok:
            for y in range(len(a)):
                a[y] = a[y][:-1]
        else:
            break
    return a

def comb_cnt(n, k):
    return math.factorial(n) // (math.factorial(n - k) * math.factorial(k))

def sinhen(n, l):
    if n < l:
        return [n]
    else:
        return sinhen(n // l, l) + [n % l]

def cnt_com(l1, r1, l2, r2):
    if l1 > l2:
        l1, l2, r1, r2 = l2, l1, r2, r1
    if l1 <= l2 and l2 <= r2 and r2 <= r1:
        return r2 - l2
    elif l1 <= l2 and l2 <= r1 and r1 <= r2:
        return r1 - l2
    elif r1 <= l2:
        return 0

def cut_yoko(a, y):
    a_copy = copy.deepcopy(a)
    res = []
    for x in range(len(a[0])):
        res.append(a_copy[y][x])
    return res

def cut_tate(a, x):
    a_copy = copy.deepcopy(a)
    res = []
    for y in range(len(a)):
        res.append(a_copy[y][x])
    return res

def mbs(a, key):
    ng = -1
    ok = len(a)
    while abs(ok - ng) > 1:
        mid = (ok + ng) // 2
        if a[mid] >= key:
            ok = mid
        else:
            ng = mid
    return ok

def satlow(f, lower = 0, upper = 10**9):
    ng = lower
    ok = upper
    while abs(ok - ng) > 1:
        mid = (ok + ng) // 2
        if f(mid):
            ok = mid
        else:
            ng = mid
    return ok

def listsatlow(a, f):
    ng = -1
    ok = len(a)
    while abs(ok - ng) > 1:
        mid = (ok + ng) // 2
        if f(a[mid]):
            ok = mid
        else:
            ng = mid
    return ok

inf = float('inf')
ans = inf
cnt=0
ay="Yes"
an="No"
#main
'''
def mss(a, key):
    ng = len(a)
    ok = -1
    while abs(ok - ng) > 1:
        mid = (ok + ng) // 2
        if a[mid] <= key:
            ok = mid
        else:
            ng = mid
    return ok
'''
h, w, nowy, nowx = gl()
n = ii()
dy = {}
sdy = set()
dx = {}
sdx = set()
for i in range(n):
    y, x = gl()
    y -= 1
    x -= 1
    if not y in dy.keys():
        dy[y] = [-1, w]
        sdy.add(y)
    dy[y].append(x)
    if not x in dx.keys():
        dx[x] = [-1, h]
        sdx.add(x)
    dx[x].append(y)
q = ii()
x = nowx - 1
y = nowy - 1
for i in range(q):
    d, l = gs()
    l = int(l)
    if d == 'L':
        if not y in dy.keys():
            x = max(x - l, 0)
        else:
            if y in sdy:
                dy[y] = list(sorted(dy[y]))
                sdy.remove(y)
            le = mbs(dy[y], x)
            x = max(max(x - l, dy[y][max(le - 1, 0)] + 1), 0)
    elif d == 'R':
        if not y in dy.keys():
            x = min(x + l, w-1)
        else:
            if y in sdy:
                dy[y] = list(sorted(dy[y]))
                sdy.remove(y)
            rrd = mbs(dy[y], x + 1)
            x = min(min(x + l, dy[y][rrd] - 1), w - 1)
    elif d == 'U':
        if not x in dx.keys():
            y = max(y - l, 0)
        else:
            # print(dx[x])
            if x in sdx:
                dx[x] = list(sorted(dx[x]))
                sdx.remove(x)
            ue = mbs(dx[x], y -1)
            y = max(max(y - l, dx[x][max(ue - 1, 0)] + 1), 0)
    elif d == 'D':
        if not x in dx.keys():
            y = min(h-1, y + l)
        else:
            if x in sdx:
                dx[x] = list(sorted(dx[x]))
                sdx.remove(x)
            dd = mbs(dx[x], y + 1)
            y = min(min(y + l, dx[x][dd] - 1), h - 1)
    assert 0 <= y
    assert y < h
    assert 0 <= x
    assert x < w
    print(y+1, x+1)
