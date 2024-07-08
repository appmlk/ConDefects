P = 998244353
p, g, ig = 998244353, 3, 332748118
W = [pow(g, (p - 1) >> i, p) for i in range(24)]
iW = [pow(ig, (p - 1) >> i, p) for i in range(24)]

def convolve(a, b):
    def fft(f):
        for l in range(k, 0, -1):
            d = 1 << l - 1
            U = [1]
            for i in range(d):
                U.append(U[-1] * W[l] % p)

            for i in range(1 << k - l):
                for j in range(d):
                    s = i * 2 * d + j
                    t = s + d
                    f[s], f[t] = (f[s] + f[t]) % p, U[j] * (f[s] - f[t]) % p

    def ifft(f):
        for l in range(1, k + 1):
            d = 1 << l - 1
            U = [1]
            for i in range(d):
                U.append(U[-1] * iW[l] % p)

            for i in range(1 << k - l):
                for j in range(d):
                    s = i * 2 * d + j
                    t = s + d
                    f[s], f[t] = (f[s] + f[t] * U[j]) % p, (f[s] - f[t] * U[j]) % p
    
    n0 = len(a) + len(b) - 1
    if len(a) < 50 or len(b) < 50:
        ret = [0] * n0
        if len(a) > len(b): a, b = b, a
        for i, aa in enumerate(a):
            for j, bb in enumerate(b):
                ret[i+j] = (ret[i+j] + aa * bb) % P
        return ret
    
    k = (n0).bit_length()
    n = 1 << k
    a = a + [0] * (n - len(a))
    b = b + [0] * (n - len(b))
    fft(a), fft(b)
    for i in range(n):
        a[i] = a[i] * b[i] % p
    ifft(a)
    invn = pow(n, p - 2, p)
    for i in range(n0):
        a[i] = a[i] * invn % p
    del a[n0:]
    return a

def Tonelli_Shanks(n, p = P):
    if pow(n, (p-1) // 2, p) == -1:
        return -1
    
    if p % 4 == 3:
        a = pow(n, (p+1) // 4, p)
        return min(a, p - a)
    
    q = p - 1
    s = 0
    while q % 2 == 0:
        q //= 2
        s += 1
    
    for z in range(1, p):
        if pow(z, (p-1) // 2, p) != 1:
            break
    m = s
    c = pow(z, q, p)
    t = pow(n, q, p)
    r = pow(n, (q+1) // 2, p)
    while 1:
        if t == 0:
            return 0
        if t == 1:
            return min(r, p - r)
        for i in range(1, m):
            if pow(t, 1 << i, p) == 1:
                break
        if m - i <= 0:
            return -1
        b = pow(c, 1 << m-i-1, p)
        m = i
        c = b ** 2 % p
        t = t * b ** 2 % p
        r = r * b % p

class fps():
    def __init__(self, a, m = 10**6):
        if type(a) == int:
            self.len = 1
            self.f = [a]
        elif a:
            self.len = len(a)
            self.f = a
        else:
            self.len = 1
            self.f = [0]
    
    def copy(self):
        return fps(self.f[:])
    
    def __neg__(self):
        l = [0] * self.len
        for i, a in enumerate(self.f):
            l[i] = P - a if a else 0
        return self.__class__(l)
        
    def __add__(self, other):
        if type(other) == int:
            return self + self.__class__([other])
        if self.len > other.len:
            l = self.f[:]
            for i, a in enumerate(other.f):
                l[i] += a
                if l[i] >= P:
                    l[i] -= P
        else:
            l = other.f[:]
            for i, a in enumerate(self.f):
                l[i] += a
                if l[i] >= P:
                    l[i] -= P
        return self.__class__(l)
    
    def __radd__(self, other):
        return self + other
    
    def __sub__(self, other):
        if type(other) == int:
            return self - self.__class__([other])
        l = self.f[:] + [0] * (other.len - self.len)
        for i, a in enumerate(other.f):
            l[i] -= a
            if l[i] < 0:
                l[i] += P
        return self.__class__(l)
    def __rsub__(self, other):
        return self.__class__([other]) - self
    
    def __mul__(self, other):
        if type(other) == int:
            l = self.f[:]
            for i in range(self.len):
                l[i] = l[i] * other % P
            return self.__class__(l)
        else:
            return self.__class__(convolve(self.f, other.f))

    def __rmul__(self, other):
        l = self.f[:]
        for i in range(self.len):
            l[i] = l[i] * other % P
        return self.__class__(l)
    
    def inv(self, deg = -1):
        f = self.f[:]
        assert f[0]
        n = self.len
        if deg < 0: deg = n
        ret = __class__([pow(self.f[0], P - 2, P)])
        i = 1
        while i < deg:
            ret = (ret * (2 - ret * self[:i*2]))[:i*2]
            i <<= 1
        return ret[:deg]
    
    def __floordiv__(self, other):
        d1 = self.len
        d2 = other.len
        if d1 < d2:
            return fps([0])
        return self.__class__((self.__class__(self.f[::-1]) * self.__class__(other.f[::-1]).inv(d1)).f[:d1-d2+1][::-1])
    
    def __mod__(self, other):
        if self.len < other.len:
            return self.copy()
        return (self - other * (self // other))[:other.len-1]
    
    def __truediv__(self, other, deg = -1):
        if type(other) == int:
            iv = pow(other, P - 2, P)
            l = self.f[:]
            for i in range(self.len):
                l[i] = l[i] * iv % P
            return self.__class__(l)
        else:
            if deg < 0: deg = max(self.len, other.len)
            return (self * other.inv(deg))[:deg]
    
    def __rtruediv__(self, other, deg = -1):
        if type(other) == int:
            return fps(other) / self
        else:
            if deg < 0: deg = max(self.len, other.len)
            return (other * self.inv(deg))[:deg]
    
    def sqrt(self):
        if self.f[0] == 0:
            for k, a in enumerate(self.f):
                if a: break
            else:
                return self.__class__([0] * self.len)
            if k & 1: return None
            sq = self.__class__(self.f[k:] + [0] * (k//2)).sqrt()
            if not sq: return None
            return fps([0] * (k//2) + sq.f)
        ts = Tonelli_Shanks(self.f[0])
        if ts < 0: return None
        deg = self.len
        a = self.__class__([ts])
        i = 1
        while i < deg:
            a += self[:i*2].__truediv__(a)
            a /= 2
            i <<= 1
        return a
    
    def f2e(self):
        f = self.f[:]
        for i, a in enumerate(f):
            f[i] = a * fainv[i] % P
        return self.__class__(f)
    
    def e2f(self):
        f = self.f[:]
        for i, a in enumerate(f):
            f[i] = a * fa[i] % P
        return self.__class__(f)
    
    def differentiate(self):
        f = self.f[:]
        for i, a in enumerate(f):
            f[i] = a * i % P
        f = f[1:] + [0]
        return self.__class__(f)
    
    def integrate(self):
        f = self.f[:]
        for i, a in enumerate(f):
            f[i] = a * fainv[i+1] % P * fa[i] % P
        f = [0] + f[:-1]
        return self.__class__(f)
    
    def log(self, deg = -1):
        return (self.differentiate().__truediv__(self, deg)).integrate()
    
    def exp(self, deg = -1):
        assert self.f[0] == 0
        if deg < 0: deg = self.len
        a = self.__class__([1])
        i = 1
        while i < deg:
            a = (a * (self[:i*2] + 1 - a.log(i * 2)))[:i*2]
            i <<= 1
        return a[:deg]
    
    def __pow__(self, n, deg = -1):
        if deg < 0: deg = self.len
        if self.f[0] == 0:
            assert n >= 0
            for i, a in enumerate(self.f):
                if a:
                    if i * n >= deg: return self.__class__([0] * deg)
                    return self.__class__([0] * (i * n) + pow(self.__class__(self.f[i:]), n, deg - i * n).f)
            else:
                return self.__class__([0] * deg)
        if self.f[0] != 1:
            a = self.f[0]
            return pow(self / a, n, deg) * pow(a, n, P)
        return (self.log(deg) * n).exp(deg)
    
    def taylor_shift(self, c):
        deg = self.len
        L = []
        a = 1
        for i in range(deg):
            L.append(a * fainv[i] % P)
            a = a * c % P
        L = L[::-1]
        return (self.e2f() * self.__class__(L))[deg-1:].f2e()
    
    def composite(self, other, deg = -1):
        assert other.f[0] == 0
        if other.len == 1: return self[:1]
        if deg < 0: deg = (self.len - 1) * (other.len - 1) + 1
        n = other.len
        k = int((n / n.bit_length()) ** 0.5) + 1
        p = other[:k]
        q = other[k:]
        def calc():
            f = self.f + [0] * ((-self.len) % 4)
            pp = p
            while 1:
                pp2 = (pp * pp)[:deg]
                pp3 = (pp2 * pp)[:deg]
                g = []
                for i in range(0, len(f), 4):
                    g.append(f[i] + (f[i+1] * pp)[:deg] + (f[i+2] * pp2)[:deg] + (f[i+3] * pp3)[:deg])
                if len(g) <= 1:
                    break
                f = g + [0] * ((-len(g)) % 4)
                pp = (pp3 * pp)[:deg]
            return g[0]
        
        if p.iszero():
            ff = self[:]
            re = ff[0]
            qq = 1
            for i in range(k, deg, k):
                ff = ff.differentiate()
                qq = (qq * q)[:deg-i]
                re += (ff[0] * fainv[i//k] * qq).shift(i)
            return re
        
        fp = calc()
        re = fp[:]
        pd = p.differentiate()
        z = pd.leadingzeroes()
        pdi = pd[z:].inv(deg)
        qq = 1
        for i in range(k, deg, k):
            fp = (pdi[:deg-i+z] * fp[:deg-i+1+z].differentiate())[:deg-i+z][z:]
            qq = (qq * q)[:deg-i]
            re += ((fp * qq)[:deg-i] * fainv[i//k]).shift(i)
        return re
    
    def at(self, v):
        f = self.f
        s = 0
        for a in f[::-1]:
            s = (s * v + a) % P
        return s
    def __lshift__(self, k):
        return self.shift(k)
    def __rshift__(self, k):
        return self.__class__(self.f[k:])
    def shift(self, k):
        return self.__class__([0] * k + self.f)
    
    def iszero(self):
        return sum(self.f) == 0
    
    def leadingzeroes(self):
        for i, a in enumerate(self.f):
            if a: return i
        return self.len
    
    def __getitem__(self, s):
        return self.__class__(self.f[s])
    
    def to_frac(self, a):
        if 0 <= a <= 10000: return a
        if -10000 <= a - P < 0: return a - P
        for i in range(1, 10001):
            if i and a * i % P <= 10000:
                return str(a * i % P) + "/" + str(i)
            if i and -a * i % P <= 10000:
                return str(-(-a * i % P)) + "/" + str(i)
        return a
    
    def __str__(self):
        l = []
        for a in self.f:
            l.append(str(self.to_frac(a)))
        return "【" + ", ".join(l) + "】"
        # return ", ".join(l)
    
    def __len__(self):
        return self.len

DEBUG = 0
from collections import deque
N = int(input())
X = [[] for i in range(N)]
for i in range(N-1):
    x, y = map(int, input().split())
    x, y = x-1, y-1
    X[x].append(y)
    X[y].append(x)

PP = [-1] * N
Q = deque([0])
R = []
while Q:
    i = deque.popleft(Q)
    R.append(i)
    for a in X[i]:
        if a != PP[i]:
            PP[a] = i
            X[a].remove(i)
            deque.append(Q, a)

if DEBUG:
    print("X =", X) # 子リスト
    print("PP =", PP) # 親
    print("R =", R) # トポロジカルソート

# N の 2 倍ぐらい必要になることがあるので注意
nn = 6060
fa = [1] * (nn+1)
fainv = [1] * (nn+1)
for i in range(nn):
    fa[i+1] = fa[i] * (i+1) % P
fainv[-1] = pow(fa[-1], P-2, P)
for i in range(nn)[::-1]:
    fainv[i] = fainv[i+1] * (i+1) % P

def calc(L):
    # print("L =", L)
    while len(L) > 1:
        k = (len(L) - 1) // 2
        L[k] *= L.pop()
    # print("L =", L)
    x = L[0].e2f()
    f = x.f
    y = fps([(i + 1) * a % P for i, a in enumerate(f)])
    ret = fps([sum(x.f), sum(y.f)])
    if DEBUG:
        print("ret =", ret)
    return ret

Y = [None for _ in range(N)]
for i in R[::-1]:
    if DEBUG:
        print("i, X =", i, X[i])
    L = [fps([1])]
    for j in X[i]:
        L.append(Y[j])
    Y[i] = calc(L)
print(Y[0].f[0] % P)
if DEBUG:
    print("Y =")
    for y in Y:
        print(y)