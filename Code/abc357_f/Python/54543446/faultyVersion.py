# セグ木のノードに長さを持たせる必要あり
class LazySegTree:
    def _update(self, k):
        # _d[k]を更新
        self._d[k] = self._op(self._d[2 * k], self._d[2 * k + 1])
    
    def _all_apply(self, k, f):
        # _d[k] <- f(_d[k])
        self._d[k] = self._mapping(f, self._d[k])
        # kが葉でないとき, fを_lz[k]に貯める
        if k < self._size:
            self._lz[k] = self._composition(f, self._lz[k])
    
    def _push(self, k):
        # _lz[k]に貯まった遅延情報を子に流す
        self._all_apply(2 * k, self._lz[k])
        self._all_apply(2 * k + 1, self._lz[k])
        self._lz[k] = self._id
    
    def __init__(self, op, e, mapping, composition, id, v):
        """
        op: 二項演算
        e: 単位元
        mapping(f, x): 遅延させた情報の処理 (f(x)を返す関数)
        composition(g, f): 遅延させた情報が衝突したときの処理 (g(f(x))を返す関数)
        id: f(x) = x を満たすf (lzをこれで初期化する)
        v: 初期の配列
        """
        self._op = op
        self._e = e
        self._mapping = mapping
        self._composition = composition
        self._id = id
        self._n = len(v)
        self._log = (self._n - 1).bit_length()
        self._size = 1 << self._log
        self._d = [e] * (2 * self._size)
        self._lz = [self._id] * self._size
        for i in range(self._n):
            self._d[self._size + i] = v[i]
        for i in range(self._size - 1, 0, -1):
            self._update(i)
    
    def set(self, p, x):
        """
        v[p]にxをセットする
        """
        p += self._size
        for i in range(self._log, 0, -1):
            self._push(p >> i)
        self._d[p] = x
        for i in range(1, self._log + 1):
            self._update(p >> i)
    
    def get(self, p):
        """
        v[p]を取得する
        """
        p += self._size
        for i in range(self._log, 0, -1):
            self._push(p >> i)
        return self._d[p]
    
    def prod(self, l, r):
        """
        [l, r)におけるopの結果を取得
        """
        if l == r:
            return self._e
        
        l += self._size
        r += self._size
        
        for i in range(self._log, 0, -1):
            if ((l >> i) << i) != l:
                self._push(l >> i)
            if ((r >> i) << i) != r:
                self._push(r >> i)
        
        sml = self._e
        smr = self._e
        while l < r:
            if l & 1:
                sml = self._op(sml, self._d[l])
                l += 1
            if r & 1:
                r -= 1
                smr = self._op(self._d[r], smr)
            l >>= 1
            r >>= 1
        
        return self._op(sml, smr)
    
    def all_prod(self):
        return self._d[1]
    
    def apply_point(self, p, f):
        """
        v[p] <- f(v[p])
        """
        p += self._size
        for i in range(self._log, 0, -1):
            self._push(p >> i)
        self._d[p] = self._mapping(f, self._d[p])
        for i in range(1, self._log + 1):
            self._update(p >> i)
    
    def apply(self, l, r, f):
        """
        i = l,..,r-1について v[i] <- f[v[i]]
        """
        if l == r:
            return
        
        l += self._size
        r += self._size
        for i in range(self._log, 0, -1):
            if ((l >> i) << i) != l:
                self._push(l >> i)
            if ((r >> i) << i) != r:
                self._push((r - 1) >> i)
        
        l2 = l
        r2 = r
        while l < r:
            if l & 1:
                self._all_apply(l, f)
                l += 1
            if r & 1:
                r -= 1
                self._all_apply(r, f)
            l >>= 1
            r >>= 1
        l = l2
        r = r2
        
        for i in range(1, self._log + 1):
            if ((l >> i) << i) != l:
                self._update(l >> i)
            if ((r >> i) << i) != r:
                self._update((r - 1) >> i)
    
    def max_right(self, l, g):
        """
        g(op(v[l],..,v[r - 1]) = True となる最大のrを返す
        bool g
        """
        if l == self._n:
            return self._n
        l += self._size
        for i in range(self._log, 0, -1):
            self._push(l >> i)
        
        sm = self._e
        first = True
        while first or (l & -l) != l:
            first = False
            while l % 2 == 0:
                l >>= 1
            if not g(self._op(sm, self._d[l])):
                while l < self._size:
                    self._push(l)
                    l *= 2
                    if g(self._op(sm, self._d[l])):
                        sm = self._op(sm, self._d[l])
                        l += 1
                return l - self._size
            sm = self._op(sm, self._d[l])
            l += 1
    
        return self._n
    
    def min_left(self, r, g):
        """
        g(op(v[l],..,v[r - 1]) = True となる最小のlを返す
        bool g
        """
        if r == 0:
            return 0

        r += self._size
        for i in range(self._log, 0, -1):
            self._push((r - 1) >> i)

        sm = self._e
        first = True
        while first or (r & -r) != r:
            first = False
            r -= 1
            while r > 1 and r % 2:
                r >>= 1
            if not g(self._op(self._d[r], sm)):
                while r < self._size:
                    self._push(r)
                    r = 2 * r + 1
                    if g(self._op(self._d[r], sm)):
                        sm = self._op(self._d[r], sm)
                        r -= 1
                return r + 1 - self._size
            sm = self._op(self._d[r], sm)

        return 0

# op
# 1.加法
# def op(x, y):
#     return (x[0] + y[0], x[1] + y[1])
# e = (0, 0)
# 2.min
# def op(x, y):
#     return (min(x[0], y[0]), x[1] + y[1])
# e = (10 ** 18, 0)
# 3.max
# def op(x, y):
#     return (max(x[0], y[0]), x[1] + y[1])
# e = (-10 ** 18, 0)

# mapping, composition(opはすべて加算の場合)
# 1.x <- x+c (加算)
# def op(x, y):
#     return (x[0] + y[0], x[1] + y[1])
# def mapping(f, x):
#     return (x[0] + f * x[1], x[1])
# def composition(g, f):
#     return (g + f)
# e = (0, 0)
# id = 0

# 2.x <- a * x (乗算)
# def op(x, y):
#     return (x[0] + y[0], x[1] + y[1])
# def mapping(f, x):
#     return (x[0] * f, x[1])
# def composition(g, f):
#     return g * f
# e = (0, 0)
# id = 1

# 3.x <- ax+b
# def op(x, y):
#     return ((x[0]+y[0]), x[1]+y[1])
# def mapping(f, x):
#     a, b = f
#     return (a*x[0]+b*x[1], x[1])
# def composition(g, f):
#     a, b = f
#     c, d = g
#     return (a*c, (b*c+d))
# e = (0, 0)
# id = (1, 0)

# 4.x <- y (変更)
# def op(x, y):
#     return ((x[0]+y[0]), x[1]+y[1])
# def mapping(f, x):
#     if f == None:
#         return x
#     return (f, x[1])
# def composition(g, f):
#     if g == id: return f
#     return g
# e = (0, 0)
# id = None

mod = 998244353
def op(x, y):
    a1, b1, ab1, l1 = x
    a2, b2, ab2, l2 = y
    return ((a1 + a2) % mod, (b1 + b2) % mod, (ab1 + ab2) % mod, l1 + l2)
def mapping(f, x):
    a, b, ab, l = x
    y1, y2 = f
    aa = (a + l * y1) % mod
    bb = (b + l * y2) % mod
    return (aa, bb, (ab + y1*b + y2*a + y1*y2) % mod, l)
def composition(g, f):
    x1, y1 = g
    x2, y2 = f
    return ((x1 + x2) % mod, (y1 + y2) % mod)
e = (0, 0, 0, 0)
id = (0, 0)

N, Q = map(int, input().split())
A = list(map(int, input().split()))
B = list(map(int, input().split()))
query = [list(map(int, input().split())) for _ in range(Q)]
init = []
for i in range(N):
    init.append((A[i], B[i], (A[i] * B[i]) % mod, 1))

seg = LazySegTree(op, e, mapping, composition, id, init)
for i in range(Q):
    if query[i][0] == 1:
        _, l, r, x = query[i]
        l -= 1
        seg.apply(l, r, (x, 0))
    elif query[i][0] == 2:
        _, l, r, x = query[i]
        l -= 1
        seg.apply(l, r, (0, x))
    else:
        _, l, r = query[i]
        l -= 1
        print(seg.prod(l, r)[2])