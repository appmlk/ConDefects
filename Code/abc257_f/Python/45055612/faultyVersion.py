# import sys
# sys.setrecursionlimit(10**6)
import re
import copy
import bisect
import math
from collections import deque
from collections import defaultdict
from collections import Counter
from heapq import heapify, heappush, heappop, heappushpop, heapreplace
from functools import cmp_to_key as cmpk
import functools
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

def zalg(s):
    n = len(s)
    a = [0] * n
    i = 1
    j = 0
    a[0] = len(s)
    l = len(s)
    while i < l:
        while i + j < l and s[j] == s[i+j]:
            j += 1
        if not j:
            i += 1
            continue
        a[i] = j
        k = 1
        while l-i > k < j - a[k]:
            a[i+k] = a[k]
            k += 1
        i += k
        j -= k
    return a

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
n, m = gl()
g = [set() for i in range(n+1)]
for i in range(m):
    a, b = gl()
    a -= 1
    b -= 1
    if a != -1:
        g[a].add(b)
        g[b].add(a)
    else:
        g[n].add(b)
        g[b].add(n)
# print(g)
dis0 = [inf for i in range(n+1)]
q = deque()
q.append((0, 0))
vi = set()
vi.add(0)
while len(q) != 0:
    st, dis = q.popleft()
    dis0[st] = dis
    for to in g[st]:
        if not to in vi:
            q.append((to, dis + 1))
            vi.add(to)
dis1 = [inf for i in range(n+1)]
q = deque()
q.append((n-1, 0))
vi = set()
vi.add(n-1)
while len(q) != 0:
    st, dis = q.popleft()
    dis1[st] = dis
    for to in g[st]:
        if not to in vi:
            q.append((to, dis + 1))
            vi.add(to)
ans = []
for i in range(n):
    res = min(dis0[n-1], dis0[n] + dis1[i])
    if res == inf:
        ans.append(-1)
    else:
        ans.append(res)
print(*ans)

