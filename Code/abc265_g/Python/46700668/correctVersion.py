class segtreelazy3:
    # 要素数、乗せる演算、単位元、作用、作用素の演算、恒等写像になる作用素
    def __init__(self, N, op, e, act, comp, identity, A = []):
        self.N = N
        self.op = op
        self.e = e
        self.act = act
        self.comp = comp
        self.identity = identity
        self.dat = [self.e] * (2 * self.N)
        self.lazy = [self.identity] * (2 * self.N)
        for i in range(len(A)):
            self.dat[i + self.N] = A[i]
        for i in range(self.N - 1, 0, -1):
            self.dat[i] = self.op(self.dat[i << 1], self.dat[i << 1 | 1])
    
    def _getidx1(self, i):
        i >>= 1
        while i:
            yield i
            i >>= 1
    
    def _getidx2(self, l, r):
        l0 = l // (l & -l)
        r0 = r // (r & -r)
        while l < r:
            if l < l0:
                yield l
            if r < r0:
                yield r
            l >>= 1
            r >>= 1
        while l:
            yield l
            l >>= 1
    
    def eval(self, idx):
        for i in reversed(idx):
            x = self.lazy[i]
            if x == self.identity: continue
            if i < self.N:
                self.lazy[i << 1] = self.comp(self.lazy[i << 1], x)
                self.dat[i << 1] = self.act(self.dat[i << 1], x)
                self.lazy[i << 1 | 1] = self.comp(self.lazy[i << 1 | 1], x)
                self.dat[i << 1 | 1] = self.act(self.dat[i << 1 | 1], x)
            self.lazy[i] = self.identity

    def apply(self, l, r, x):
        l += self.N
        r += self.N
        *idx, = self._getidx2(l, r)
        self.eval(idx)
        while l < r:
            if l & 1:
                self.lazy[l] = self.comp(self.lazy[l], x)
                self.dat[l] = self.act(self.dat[l], x)
                l += 1
            if r & 1:
                r -= 1
                self.lazy[r] = self.comp(self.lazy[r], x)
                self.dat[r] = self.act(self.dat[r], x)
            l >>= 1
            r >>= 1
        for i in idx:
            self.dat[i] = self.op(self.dat[i << 1], self.dat[i << 1 | 1])
    
    def prod(self, l, r):
        l += self.N
        r += self.N
        *idx, = self._getidx2(l, r)
        self.eval(idx)
        lres = self.e
        rres = self.e
        while l < r:
            if l & 1:
                lres = self.op(lres, self.dat[l])
                l += 1
            if r & 1:
                r -= 1
                rres = self.op(self.dat[r], rres)
            l >>= 1
            r >>= 1
        return self.op(lres, rres)
    
    def set(self, i, a):
        i += self.N
        *idx, = self._getidx1(i)
        self.eval(idx)
        self.dat[i] = a
        self.lazy[i] = self.identity
        for i in idx:
            self.dat[i] = self.op(self.dat[i << 1], self.dat[i << 1 | 1])

N, Q = map(int, input().split())
# 0, 1, 2, (0, 1), (0, 2), (1, 0), (1, 2), (2, 0), (2, 1)
A = [
    (1, 0, 0, 0, 0, 0, 0, 0, 0),
    (0, 1, 0, 0, 0, 0, 0, 0, 0),
    (0, 0, 1, 0, 0, 0, 0, 0, 0)
]
B = list(map(lambda x: A[int(x)], input().split()))
idx = [-1, 3, 4, 5, -1, 6, 7, 8, -1]

def op(A, B):
    return (
        A[0] + B[0],
        A[1] + B[1],
        A[2] + B[2],
        A[3] + B[3] + A[0] * B[1],
        A[4] + B[4] + A[0] * B[2],
        A[5] + B[5] + A[1] * B[0],
        A[6] + B[6] + A[1] * B[2],
        A[7] + B[7] + A[2] * B[0],
        A[8] + B[8] + A[2] * B[1]
    )

def act(A, F):
    res = [0] * 9
    for i in range(3): res[F[i]] += A[i]
    for i, a, b in ((3, 0, 1), (4, 0, 2), (5, 1, 0), (6, 1, 2), (7, 2, 0), (8, 2, 1)):
        j = idx[F[a] * 3 + F[b]]
        if j != -1: res[j] += A[i]
    return tuple(res)

def comp(F, G):
    return (G[F[0]], G[F[1]], G[F[2]])

e = (0, 0, 0, 0, 0, 0, 0, 0, 0)
identity = (0, 1, 2)

tree = segtreelazy3(N, op, e, act, comp, identity, B)
for _ in range(Q):
    k, *query = map(int, input().split())
    if k == 1:
        l, r = query
        l -= 1
        res = tree.prod(l, r)
        print(res[5] + res[7] + res[8])
    else:
        l, r, s, t, u = query
        l -= 1
        tree.apply(l, r, (s, t, u))