# begin template
# begin import
# import pypyjit
# pypyjit.set_param('max_unroll_recursion=-1')
# import sys
# sys.setrecursionlimit(10**7)
import sys
from collections import deque
from collections import defaultdict
from collections import Counter
from copy import copy, deepcopy
from functools import cmp_to_key as cmpk
# end import
# begin io
def input():
    return sys.stdin.readline().rstrip("\r\n")

def ii(): return int(input())
def gl(): return list(map(int, input().split()))
def gs(): return input().split()
def glm(h, w):
    a = []
    for i in range(h):
        a.append(gl())
    return a
def gsm(h):
    a = []
    for i in range(h):
        a.append(input().split())
    return a
def pyn(con, yes = 'Yes', no = 'No'):
    if con: print('Yes')
    else: print('No')
def perr(*l):
    print(l, file = sys.stderr)
# end io

# unionfind
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
# end unionfind

# begin utils
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
# end utils

# begin SortedSet
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
        "Add an element and return True if added. / O(鬯ｩ蛹・ｽｽ・ｶ髣包ｽｳ隶壹・・ｽ・ｨ郢晢ｽｻ"
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
        "Remove an element and return True if removed. / O(鬯ｩ蛹・ｽｽ・ｶ髣包ｽｳ隶壹・・ｽ・ｨ郢晢ｽｻ"
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
        "count the number of x."
        return self.index_right(x) - self.index(x)

    def add(self, x: T) -> None:
        "Add an element. / O(鬯ｩ蛹・ｽｽ・ｶ髣包ｽｳ隶壹・・ｽ・ｨ郢晢ｽｻ"
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
        "Remove an element and return True if removed. / O(鬯ｩ蛹・ｽｽ・ｶ髣包ｽｳ隶壹・・ｽ・ｨ郢晢ｽｻ"
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

# end SortedSet

# begin heapq
# https://stackoverflow.com/questions/2501457/what-do-i-use-for-a-max-heap-implementation-in-python#answer-40455775
from heapq import heappop, heappush, heapify
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
# end heapq

# begin const
al = "abcdefghijklmnopqrstuvwxyz"
au = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
yes='Yes'
no='No'
v4 = [[-1, 0], [0, -1], [0, 1], [1, 0]]
inf = float('inf')
ans = inf
cnt=0
_log = True
# end const
# end template

#main
h, w, n = gl()
sy, sx = gl()
sy -= 1
sx -= 1
gy, gx = gl()
gy -= 1
gx -= 1
ds = set()
dx = {}
dy = {}
for i in range(n):
    y, x = gl()
    y -= 1
    x -= 1
    ds.add((y, x))
    if not y in dy.keys(): dy[y] = SortedSet()
    dy[y].add(x)
    if not x in dx.keys(): dx[x] = SortedSet()
    dx[x].add(y)
vi = set()
q = deque()
q.append((0, sy, sx, None, None))
for v in v4:
    ny = sy + v[0]
    nx = sx + v[1]
    if 0 <= ny < h and 0 <= nx < w and (ny, nx) in vi:
        vi.add(y, x, v[0], v[1])
while len(q) != 0:
    (now, y, x, vy, vx) = q.popleft()
    if y == gy and x == gx:
        print(now)
        exit()
    # print(y, x)
    if y in dy.keys() and (left := dy[y].le(x)) != None and (not (y, left+1, 0, -1) in vi):
        q.append((now + 1, y, left + 1, 0, -1))
        vi.add((y, left + 1, 0, -1))
    if y in dy.keys() and (right := dy[y].ge(x)) != None and (not (y, right- 1, 0, 1) in vi):
        q.append((now + 1, y, right - 1, 0, 1))
        vi.add((y, right - 1, 0, 1))
    if x in dx.keys() and (up := dx[x].le(y)) != None and (not (up + 1, x, -1, 0) in vi):
        q.append((now + 1, up + 1, x, -1, 0))
        vi.add((up + 1, x, -1, 0))
    if x in dx.keys() and (down := dx[x].ge(y)) != None and (not (down - 1, x, 1, 0) in vi):
        q.append((now + 1, down - 1, x, 1, 0))
        vi.add((down - 1, x, 1, 0))
print(-1)
