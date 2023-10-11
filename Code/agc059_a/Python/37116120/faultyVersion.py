def solve(k):
    if k <= 2:
        return k
    if k % 2 == 0:
        return (k - 2) // 2 + 2
    return (k - 1) // 2 + 1


class LazySegTree:
    "X × G --> X"
    "Fill in the brackets."
    X_e = 0
    G_e = X_e

    @classmethod
    def X_op(cls, x, y):
        return x + y

    @classmethod
    def G_op(cls, a, b):
        return cls.X_op(a, b)

    @classmethod
    def action(cls, x, a):
        return cls.X_op(x, a)

    "Up to here."

    def __init__(self, A):
        self.N = len(A)
        self.X = [self.X_e] * (2 * self.N)
        self.G = [self.G_e] * (2 * self.N)
        for i, x in enumerate(A, self.N):
            self.X[i] = x
        for i in range(self.N - 1, 0, -1):
            self.X[i] = self.X_op(self.X[i << 1], self.X[i << 1 | 1])

    def _eval_at(self, i):
        return self.action(self.X[i], self.G[i])

    def _propagate_at(self, i):
        self.X[i] = self._eval_at(i)
        self.G[i << 1] = self.G_op(self.G[i << 1], self.G[i])
        self.G[i << 1 | 1] = self.G_op(self.G[i << 1 | 1], self.G[i])
        self.G[i] = self.G_e

    def _propagate_above(self, i):
        H = i.bit_length() - 1
        for h in range(H, 0, -1):
            self._propagate_at(i >> h)

    def _recalc_above(self, i):
        while i > 1:
            i >>= 1
            self.X[i] = self.X_op(self._eval_at(i << 1), self._eval_at(i << 1 | 1))

    def __iter__(self):
        for i in range(self.N):
            yield self.X[self.N + i]

    def __getitem__(self, i):
        i %= self.N
        return self.X[self.N + i]

    def __setitem__(self, i, x):
        i %= self.N
        i += self.N
        self._propagate_above(i)
        self.X[i] = x
        self.G[i] = self.G_e
        self._recalc_above(i)

    def prod(self, L, R):
        L += self.N
        R += self.N
        self._propagate_above(L // (L & -L))
        self._propagate_above(R // (R & -R) - 1)
        vL = self.X_e
        vR = self.X_e
        while L < R:
            if L & 1:
                vL = self.X_op(vL, self._eval_at(L))
                L += 1
            if R & 1:
                R -= 1
                vR = self.X_op(self._eval_at(R), vR)
            L >>= 1
            R >>= 1
        return self.X_op(vL, vR)

    def ranged_act(self, L, R, a):
        L += self.N
        R += self.N
        L0 = L // (L & -L)
        R0 = R // (R & -R) - 1
        self._propagate_above(L0)
        self._propagate_above(R0)
        while L < R:
            if L & 1:
                self.G[L] = self.G_op(self.G[L], a)
                L += 1
            if R & 1:
                R -= 1
                self.G[R] = self.G_op(self.G[R], a)
            L >>= 1
            R >>= 1
        self._recalc_above(L0)
        self._recalc_above(R0)

    def __repr__(self):
        for i in range(self.N, 2 * self.N):
            self._propagate_above(i)
            self.X[i] = self._eval_at(i)
        return str(self.X[self.N:])


N, Q = map(int, input().split())
S = input()
seg = LazySegTree([0] * N)
for i in range(N - 1):
    s, t = S[i], S[i + 1]
    if s != t:
        seg[i + 1] = 1
print(seg)
for _ in range(Q):
    L, R = map(int, input().split())
    s, t = S[L - 1], S[R - 1]
    k = seg.prod(L, R - 1 + 1)
    if s == t:
        k = max(k - 1, 0)
    print(solve(k))

