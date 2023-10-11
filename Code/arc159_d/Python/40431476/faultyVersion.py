class segtree:
    """It is the data structure for monoids (S, op), i.e., the algebraic structure that satisfies the following properties.

    >   associativity: op(op(a, b), c) = op(a, op(b, c)) for all a, b, c in S

    >   existence of the identity element: op(a, e) = op(e, a) = a for all a in S

    Given an array of length n, it processes the following queries in O(log n) time
    (see Appendix in the document of AC Library for further details).

    >   Updating an element

    >   Calculating the product of the elements of an interval

    For simplicity, in this document, we assume that the oracles `op` and `e` work in constant time.
    If these oracles work in O(T) time, each time complexity appear in this document is multipled by O(T).
    """

    __slots__ = ["op", "e", "n", "size", "d"]

    def __init__(self, n_or_list, op, e):
        """The following should be defined.

        >   The binary operation `op(a, b)`

        >   The identity element `e()`

        For example, for Range Minimum Query, the definitions are as follows.

        ```python
        seg = segtree(10, min, lambda: 10 ** 9)
        ```

        >   If `n_or_list` is an integer, it creates an array `a` of length `n_or_list`. All the elements are initialized to `e()`.

        >   If `n_or_list` is a list, it creates an array `a` of length `len(n_or_list)`, initialized to `n_or_list`.

        >   Otherwise, it raises `TypeError`.

        Constraints
        -----------

        >   0 <= n <= 10 ** 8

        Complexity
        -----------

        >   O(n)
        """
        self.op = op
        self.e = e
        if isinstance(n_or_list, int):
            self.n = n_or_list
            self.size = 1 << ((self.n - 1).bit_length())
            self.d = [self.e() for _ in range(self.size * 2)]
        elif isinstance(n_or_list, list):
            self.n = len(n_or_list)
            self.size = 1 << ((self.n - 1).bit_length())
            self.d = (
                [self.e() for _ in range(self.size)]
                + n_or_list
                + [self.e() for _ in range(self.size - self.n)]
            )
            for i in range(self.size - 1, 0, -1):
                self._update(i)
        else:
            raise TypeError(
                f"The argument 'n_or_list' must be an integer or a list, not {type(n_or_list).__name__}"
            )

    def set(self, p, x):
        """It assigns x to `a[p]`.

        Constraints
        -----------

        >   0 <= p < n

        Complexity
        ----------

        >   O(log n)
        """
        assert 0 <= p < self.n
        p += self.size
        self.d[p] = x
        p //= 2
        while p > 0:
            self._update(p)
            p //= 2

    def get(self, p):
        """It returns `a[p]`.

        Constraints
        -----------

        >   0 <= p < n

        Complexity
        ----------

        >   O(1)
        """
        assert 0 <= p < self.n
        return self.d[p + self.size]

    def prod(self, l, r):
        """It returns `op(a[l], ..., a[r - 1])`, assuming the properties of the monoid.
        It returns `e()` if l = r.

        Constraints
        -----------

        >   0 <= l <= r <= n

        Complexity
        ----------

        >   O(log n)
        """
        assert 0 <= l <= r <= self.n
        sml = self.e()
        smr = self.e()
        l += self.size
        r += self.size
        while l < r:
            if l % 2:
                sml = self.op(sml, self.d[l])
                l += 1
            if r % 2:
                r -= 1
                smr = self.op(self.d[r], smr)
            l //= 2
            r //= 2
        return self.op(sml, smr)

    def all_prod(self):
        """It returns `op(a[0], ..., a[n - 1])`, assuming the properties of the monoid.
        It returns `e()` if n = 0.

        Complexity
        ----------

        >   O(1)
        """
        return self.d[1]

    def max_right(self, l, f):
        """It returns an index `r` that satisfies both of the following.

        >   `r == l` or `f(op(a[l], a[l + 1], ..., a[r - 1])) == True`

        >   `r == n` or `f(op(a[l], a[l + 1], ..., a[r])) == False`

        If `f` is monotone, this is the maximum `r` that satisfies `f(op(a[l], a[l + 1], ..., a[r - 1])) == True`.

        Constraints
        -----------

        >   if `f` is called with the same argument, it returns the same value, i.e., `f` has no side effect.

        >   `f(e()) == True`

        >   0 <= l <= n

        Complexity
        ----------

        >   O(log n)
        """
        assert 0 <= l <= self.n
        assert f(self.e())
        if l == self.n:
            return self.n
        l += self.size
        sm = self.e()
        while True:
            while l % 2 == 0:
                l //= 2
            if not f(self.op(sm, self.d[l])):
                while l < self.size:
                    l *= 2
                    if f(self.op(sm, self.d[l])):
                        sm = self.op(sm, self.d[l])
                        l += 1
                return l - self.size
            sm = self.op(sm, self.d[l])
            l += 1
            if l == l & -l:
                break
        return self.n

    def min_left(self, r, f):
        """It returns an index `l` that satisfies both of the following.

        >   `l == r` or `f(op(a[l], a[l + 1], ..., a[r - 1])) == True`

        >   `l == 0` or `f(op(a[l - 1], a[l], ..., a[r - 1])) == False`

        If `f` is monotone, this is the minimum `l` that satisfies `f(op(a[l], a[l + 1], ..., a[r - 1])) == True`.

        Constraints
        -----------

        >   if `f` is called with the same argument, it returns the same value, i.e., `f` has no side effect.

        >   `f(e()) == True`

        >   0 <= r <= n

        Complexity
        ----------

        >   O(log n)
        """
        assert 0 <= r <= self.n
        assert f(self.e())
        if r == 0:
            return 0
        r += self.size
        sm = self.e()
        while True:
            r -= 1
            while r > 1 and r % 2:
                r //= 2
            if not f(self.op(self.d[r], sm)):
                while r < self.size:
                    r = 2 * r + 1
                    if f(self.op(self.d[r], sm)):
                        sm = self.op(self.d[r], sm)
                        r -= 1
                return r + 1 - self.size
            sm = self.op(self.d[r], sm)
            if r == r & -r:
                break
        return 0

    def _update(self, k):
        self.d[k] = self.op(self.d[2 * k], self.d[2 * k + 1])


# 「LIS の最終値」から「LIS の長さ」を管理する。
#
# [l_i, r_i] を採用する場合の更新を考える。
#
# r_j <= l_i なる r_j については、
# 右端から長さへの情報を管理すればよい。
#
# それ以外は、
# l_i <= r_j となる区間 [l_j, r_j] のうち、
# 切片の最大値を取ってくればよい。
# これを用いて、右端から長さへの情報も更新

N = int(input())
lrs = []
Xs = set()
for _ in range(N):
    l, r = map(int, input().split())
    l -= 1
    lrs.append((l, r))
    Xs.add(l)
    Xs.add(r)

x2X = sorted(Xs)
X2x = {X: x for x, X in enumerate(Xs)}
seg_len = len(x2X)
INF = 10**18
disjoint_seg = segtree(seg_len, max, lambda: 0)
slice_seg = segtree(seg_len, max, lambda: -INF)
for L, R in lrs:
    l = X2x[L]
    r = X2x[R]
    disjoint_lef_len = disjoint_seg.prod(0, l)
    disjoint_rig_len = disjoint_lef_len + R - L
    slice_max = slice_seg.prod(l, seg_len)
    slice_rig_len = R + slice_max
    new_len = max(disjoint_rig_len, slice_rig_len)
    new_slice = new_len - R
    # print(new_len, new_slice)
    disjoint_seg.set(r, max(disjoint_seg.get(r), new_len))
    slice_seg.set(r, max(slice_seg.get(r), new_slice))
    # print([disjoint_seg.get(i) for i in range(seg_len)])
    # print([slice_seg.get(i) for i in range(seg_len)])
print(disjoint_seg.all_prod())
