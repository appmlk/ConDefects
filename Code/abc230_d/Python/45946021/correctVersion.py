import sys
sys.setrecursionlimit(10**7)
import re
import copy
import bisect
import math
import itertools
import more_itertools
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

def gr(l):
    res = itertools.groupby(l)
    return list([(key, len(list(v))) for key, v in res])

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
        a.append(input().split())
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

# from decimal import *
# def myround(x, k):
#     if k < 0:
#         return float(Decimal(str(x)).quantize(Decimal('1E' + str(k+1)), rounding = ROUND_HALF_UP))
#     else:
#         return int(Decimal(str(x)).quantize(Decimal('1E' + str(k+1)), rounding = ROUND_HALF_UP))

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

# https://github.com/tatyam-prime/SortedSet/blob/main/SortedSet.py
import math
from bisect import bisect_left, bisect_right
from typing import Generic, Iterable, Iterator, List, Tuple, TypeVar, Optional
T = TypeVar('T')

class SortedSet(Generic[T]):
    BUCKET_RATIO = 50
    REBUILD_RATIO = 170

    def _build(self, a: Optional[List[T]] = None) -> None:
        "Evenly divide `a` into buckets."
        if a is None: a = list(self)
        size = len(a)
        bucket_size = int(math.ceil(math.sqrt(size / self.BUCKET_RATIO)))
        self.a = [a[size * i // bucket_size : size * (i + 1) // bucket_size] for i in range(bucket_size)]
    
    def __init__(self, a: Iterable[T] = []) -> None:
        "Make a new SortedSet from iterable. / O(N) if sorted and unique / O(N log N)"
        a = list(a)
        self.size = len(a)
        if not all(a[i] < a[i + 1] for i in range(len(a) - 1)):
            a = sorted(set(a))
        self._build(a)

    def __iter__(self) -> Iterator[T]:
        for i in self.a:
            for j in i: yield j

    def __reversed__(self) -> Iterator[T]:
        for i in reversed(self.a):
            for j in reversed(i): yield j
    
    def __eq__(self, other) -> bool:
        return list(self) == list(other)
    
    def __len__(self) -> int:
        return self.size
    
    def __repr__(self) -> str:
        return "SortedSet" + str(self.a)
    
    def __str__(self) -> str:
        s = str(list(self))
        return "{" + s[1 : len(s) - 1] + "}"

    def _position(self, x: T) -> Tuple[List[T], int]:
        "Find the bucket and position which x should be inserted. self must not be empty."
        for a in self.a:
            if x <= a[-1]: break
        return (a, bisect_left(a, x))

    def __contains__(self, x: T) -> bool:
        if self.size == 0: return False
        a, i = self._position(x)
        return i != len(a) and a[i] == x

    def add(self, x: T) -> bool:
        "Add an element and return True if added. / O(竏哢)"
        if self.size == 0:
            self.a = [[x]]
            self.size = 1
            return True
        a, i = self._position(x)
        if i != len(a) and a[i] == x: return False
        a.insert(i, x)
        self.size += 1
        if len(a) > len(self.a) * self.REBUILD_RATIO:
            self._build()
        return True
    
    def _pop(self, a: List[T], i: int) -> T:
        ans = a.pop(i)
        self.size -= 1
        if not a: self._build()
        return ans

    def discard(self, x: T) -> bool:
        "Remove an element and return True if removed. / O(竏哢)"
        if self.size == 0: return False
        a, i = self._position(x)
        if i == len(a) or a[i] != x: return False
        self._pop(a, i)
        return True
    
    def lt(self, x: T) -> Optional[T]:
        "Find the largest element < x, or None if it doesn't exist."
        for a in reversed(self.a):
            if a[0] < x:
                return a[bisect_left(a, x) - 1]

    def le(self, x: T) -> Optional[T]:
        "Find the largest element <= x, or None if it doesn't exist."
        for a in reversed(self.a):
            if a[0] <= x:
                return a[bisect_right(a, x) - 1]

    def gt(self, x: T) -> Optional[T]:
        "Find the smallest element > x, or None if it doesn't exist."
        for a in self.a:
            if a[-1] > x:
                return a[bisect_right(a, x)]

    def ge(self, x: T) -> Optional[T]:
        "Find the smallest element >= x, or None if it doesn't exist."
        for a in self.a:
            if a[-1] >= x:
                return a[bisect_left(a, x)]
    
    def __getitem__(self, i: int) -> T:
        "Return the i-th element."
        if i < 0:
            for a in reversed(self.a):
                i += len(a)
                if i >= 0: return a[i]
        else:
            for a in self.a:
                if i < len(a): return a[i]
                i -= len(a)
        raise IndexError
    
    def pop(self, i: int = -1) -> T:
        "Pop and return the i-th element."
        if i < 0:
            for a in reversed(self.a):
                i += len(a)
                if i >= 0: return self._pop(a, i)
        else:
            for a in self.a:
                if i < len(a): return self._pop(a, i)
                i -= len(a)
        raise IndexError
    
    def index(self, x: T) -> int:
        "Count the number of elements < x."
        ans = 0
        for a in self.a:
            if a[-1] >= x:
                return ans + bisect_left(a, x)
            ans += len(a)
        return ans

    def index_right(self, x: T) -> int:
        "Count the number of elements <= x."
        ans = 0
        for a in self.a:
            if a[-1] > x:
                return ans + bisect_right(a, x)
            ans += len(a)
        return ans

# https://github.com/tatyam-prime/SortedSet/blob/main/SortedMultiset.py
import math
from bisect import bisect_left, bisect_right
from typing import Generic, Iterable, Iterator, List, Tuple, TypeVar, Optional
T = TypeVar('T')

class SortedMultiset(Generic[T]):
    BUCKET_RATIO = 50
    REBUILD_RATIO = 170

    def _build(self, a: Optional[List[T]] = None) -> None:
        "Evenly divide `a` into buckets."
        if a is None: a = list(self)
        size = len(a)
        bucket_size = int(math.ceil(math.sqrt(size / self.BUCKET_RATIO)))
        self.a = [a[size * i // bucket_size : size * (i + 1) // bucket_size] for i in range(bucket_size)]
    
    def __init__(self, a: Iterable[T] = []) -> None:
        "Make a new SortedMultiset from iterable. / O(N) if sorted / O(N log N)"
        a = list(a)
        self.size = len(a)
        if not all(a[i] <= a[i + 1] for i in range(len(a) - 1)):
            a = sorted(a)
        self._build(a)

    def __iter__(self) -> Iterator[T]:
        for i in self.a:
            for j in i: yield j

    def __reversed__(self) -> Iterator[T]:
        for i in reversed(self.a):
            for j in reversed(i): yield j
    
    def __eq__(self, other) -> bool:
        return list(self) == list(other)
    
    def __len__(self) -> int:
        return self.size
    
    def __repr__(self) -> str:
        return "SortedMultiset" + str(self.a)
    
    def __str__(self) -> str:
        s = str(list(self))
        return "{" + s[1 : len(s) - 1] + "}"

    def _position(self, x: T) -> Tuple[List[T], int]:
        "Find the bucket and position which x should be inserted. self must not be empty."
        for a in self.a:
            if x <= a[-1]: break
        return (a, bisect_left(a, x))

    def __contains__(self, x: T) -> bool:
        if self.size == 0: return False
        a, i = self._position(x)
        return i != len(a) and a[i] == x

    def count(self, x: T) -> int:
        "Count the number of x."
        return self.index_right(x) - self.index(x)

    def add(self, x: T) -> None:
        "Add an element. / O(竏哢)"
        if self.size == 0:
            self.a = [[x]]
            self.size = 1
            return
        a, i = self._position(x)
        a.insert(i, x)
        self.size += 1
        if len(a) > len(self.a) * self.REBUILD_RATIO:
            self._build()
    
    def _pop(self, a: List[T], i: int) -> T:
        ans = a.pop(i)
        self.size -= 1
        if not a: self._build()
        return ans

    def discard(self, x: T) -> bool:
        "Remove an element and return True if removed. / O(竏哢)"
        if self.size == 0: return False
        a, i = self._position(x)
        if i == len(a) or a[i] != x: return False
        self._pop(a, i)
        return True

    def lt(self, x: T) -> Optional[T]:
        "Find the largest element < x, or None if it doesn't exist."
        for a in reversed(self.a):
            if a[0] < x:
                return a[bisect_left(a, x) - 1]

    def le(self, x: T) -> Optional[T]:
        "Find the largest element <= x, or None if it doesn't exist."
        for a in reversed(self.a):
            if a[0] <= x:
                return a[bisect_right(a, x) - 1]

    def gt(self, x: T) -> Optional[T]:
        "Find the smallest element > x, or None if it doesn't exist."
        for a in self.a:
            if a[-1] > x:
                return a[bisect_right(a, x)]

    def ge(self, x: T) -> Optional[T]:
        "Find the smallest element >= x, or None if it doesn't exist."
        for a in self.a:
            if a[-1] >= x:
                return a[bisect_left(a, x)]
    
    def __getitem__(self, i: int) -> T:
        "Return the i-th element."
        if i < 0:
            for a in reversed(self.a):
                i += len(a)
                if i >= 0: return a[i]
        else:
            for a in self.a:
                if i < len(a): return a[i]
                i -= len(a)
        raise IndexError
    
    def pop(self, i: int = -1) -> T:
        "Pop and return the i-th element."
        if i < 0:
            for a in reversed(self.a):
                i += len(a)
                if i >= 0: return self._pop(a, i)
        else:
            for a in self.a:
                if i < len(a): return self._pop(a, i)
                i -= len(a)
        raise IndexError

    def index(self, x: T) -> int:
        "Count the number of elements < x."
        ans = 0
        for a in self.a:
            if a[-1] >= x:
                return ans + bisect_left(a, x)
            ans += len(a)
        return ans

    def index_right(self, x: T) -> int:
        "Count the number of elements <= x."
        ans = 0
        for a in self.a:
            if a[-1] > x:
                return ans + bisect_right(a, x)
            ans += len(a)
        return ans

def dijkstra(g, st):
    vi = set()
    res = [inf for i in range(len(g))]
    res[st] = 0
    s = SortedSet([])
    s.add((0, st))
    while len(s) != 0:
        dis, now = s.pop(0)
        vi.add(now)
        # print(s, res, now, dis)
        for to in g[now].keys():
            if to in vi:
                continue
            w = g[now][to]
            if dis + w <= res[to]:
                if res[to] == inf:
                    s.add((dis + w, to))
                    res[to] = dis + w
                else:
                    r = s.discard((res[to], to))
                    if r == False:
                        print('discard error')
                        print(s)
                        print(res[to], to)
                    s.add((dis + w, to))
                    res[to] = dis + w
    return res

def tarjan(g):
    n = len(g)
    scc, s, p = [], [], []
    q = [i for i in range(n)]
    state = [0] * n
    while q:
        node = q.pop()
        if node < 0:
            d = state[~node] - 1
            if p[-1] > d:
                scc.append(s[d:])
                del s[d:]
                p.pop()
                for v in scc[-1]:
                    state[v] = -1
        elif state[node] > 0:
            while p[-1] > state[node]:
                p.pop()
        elif state[node] == 0:
            s.append(node)
            p.append(len(s))
            state[node] = len(s)
            q.append(~node)
            q.extend(g[node])
    return scc

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

v4 = [[-1, 0], [0, -1], [0, 1], [1, 0]]
inf = float('inf')
ans = inf
cnt=0
ay="Yes"
an="No"
#main
n, d = gl()
a = glm(n, 2)
a = list(sorted(a, key = lambda x: x[1]))
now = a[0]
for i in range(1, n):
    l, r = a[i]
    if now[1] + d <= l:
        cnt += 1
        now = [l, r]
cnt += 1
print(cnt)
