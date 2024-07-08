class FenwickTree:
    def __init__(self, n: int):
        self.n = n
        self.arr = [0] * (n + 1)
        # n以下の最大の2冪
        self.powlog_n = 2 ** (n.bit_length() - 1)

    def add(self, i: int, x: int):
        """`a[i] += x`

        Args:
            i (int): 0-indexed
            x (int): to add

        Note:
            If `i` is out of `[0, n)`, do nothing.
        """
        if i < 0:
            return
        i += 1
        arr = self.arr
        n = self.n
        while i <= n:
            arr[i] += x
            # arr[i] %= mod
            i += i & -i

    def sum(self, i: int) -> int:
        """get `sum(a[:i])`

        Args:
            i (int): 0-indexed

        Returns:
            int: `sum(a[:i])`
        """
        ans = 0
        arr = self.arr
        # mod = self.mod
        if i > self.n:
            i = self.n
        while i > 0:
            ans += arr[i]
            # ans %= mod
            i -= i & -i
        return ans

    def sum2(self, i: int, j: int) -> int:
        """get `sum(a[i:j])`

        Args:
            i (int): 0-indexed begin
            j (int): 0-indexed end

        Returns:
            int: `sum(a[i:j])`
        """
        ans = self.sum(j) - self.sum(i)
        # ans %= mod
        return ans

    def lower_bound(self, w: int) -> int:
        """lower_bound

        Args:
            w (int): sum

        Returns:
            int: min `x`(>= 0, 0-indexed)  s.t.  `sum(a[:x])` >= w
                `n + 1` if w > `sum(a[:n])`

        Note:
            expect `a[i]` >= 0 for all `i`

        Reference:
            http://hos.ac/slides/20140319_bit.pdf - p.75
        """
        if w <= 0:
            return 0
        arr = self.arr
        n = self.n
        k = self.powlog_n
        x = 0
        while k > 0:
            if x + k <= n and arr[x + k] < w:
                x += k
                w -= arr[x]
            k //= 2
        return x + 1

    def get(self, i: int):
        """index access `a[i]`

        Args:
            i (int): 0-indexed

        Returns:
            int | None: `a[i]` if 0 <= `i` < n
                else `None`
        """
        if 0 <= i < self.n:
            return self.sum2(i, i + 1)
        else:
            return None

    def __repr__(self) -> str:
        a = [self.get(i) for i in range(self.n)]
        a_s = " ".join(map(str, a))
        return f"FenwickTree({self.n})[{a_s}]"


from sys import stdin
def input():
    return stdin.readline().rstrip()

n, k = map(int, input().split())
lines = [tuple(map(int, input().split())) for i in range(n)]
from math import atan2, acos, pi, hypot
lines = [(atan2(a / c, b / c) if c else atan2(a, b), abs(c) / hypot(a, b)) for a, b, c in lines]

# n, m = map(int, input().split())
# seg = [tuple(map(int, input().split())) for i in range(m)]
tau = 2 * pi
def within(rad):
    seg = []
    s = set()
    for th0, d in lines:
        if d / rad >= 1:
            continue
        th = acos(d / rad)
        l = (th0 + th) % tau
        r = (th0 - th) % tau
        if l > r:
            l, r = r, l
        seg.append((l, r))
        s.add(l)
        s.add(r)
    s = sorted(s)
    toi = {x: i for i, x in enumerate(s)}
    ms = len(s)
    g = [[] for i in range(ms)]
    for l, r in seg:
        g[toi[l]].append(toi[r])

    ft = FenwickTree(ms)
    ans = 0
    for l in range(ms):
        for r in g[l]:
            ans += ft.sum2(l + 1, r)
        for r in g[l]:
            ft.add(r, 1)
    return ans

lo = 0
hi = 1
while within(hi) < k:
    lo, hi = hi, hi * 2

for i in range(15):
    mi = (hi + lo) / 2
    if within(mi) < k:
        lo = mi
    else:
        hi = mi
print(hi)


