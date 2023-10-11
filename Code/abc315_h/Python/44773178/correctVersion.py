from __pypy__.builders import StringBuilder
import sys
from os import read as os_read, write as os_write
from atexit import register as atexist_register
from typing import Generic, Iterator, List, Tuple, Dict, Iterable, Sequence, Callable, Union, Optional, TypeVar
T = TypeVar('T')
Pair = Tuple[int, int]
Graph = List[List[int]]
Poly = List[int]
Vector = List[int]
Matrix = List[List[int]]
Func10 = Callable[[int], None]
Func20 = Callable[[int, int], None]
Func11 = Callable[[int], int]
Func21 = Callable[[int, int], int]
Func31 = Callable[[int, int, int], int]

class Fastio:
    ibuf = bytes()
    pil = pir = 0
    sb = StringBuilder()
    def load(self):
        self.ibuf = self.ibuf[self.pil:]
        self.ibuf += os_read(0, 131072)
        self.pil = 0; self.pir = len(self.ibuf)
    def flush_atexit(self): os_write(1, self.sb.build().encode())
    def flush(self):
        os_write(1, self.sb.build().encode())
        self.sb = StringBuilder()
    def fastin(self):
        if self.pir - self.pil < 64: self.load()
        minus = x = 0
        while self.ibuf[self.pil] < 45: self.pil += 1
        if self.ibuf[self.pil] == 45: minus = 1; self.pil += 1
        while self.ibuf[self.pil] >= 48:
            x = x * 10 + (self.ibuf[self.pil] & 15)
            self.pil += 1
        if minus: return -x
        return x
    def fastin_string(self):
        if self.pir - self.pil < 64: self.load()
        while self.ibuf[self.pil] <= 32: self.pil += 1
        res = bytearray()
        while self.ibuf[self.pil] > 32:
            if self.pir - self.pil < 64: self.load()
            res.append(self.ibuf[self.pil])
            self.pil += 1
        return res
    def fastout(self, x): self.sb.append(str(x))
    def fastoutln(self, x): self.sb.append(str(x)); self.sb.append('\n')
fastio = Fastio()
rd = fastio.fastin; rds = fastio.fastin_string; wt = fastio.fastout; wtn = fastio.fastoutln; flush = fastio.flush
atexist_register(fastio.flush_atexit)
sys.stdin = None; sys.stdout = None
def rdl(n): return [rd() for _ in range(n)]
def wtnl(l): wtn(' '.join(map(str, l)))
def wtn_yes(): wtn("Yes")
def wtn_no(): wtn("No")
def print(*arg): wtnl(arg)

def modinv(a: int, m: int) -> int:
    '''return x s.t. x == a^(-1) (mod m)'''
    b = m; u = 1; v = 0
    while b:
        t = a // b
        a, b = b, a - t * b
        u, v = v, u - t * v
    u %= m
    return u

MOD = 998244353
_IMAG = 911660635
_IIMAG = 86583718
_rate2 = (0, 911660635, 509520358, 369330050, 332049552, 983190778, 123842337, 238493703, 975955924, 603855026, 856644456, 131300601, 842657263, 730768835, 942482514, 806263778, 151565301, 510815449, 503497456, 743006876, 741047443, 56250497, 867605899, 0)
_rate3 = (0, 372528824, 337190230, 454590761, 816400692, 578227951, 180142363, 83780245, 6597683, 70046822, 623238099, 183021267, 402682409, 631680428, 344509872, 689220186, 365017329, 774342554, 729444058, 102986190, 128751033, 395565204, 0)
_irate3 = (0, 509520358, 929031873, 170256584, 839780419, 282974284, 395914482, 444904435, 72135471, 638914820, 66769500, 771127074, 985925487, 262319669, 262341272, 625870173, 768022760, 859816005, 914661783, 430819711, 272774365, 530924681, 0)

class NTT:
    @staticmethod
    def _fft(a: Vector) -> None:
        n = len(a)
        h = (n - 1).bit_length()
        le = 0
        for le in range(0, h - 1, 2):
            p = 1 << (h - le - 2)
            rot = 1
            for s in range(1 << le):
                rot2 = rot * rot % MOD
                rot3 = rot2 * rot % MOD
                offset = s << (h - le)
                for i in range(p):
                    a0 = a[i + offset]
                    a1 = a[i + offset + p] * rot
                    a2 = a[i + offset + p * 2] * rot2
                    a3 = a[i + offset + p * 3] * rot3
                    a1na3imag = (a1 - a3) % MOD * _IMAG
                    a[i + offset] = (a0 + a2 + a1 + a3) % MOD
                    a[i + offset + p] = (a0 + a2 - a1 - a3) % MOD
                    a[i + offset + p * 2] = (a0 - a2 + a1na3imag) % MOD
                    a[i + offset + p * 3] = (a0 - a2 - a1na3imag) % MOD
                rot = rot * _rate3[(~s & -~s).bit_length()] % MOD
        if h - le & 1:
            rot = 1
            for s in range(1 << (h - 1)):
                offset = s << 1
                l = a[offset]
                r = a[offset + 1] * rot
                a[offset] = (l + r) % MOD
                a[offset + 1] = (l - r) % MOD
                rot = rot * _rate2[(~s & -~s).bit_length()] % MOD

    @staticmethod
    def _ifft(a: Vector) -> None:
        n = len(a)
        h = (n - 1).bit_length()
        le = h
        for le in range(h, 1, -2):
            p = 1 << (h - le)
            irot = 1
            for s in range(1 << (le - 2)):
                irot2 = irot * irot % MOD
                irot3 = irot2 * irot % MOD
                offset = s << (h - le + 2)
                for i in range(p):
                    a0 = a[i + offset]
                    a1 = a[i + offset + p]
                    a2 = a[i + offset + p * 2]
                    a3 = a[i + offset + p * 3]
                    a2na3iimag = (a2 - a3) * _IIMAG % MOD
                    a[i + offset] = (a0 + a1 + a2 + a3) % MOD
                    a[i + offset + p] = (a0 - a1 + a2na3iimag) * irot % MOD
                    a[i + offset + p * 2] = (a0 + a1 - a2 - a3) * irot2 % MOD
                    a[i + offset + p * 3] = (a0 - a1 - a2na3iimag) * irot3 % MOD
                irot = irot * _irate3[(~s & -~s).bit_length()] % MOD
        if le & 1:
            p = 1 << (h - 1)
            for i in range(p):
                l = a[i]
                r = a[i + p]
                a[i] = l + r if l + r < MOD else l + r - MOD
                a[i + p] = l - r if l - r >= 0 else l - r + MOD

    @classmethod
    def ntt(cls, a: Vector) -> None:
        if len(a) <= 1: return
        cls._fft(a)

    @classmethod
    def intt(cls, a:Vector) -> None:
        if len(a) <= 1: return
        cls._ifft(a)
        iv = modinv(len(a), MOD)
        for i, x in enumerate(a): a[i] = x * iv % MOD

    @classmethod
    def multiply(cls, s: Vector, t: Vector) -> Vector:
        n, m = len(s), len(t)
        l = n + m - 1
        if min(n, m) <= 60:
            a = [0] * l
            for i, x in enumerate(s):
                for j, y in enumerate(t):
                    a[i + j] = (a[i + j] + x * y) % MOD
            return a
        z = 1 << (l - 1).bit_length()
        a = s + [0] * (z - n)
        b = t + [0] * (z - m)
        cls._fft(a)
        cls._fft(b)
        for i, x in enumerate(b): a[i] = a[i] * x % MOD
        cls._ifft(a)
        a[l:] = []
        iz = modinv(z, MOD)
        return [x * iz % MOD for x in a]

    @classmethod
    def pow2(cls, s: Vector) -> Vector:
        n = len(s)
        l = (n << 1) - 1
        if n <= 60:
            a = [0] * l
            for i, x in enumerate(s):
                for j, y in enumerate(s):
                    a[i + j] = (a[i + j] + x * y) % MOD
            return a
        z = 1 << (l - 1).bit_length()
        a = s + [0] * (z - n)
        cls._fft(a)
        for i, x in enumerate(a): a[i] = x * x % MOD
        cls._ifft(a)
        a[l:] = []
        iz = modinv(z, MOD)
        return [x * iz % MOD for x in a]

    @classmethod
    def ntt_doubling(cls, a: Vector) -> None:
        M = len(a)
        b = a[:]
        cls.intt(b)
        r = 1
        zeta = pow(3, (MOD - 1) // (M << 1), MOD)
        for i, x in enumerate(b):
            b[i] = x * r % MOD
            r = r * zeta % MOD
        cls.ntt(b)
        a += b

# https://nyaannyaan.github.io/library/ntt/relaxed-convolution.hpp
class RelaxedConvolution:
    def __init__(self, n: int) -> None:
        self.n = n
        self.q = 0
        self.a = [0] * (n + 1)
        self.b = [0] * (n + 1)
        self.c = [0] * (n + 1)
        self.aa: List[Poly] = []
        self.bb: List[Poly] = []

    def get(self, x: int, y: int) -> int:
        '''
        x: a[q]
        y: b[q]
        return: c[q]
        '''
        q, n = self.q, self.n
        a, b, c = self.a, self.b, self.c
        aa, bb = self.aa, self.bb
        assert(q <= n)
        a[q] = x; b[q] = y
        c[q] += a[q] * b[0] + (b[q] * a[0] if q else 0)
        c[q] %= MOD

        def precalc(lg: int) -> None:
            if len(aa) <= lg:
                aa[len(aa):] = [0] * (lg + 1 - len(aa))
                bb[len(bb):] = [0] * (lg + 1 - len(bb))
            if aa[lg]: return
            d = 1 << lg
            s = a[:d << 1]
            t = b[:d << 1]
            NTT.ntt(s); NTT.ntt(t)
            aa[lg] = s; bb[lg] = t

        self.q += 1; q += 1
        if q > n:
            return c[q - 1]

        f: Poly = []
        g: Poly = []
        for lg in range(q.bit_length()):
            d = 1 << lg
            if q & ((d << 1) - 1) != d:
                continue
            if q == d:
                f = a[:d] + [0] * d
                g = b[:d] + [0] * d
                NTT.ntt(f); NTT.ntt(g)
                f = [f[i] * y % MOD for i, y in enumerate(g)]
                NTT.intt(f)
                for i in range(q, min(q + d, n + 1)):
                    c[i] += f[d + i - q]
                    c[i] %= MOD
            else:
                precalc(lg)
                f = [a[q - d + i] for i in range(d)] + [0] * d
                g = [b[q - d + i] for i in range(d)] + [0] * d
                NTT.ntt(f); NTT.ntt(g)
                s = aa[lg]; t = bb[lg]
                for i in range(d << 1):
                    f[i] = (f[i] * t[i] + g[i] * s[i]) % MOD
                NTT.intt(f)
                for i in range(q, min(q + d, n + 1)):
                    c[i] += f[d + i - q]
                    c[i] %= MOD
        return c[q - 1]

N = rd()
A = rdl(N)
F = [1]
G = [0]
conv = RelaxedConvolution(N)
for i in range(N):
    f = F[i]
    G.append(G[-1] + conv.get(f, f))
    F.append(G[-1] * A[i] % MOD)
wtnl(F[1:])
