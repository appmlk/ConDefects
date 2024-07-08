import sys
from collections import defaultdict
from typing import TypeVar, Generic, Callable, List

T = TypeVar('T')
U = TypeVar('U')


class LazySegmentTreeInjectable(Generic[T, U]):
    def __init__(self,
                 n: int,
                 identity_data: Callable[[], T],
                 identity_lazy: Callable[[], U],
                 operation: Callable[[T, T], T],
                 mapping: Callable[[T, U], T],
                 composition: Callable[[U, U], U],
                 ):
        self.n = n
        self.depth = n.bit_length()
        self.offset = 1 << self.depth
        self.identity_data = identity_data
        self.identity_lazy = identity_lazy
        self.operation = operation
        self.mapping = mapping
        self.composition = composition
        self.data = [identity_data() for _ in range(self.offset << 1)]
        self.lazy = [identity_lazy() for _ in range(self.offset)]

    @classmethod
    def from_array(cls,
                   arr: List[T],
                   identity_data: Callable[[], T],
                   identity_lazy: Callable[[], U],
                   operation: Callable[[T, T], T],
                   mapping: Callable[[T, U], T],
                   composition: Callable[[U, U], U],
                   ):
        ins = cls(len(arr), identity_data, identity_lazy, operation, mapping, composition)
        ins.data[ins.offset:ins.offset + ins.n] = arr
        for i in range(ins.offset - 1, 0, -1):
            ins.update(i)
        return ins

    def push(self, i: int) -> None:
        if i < self.offset:
            data = self.data
            lazy = self.lazy
            lz = lazy[i]

            lch = i << 1
            rch = lch + 1
            data[lch] = self.mapping(data[lch], lz)
            data[rch] = self.mapping(data[rch], lz)
            if lch < self.offset:
                lazy[lch] = self.composition(lazy[lch], lz)
                lazy[rch] = self.composition(lazy[rch], lz)
            lazy[i] = self.identity_lazy()

    def update(self, i: int) -> None:
        lch = i << 1
        rch = lch + 1
        self.data[i] = self.operation(self.data[lch], self.data[rch])

    def all_apply(self, i: int, d: U) -> None:
        self.data[i] = self.mapping(self.data[i], d)
        if i < self.offset:
            self.lazy[i] = self.composition(self.lazy[i], d)

    def propagate(self, l: int, r: int) -> None:
        for i in range(self.depth, 0, -1):
            if ((l >> i) << i) != l:
                self.push(l >> i)
            if ((r >> i) << i) != r:
                self.push((r - 1) >> i)

    def range_update(self, l: int, r: int, d: U) -> None:
        l += self.offset
        r += self.offset
        self.propagate(l, r)

        l2 = l
        r2 = r
        while l < r:
            if (l & 1) == 1:
                self.all_apply(l, d)
                l += 1
            if (r & 1) == 1:
                r -= 1
                self.all_apply(r, d)
            l >>= 1
            r >>= 1
        l = l2
        r = r2

        for i in range(1, self.depth + 1):
            if ((l >> i) << i) != l:
                self.update(l >> i)
            if ((r >> i) << i) != r:
                self.update((r - 1) >> i)

    def range_query(self, l: int, r: int) -> T:
        l += self.offset
        r += self.offset

        self.propagate(l, r)
        sml = self.identity_data()
        smr = self.identity_data()
        while l < r:
            if (l & 1) == 1:
                sml = self.operation(sml, self.data[l])
                l += 1
            if (r & 1) == 1:
                r -= 1
                smr = self.operation(self.data[r], smr)
            l >>= 1
            r >>= 1
        return self.operation(sml, smr)

    def point_set(self, p: int, d: T) -> None:
        p += self.offset
        for i in range(self.depth, 0, -1):
            self.push(p >> i)
        self.data[p] = d
        for i in range(1, self.depth + 1):
            self.update(p >> i)

    def point_get(self, p: int) -> T:
        p += self.offset
        for i in range(self.depth, 0, -1):
            self.push(p >> i)
        return self.data[p]

    def debug_print(self) -> None:
        i = 1
        while i <= self.offset:
            print(*((cnt, xxx[idx] if idx < m else idx) for cnt, idx in self.data[i:i * 2]))
            i <<= 1
        i = 1
        while i <= self.offset:
            print(self.lazy[i:i * 2])
            i <<= 1


INF = 1 << 60
identity_data = lambda: (0, INF)
identity_lazy = int


def operation(a: T, b: T) -> T:
    if a[0] >= b[0]:
        return a
    return b


def mapping(a: T, b: U) -> T:
    return (a[0] + b, a[1])


def composition(a: U, b: U) -> U:
    return a + b


input = sys.stdin.buffer.readline
n = int(input())
sections = []
xxx = set()
for _ in range(n):
    l, r = map(int, input().split())
    sections.append((l, r))
    xxx.add(l - 1)
    xxx.add(l)
    xxx.add(l + 1)
    xxx.add(r - 1)
    xxx.add(r)
    xxx.add(r + 1)
xxx = sorted(xxx)
rev = {x: i for i, x in enumerate(xxx)}
m = len(xxx)

# assert xxx[0] != -1  # 関係ない
# assert xxx[-1] != 1_000_000_001  # 関係ない

init = [(0, i) for i in range(m)]
lst = LazySegmentTreeInjectable.from_array(init, identity_data, identity_lazy, operation, mapping, composition)

sections_l = defaultdict(list)
sections_r = defaultdict(int)
for l, r in sections:
    i = rev[l]
    j = rev[r]
    sections_l[i].append(j)
    sections_r[j] += 1
    lst.range_update(i + 1, j, 1)

ans_count = 0
ans_l = 0
ans_r = 1
for l in range(m):
    if l in sections_l:
        for r in sections_l[l]:
            lst.range_update(l + 1, r, -1)
    if l in sections_r:
        lst.range_update(l + 1, lst.offset, -sections_r[l])

    cnt, r = lst.data[1]
    # print(f'{l=} {cnt=} {r=}')
    if ans_count < cnt and 0 <= xxx[l] < xxx[r] <= 1_000_000_000:
        ans_count = cnt
        ans_l = xxx[l]
        ans_r = xxx[r]

    if l in sections_l:
        for r in sections_l[l]:
            lst.range_update(r + 1, lst.offset, 1)

    # print(f'{l=} {ans_count=} {ans_l=} {ans_r=}')

cnt, r = lst.data[1]
assert cnt == 0 and r == 0

print(ans_l, ans_r)
