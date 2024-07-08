import pypyjit
pypyjit.set_param('max_unroll_recursion=-1')
import sys
sys.setrecursionlimit(10**7)
import re
# import more_itertools
import functools
import sys
import bisect
import math
import itertools
from collections import deque
from collections import defaultdict
from collections import Counter
from copy import copy, deepcopy
from heapq import heapify, heappush, heappop, heappushpop, heapreplace
from functools import cmp_to_key as cmpk
al = "abcdefghijklmnopqrstuvwxyz"
au = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

# io
# begin fastio
import os
import sys
from io import BytesIO, IOBase

BUFSIZE = 8192


class FastIO(IOBase):
    newlines = 0

    def __init__(self, file):
        self._fd = file.fileno()
        self.buffer = BytesIO()
        self.writable = "x" in file.mode or "r" not in file.mode
        self.write = self.buffer.write if self.writable else None

    def read(self):
        while True:
            b = os.read(self._fd, max(os.fstat(self._fd).st_size, BUFSIZE))
            if not b:
                break
            ptr = self.buffer.tell()
            self.buffer.seek(0, 2), self.buffer.write(b), self.buffer.seek(ptr)
        self.newlines = 0
        return self.buffer.read()

    def readline(self):
        while self.newlines == 0:
            b = os.read(self._fd, max(os.fstat(self._fd).st_size, BUFSIZE))
            self.newlines = b.count(b"\n") + (not b)
            ptr = self.buffer.tell()
            self.buffer.seek(0, 2), self.buffer.write(b), self.buffer.seek(ptr)
        self.newlines -= 1
        return self.buffer.readline()

    def flush(self):
        if self.writable:
            os.write(self._fd, self.buffer.getvalue())
            self.buffer.truncate(0), self.buffer.seek(0)


class IOWrapper(IOBase):
    def __init__(self, file):
        self.buffer = FastIO(file)
        self.flush = self.buffer.flush
        self.writable = self.buffer.writable
        self.write = lambda s: self.buffer.write(s.encode("ascii"))
        self.read = lambda: self.buffer.read().decode("ascii")
        self.readline = lambda: self.buffer.readline().decode("ascii")


sys.stdin = IOWrapper(sys.stdin)
# sys.stdout = IOWrapper(sys.stdout)

_log = True # if False, perr() do notiong
import sys
import itertools
def input(): return sys.stdin.readline().rstrip()
def ii(): return int(sys.stdin.readline().rstrip())
def gl(): return list(map(int, sys.stdin.readline().split()))
def gs(): return list(input().split())
def gr(l):
    res = itertools.groupby(l)
    return list([(key, len(list(v))) for key, v in res])

def glm(h,w):
    a = []
    for i in range(h):
        a.append(gl())
    return a

def gsm(h):
    a = []
    for i in range(h):
        a.append(input().split())
    return a

def perr(*l):
    if _log:
        print('\033[33m', end = '', file = sys.stderr)
        print(*l, '\033[0m', file=sys.stderr)

def pex(con):
    pyn(con)
    exit()

def pyn(con, yes = 'Yes', no = 'No'):
    if con:
        print(yes)
    else:
        print(no)

def py(yes = 'Yes'):
    print(yes)

def pn(no = 'No'):
    print(no)


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
# end io
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
        # return group_members
        return dict(group_members)

    def __str__(self):
        return '\n'.join(f'{r}: {m}' for r, m in self.all_group_members().items())

# begin util/util
def rev(a):
    a = a[:]
    return list(reversed(a))

def drev(d):
    newd = {}
    for k in rev(list(d.keys())):
        newd[k] = d[k]
    return newd

def dvsort(d):
    return dict(sorted(d.items(), key = lambda x: x[1]))

def dksort(d):
    return dict(sorted(d.items()))

def yn(con, yes = 'Yes', no = 'No'):
    if con:
        return yes
    else:
        return no

def kiriage(n, r):
    if n % r == 0:
        return n // r
    else:
       return (n // r) + 1

def ketawa(n):
    ans = 0
    s = str(n)
    for i in s:
        ans += int(i)
    return ans

def sinhen(n, l):
    if n < l:
        return [n]
    else:
        return sinhen(n // l, l) + [n % l]

import re
def search(q, b):
    return re.search(b, q)

def cut_yoko(a, y):
    a_copy = deepcopy(a)
    res = []
    for x in range(len(a[0])):
        res.append(a_copy[y][x])
    return res

def cut_tate(a, x):
    a_copy = deepcopy(a)
    res = []
    for y in range(len(a)):
        res.append(a_copy[y][x])
    return res

def rmwh(a):
    s = set([len(e) for e in a])
    assert len(s) == 1
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

def cntsep(a, b, k):
    r = a % k
    m = a - r
    ans = (b - m) // (k+1)
    if r > 0:
        ans -= 1
    return ans

def compress(a, base = 1):
    s = set()
    for e in a:
        s.add(e)
    s = list(sorted(s))
    d = {}
    for i in range(len(s)):
        d[s[i]] = i
    b = []
    for e in a:
        b.append(d[e] + base)
    return b

# from decimal import *
def myround(x, k):
    if k < 0:
        return float(Decimal(str(x)).quantize(Decimal('1E' + str(k+1)), rounding = ROUND_HALF_UP))
    else:
        return int(Decimal(str(x)).quantize(Decimal('1E' + str(k+1)), rounding = ROUND_HALF_UP))

def rp(s, d):
    return s.translate(str.maketrans(d))

def tr(s, a, b):
    assert len(a) == len(b)
    res = []
    d = {}
    for i in len(a):
        d[a] = b[b]
    return ''.join([d[e] for e in s])# ned
# end util/util

# begin permutation
# https://strangerxxx.hateblo.jp/entry/20220201/1643705539
def next_permutation(a, l = 0, r = None):
    if r is None:
        r = len(a)
    for i in range(r - 2, l - 1, -1):
        if a[i] < a[i + 1]:
            for j in range(r - 1, i, -1):
                if a[i] < a[j]:
                    a[i], a[j] = a[j], a[i]
                    p, q = i + 1, r - 1
                    while p < q:
                        a[p], a[q] = a[q], a[p]
                        p += 1
                        q -= 1
                    return True
    return False


def prev_permutation(a, l = 0, r = None):
    if r is None:
        r = len(a)
    for i in range(r - 2, l - 1, -1):
        if a[i] > a[i + 1]:
            for j in range(r - 1, i, -1):
                if a[i] > a[j]:
                    a[i], a[j] = a[j], a[i]
                    p, q = i + 1, r - 1
                    while p < q:
                        a[p], a[q] = a[q], a[p]
                        p += 1
                        q -= 1
                    return True
    return False
# end permutation

# begin math/gcd
def lcm2(x, y):
    return (x * y) // math.gcd(x, y)

def lcm3(*ints):
    return functools.reduce(lcm2, ints)

def gcd(*ints):
    return math.gcd(*ints)
# end math/gcd

# https://github.com/tatyam-prime/SortedSet/blob/main/SortedSet.py
import math
from bisect import bisect_left, bisect_right
from typing import Generic, Iterable, Iterator, List, Tuple, TypeVar, Optional
T = TypeVar('T')

class SortedSet(Generic[T]):
    BUCKET_RATIO = 16
    SPLIT_RATIO = 24
    
    def __init__(self, a: Iterable[T] = []) -> None:
        "Make a new SortedSet from iterable. / O(N) if sorted and unique / O(N log N)"
        a = list(a)
        n = len(a)
        if any(a[i] > a[i + 1] for i in range(n - 1)):
            a.sort()
        if any(a[i] >= a[i + 1] for i in range(n - 1)):
            a, b = [], a
            for x in b:
                if not a or a[-1] != x:
                    a.append(x)
        n = self.size = len(a)
        num_bucket = int(math.ceil(math.sqrt(n / self.BUCKET_RATIO)))
        self.a = [a[n * i // num_bucket : n * (i + 1) // num_bucket] for i in range(num_bucket)]

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

    def _position(self, x: T) -> Tuple[List[T], int, int]:
        "return the bucket, index of the bucket and position in which x should be. self must not be empty."
        for i, a in enumerate(self.a):
            if x <= a[-1]: break
        return (a, i, bisect_left(a, x))

    def __contains__(self, x: T) -> bool:
        if self.size == 0: return False
        a, _, i = self._position(x)
        return i != len(a) and a[i] == x

    def add(self, x: T) -> bool:
        "Add an element and return True if added. / O(√N)"
        if self.size == 0:
            self.a = [[x]]
            self.size = 1
            return True
        a, b, i = self._position(x)
        if i != len(a) and a[i] == x: return False
        a.insert(i, x)
        self.size += 1
        if len(a) > len(self.a) * self.SPLIT_RATIO:
            mid = len(a) >> 1
            self.a[b:b+1] = [a[:mid], a[mid:]]
        return True
    
    def _pop(self, a: List[T], b: int, i: int) -> T:
        ans = a.pop(i)
        self.size -= 1
        if not a: del self.a[b]
        return ans

    def discard(self, x: T) -> bool:
        "Remove an element and return True if removed. / O(√N)"
        if self.size == 0: return False
        a, b, i = self._position(x)
        if i == len(a) or a[i] != x: return False
        self._pop(a, b, i)
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
            for b, a in enumerate(reversed(self.a)):
                i += len(a)
                if i >= 0: return self._pop(a, ~b, i)
        else:
            for b, a in enumerate(self.a):
                if i < len(a): return self._pop(a, b, i)
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
    BUCKET_RATIO = 16
    SPLIT_RATIO = 24

    def __init__(self, a: Iterable[T] = []) -> None:
        "Make a new SortedMultiset from iterable. / O(N) if sorted / O(N log N)"
        a = list(a)
        n = self.size = len(a)
        if any(a[i] > a[i + 1] for i in range(n - 1)):
            a.sort()
        num_bucket = int(math.ceil(math.sqrt(n / self.BUCKET_RATIO)))
        self.a = [a[n * i // num_bucket : n * (i + 1) // num_bucket] for i in range(num_bucket)]

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

    def _position(self, x: T) -> Tuple[List[T], int, int]:
        "return the bucket, index of the bucket and position in which x should be. self must not be empty."
        for i, a in enumerate(self.a):
            if x <= a[-1]: break
        return (a, i, bisect_left(a, x))

    def __contains__(self, x: T) -> bool:
        if self.size == 0: return False
        a, _, i = self._position(x)
        return i != len(a) and a[i] == x

    def count(self, x: T) -> int:
        "Count the number of x."
        return self.index_right(x) - self.index(x)

    def add(self, x: T) -> None:
        "Add an element. / O(√N)"
        if self.size == 0:
            self.a = [[x]]
            self.size = 1
            return
        a, b, i = self._position(x)
        a.insert(i, x)
        self.size += 1
        if len(a) > len(self.a) * self.SPLIT_RATIO:
            mid = len(a) >> 1
            self.a[b:b+1] = [a[:mid], a[mid:]]

    def _pop(self, a: List[T], b: int, i: int) -> T:
        ans = a.pop(i)
        self.size -= 1
        if not a: del self.a[b]
        return ans

    def discard(self, x: T) -> bool:
        "Remove an element and return True if removed. / O(√N)"
        if self.size == 0: return False
        a, b, i = self._position(x)
        if i == len(a) or a[i] != x: return False
        self._pop(a, b, i)
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
            for b, a in enumerate(reversed(self.a)):
                i += len(a)
                if i >= 0: return self._pop(a, ~b, i)
        else:
            for b, a in enumerate(self.a):
                if i < len(a): return self._pop(a, b, i)
                i -= len(a)
        raise IndexError

    def index(self, x: T) -> int:
        "count the number of elements < x."
        ans = 0
        for a in self.a:
            if a[-1] >= x:
                return ans + bisect_left(a, x)
            ans += len(a)
        return ans

    def index_right(self, x: T) -> int:
        "count the number of elements <= x."
        ans = 0
        for a in self.a:
            if a[-1] > x:
                return ans + bisect_right(a, x)
            ans += len(a)
        return ans

# https://stackoverflow.com/questions/2501457/what-do-i-use-for-a-max-heap-implementation-in-python#answer-40455775
class Heapq():
    # def __init__(self, arr = []):
    #     self.hq = arr
    #     heapify(self.hq)
    def __init__(self, arr = None):
        if arr == None:
            arr = []
        self.hq = arr
        heapify(self.hq)
    def pop(self): return heappop(self.hq)
    def append(self, a): heappush(self.hq, a)
    def __len__(self): return len(self.hq)
    def __getitem__(self, idx): return self.hq[idx]
    def __repr__(self): return str(self.hq)

class _MaxHeapObj(object):
  def __init__(self, val): self.val = val
  def __lt__(self, other): return self.val > other.val
  def __eq__(self, other): return self.val == other.val
  def __str__(self): return str(self.val)

class Maxheapq():
    def __init__(self, arr = []):
        self.hq = [_MaxHeapObj(e) for e in arr]
        heapify(self.hq)
    def pop(self): return heappop(self.hq).val
    def append(self, a): heappush(self.hq, _MaxHeapObj(a))
    def __len__(self): return len(self.hq)
    def __getitem__(self, idx): return self.hq[idx].val
    def __repr__(self): return str([e.val for e in self.hq])

def dijkstra(g, st):
    h = Heapq()
    h.append((0, st))
    vi = set()
    res = [inf for i in range(len(g))]
    while len(vi) != n and len(h) != 0:
        d, now = h.pop()
        if now in vi:
            continue
        vi.add(now)
        res[now] = d
        for to in g[now]:
            if not to in vi:
                h.append((d + g[now][to], to))
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

def top_sort(g):
    res = []
    vi = set()
    q = deque()
    din = [0 for i in range(len(g))]
    for i in range(len(g)):
        for e in g[i]:
            din[e] += 1
    for i in range(len(din)):
        if din[i] == 0:
            q.append(i)
    while len(q) != 0:
        st = q.popleft()
        res.append(st)
        for to in g[st]:
            din[to] -= 1
            if din[to] == 0:
                q.append(to)
    return res

# begin combination
# https://rin204.github.io/Library-Python/expansion/math/Combination.py
import math
class Combination:
    def __init__(self, n, MOD=998244353):
        n = min(n, MOD - 1)
        self.fact = [1] * (n + 1)
        self.invfact = [1] * (n + 1)
        self.MOD = MOD
        for i in range(1, n + 1):
            self.fact[i] = self.fact[i - 1] * i % MOD

        self.invfact[n] = pow(self.fact[n], MOD - 2, MOD)
        for i in range(n - 1, -1, -1):
            self.invfact[i] = self.invfact[i + 1] * (i + 1) % MOD

    def extend(self, n):
        le = len(self.fact)
        if n < le:
            return
        self.fact.extend([1] * (n - le + 1))
        self.invfact.extend([1] * (n - le + 1))
        for i in range(le, n + 1):
            self.fact[i] = self.fact[i - 1] * i % self.MOD

        self.invfact[n] = pow(self.fact[n], self.MOD - 2, self.MOD)
        for i in range(n - 1, le - 1, -1):
            self.invfact[i] = self.invfact[i + 1] * (i + 1) % self.MOD

    def nPk(self, n, k):
        if k < 0 or n < k:
            return 0
        if n >= len(self.fact):
            self.extend(n)
        return self.fact[n] * self.invfact[n - k] % self.MOD

    def nCk(self, n, k):
        if k < 0 or n < k:
            return 0
        if n >= len(self.fact):
            self.extend(n)
        return (self.fact[n] * self.invfact[n - k] % self.MOD) * self.invfact[k] % self.MOD

    def nHk(self, n, k):
        if n == 0 and k == 0:
            return 1
        return self.nCk(n + k - 1, k)

    def Catalan(self, n):
        return (self.nCk(2 * n, n) - self.nCk(2 * n, n - 1)) % self.MOD

def nCk(n, k):
    return math.comb(n, k)

def nCk_mod(n, k, mod = 998244353):
    if k < 0 or n < k:
        return 0
    res = 1
    for i in range(k):
        res *= (n - i)
        res %= mod
        res *= pow((k - i), -1, mod)
        res %= mod
    return res
# end combination

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

_log=True
def pex(con):
    pyn(con)
    exit()
def yn(con, yes = 'Yes', no = 'No'):
    if con:
        return yes
    else:
        return no
def pp(con, yes = 'Yes', no = 'No'):
    if con:
        print(yes)
    else:
        print(no)
def pyn(con, yes = 'Yes', no = 'No'):
    if con:
        print(yes)
    else:
        print(no)
def py(yes = 'Yes'):
    print(yes)
def pn(no = 'No'):
    print(no)
yes='Yes'
no='No'
v4 = [[-1, 0], [0, -1], [0, 1], [1, 0]]
inf = float('inf')
ans = inf
cnt=0
#main
n = ii()
a = glm(n, 2)
d = {}
def p(now):
    if now in d.keys(): return d[now]
    win = False
    for i in range(n - 1):
        if win:
            break
        if now & (1 << i) != 0:
            continue
        for j in range(i + 1, n):
            if win:
                break
            if now & (1 << j) != 0:
                continue
            if a[i][0] != a[j][0] and a[i][1] != a[j][1]:
                continue
            new = now
            new |= (1 << i)
            new |= (1 << j)
            res = p(new)
            if not res:
                win = True
    if win:
        d[now] = True
    else:
        d[now] = False
    return d[now]

if p(0):
    print('Takahashi')
else:
    print('Aoki')
