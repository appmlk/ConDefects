"""
y 軸の小さい方から大きい方に遷移
    とりあえずy軸でソート
i -> j に遷移できるか

T_j - T_i >= Y_j - Y_i + |X_j - X_i|
であればＯＫ

T_j - Y_j >= T_i - Y_i + |X_j - X_i|

Z_i = T_i - Y_i

Z_j >= Z_i + |X_j - X_i|

dp[i] := 時刻 T_i に(X_i, Y_i) にいるときの合計の最大値

(Z, X) を持って range tree ?

区間が三角形なんだよな

45度回転でできるか？

"""

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
 
 
sys.stdin, sys.stdout = IOWrapper(sys.stdin), IOWrapper(sys.stdout)
input = lambda: sys.stdin.readline().rstrip("\r\n")

from bisect import bisect_left

class SegTree:
    def __init__(self, n, e, ope, lst=[]):
        self.N0 = 2 ** (n - 1).bit_length()
        self.e = e
        self.ope = ope
        self.data = [e] * (2 * self.N0)
        if lst:
            for i in range(n):
                self.data[self.N0 + i] = lst[i]
            for i in range(self.N0 - 1, 0, -1):
                self.data[i] = self.ope(self.data[2 * i], self.data[2 * i + 1])
    
    def build(self):
        for i in range(self.N0 - 1, 0, -1):
            self.data[i] = self.ope(self.data[2 * i], self.data[2 * i + 1])
                
    def update(self, i, x): #a_iの値をxに更新
        i += self.N0
        self.data[i] = x
        while i > 1:
            i >>= 1
            self.data[i] = self.ope(self.data[2 * i], self.data[2 * i + 1])
    
    def add(self, i, x):
        self.update(i, x + self.get(i))

    def set(self, i, x):
        self.data[self.N0 + i] = x
    
    def query(self, l, r): #区間[l, r)での演算結果
        if r <= l:
            return self.e
        lres = self.e
        rres = self.e
        l += self.N0
        r += self.N0
        while l < r:
            if l & 1:
                lres = self.ope(lres, self.data[l])
                l += 1
            if r & 1:
                r -= 1
                rres = self.ope(self.data[r], rres)
            l >>= 1
            r >>= 1
        return self.ope(lres, rres)
    
    def get(self, i): #a_iの値を返す
        return self.data[self.N0 + i]

class RangeTree:
    def __init__(self, e, ope, inflog=32):
        self.e = e
        self.ope = ope
        self.ps = []
        self.inflog = inflog
        self.inf = 1 << self.inflog
        self.mask = (self.inf) - 1

    def add_point(self, x, y):
        self.ps.append((x << self.inflog) | y)

    def _merge(self, A, B):
        ret = []
        al = len(A)
        bl = len(B)
        ap = 0
        bp = 0
        while ap < al and bp < bl:
            if A[ap] < B[bp]:
                ret.append(A[ap])
                ap += 1
            elif A[ap] == B[bp]:
                ret.append(A[ap])
                ap += 1
                bp += 1
            else:
                ret.append(B[bp])
                bp += 1
        
        if ap == al:
            ret += B[bp:]
        else:
            ret += A[ap:]
        return ret

    def build(self):
        self.ps = sorted(set(self.ps))
        self.n = len(self.ps)
        self.ys = [[] for _ in range(2 * self.n)]
        for i in range(self.n):
            self.ys[i + self.n].append(self.ps[i] & self.mask)
        for i in range(self.n - 1, -1, -1):
            self.ys[i] = self._merge(self.ys[i << 1], self.ys[(i << 1) | 1])            
        self.le = [0] * (2 * self.n + 1)
        for i in range(1, 2 * self.n + 1):
            self.le[i] = self.le[i - 1] + len(self.ys[i - 1])
        self.seg = SegTree(self.le[-1], self.e, self.ope)
    
    def _idx(self, x):
        return bisect_left(self.ps, x << self.inflog)

    def _idy(self, i, y):
        return bisect_left(self.ys[i], y) + self.le[i]

    def upd(self, x, y, w):
        i = bisect_left(self.ps, (x << self.inflog) | y)
        i += self.n
        while i > 0:
            bef = self.seg.get(self._idy(i, y))
            if w > bef:
                self.seg.update(self._idy(i, y), w)
            i >>= 1

    def add_init(self, xyw):
        plus = [0] * (self.le[-1])
        for x, y, w in xyw:
            i = bisect_left(self.ps, (x << self.inflog) | y)
            i += self.n
            while i > 0:
                plus[self._idy(i, y)] += w
                i >>= 1
        
        for i, p in enumerate(plus):
            if p != 0:
                self.seg.add(i, p)
    
    def query(self, l, d, r, u):
        L = self.e
        R = self.e
        a = self._idx(l) + self.n
        b = self._idx(r) + self.n
        while a < b:
            if a & 1:
                L = self.ope(L, self.seg.query(self._idy(a, d), self._idy(a, u)))
                a += 1
            if b & 1:
                b -= 1
                R = self.ope(self.seg.query(self._idy(b, d), self._idy(b, u)), R)
            
            a >>= 1
            b >>= 1

        return self.ope(L, R)


n = int(input())
points = [list(map(int, input().split())) for _ in range(n)]
points.sort(key=lambda x:(x[2], x[0]))
inf = 1 << 40
Z = [0]
X = [0]
A = [0]
for t, x, y, a in points:
    z = t - y
    Z.append(z + x)
    X.append(z - x)
    A.append(a)
    
Z_se = set(Z)
X_se = set(X)
dic = {z:i for i, z in enumerate(sorted(Z_se))}
Z = [dic[z] for z in Z]
dic = {x:i for i, x in enumerate(sorted(X_se))}
X = [dic[x] for x in X]
rt = RangeTree(-inf, max, 33)
for z, x in zip(Z, X):
    rt.add_point(z, x)
rt.build()
rt.upd(Z[0], X[0], 0)
ans = 0
for z, x, a in zip(Z[1:], X[1:], A[1:]):
    ma = rt.query(0, 0, z + 1, x + 1)
    ma += a
    rt.upd(z, x, ma)
    ans = max(ans, ma)
    

print(ans)