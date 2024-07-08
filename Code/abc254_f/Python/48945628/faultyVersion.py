from collections import *
import sys
import heapq
import bisect
import itertools
from functools import lru_cache
from types import GeneratorType
from fractions import Fraction
import math
import copy

sys.setrecursionlimit(int(1e7))
# @lru_cache(maxsize=None) # CPython特化
# @bootstrap # PyPy特化(こっちのほうが速い) yield dfs(), yield Noneを忘れずに


def bootstrap(f, stack=[]):  # yield
    def wrappedfunc(*args, **kwargs):
        if stack:
            return f(*args, **kwargs)
        else:
            to = f(*args, **kwargs)
            while True:
                if type(to) is GeneratorType:
                    stack.append(to)
                    to = next(to)
                else:
                    stack.pop()
                    if not stack:
                        break
                    to = stack[-1].send(to)
            return to

    return wrappedfunc


dxdy1 = ((0, 1), (0, -1), (1, 0), (-1, 0))  # 上下左右
dxdy2 = ((0, 1), (0, -1), (1, 0), (-1, 0), (1, 1), (-1, -1), (1, -1), (-1, 1))  # 8方向すべて
dxdy3 = ((0, 1), (1, 0))  # 右 or 下
dxdy4 = ((1, 1), (1, -1), (-1, 1), (-1, -1))  # 斜め
INF = float("inf")
_INF = 1 << 60
MOD = 998244353
mod = 998244353
MOD2 = 10**9 + 7
mod2 = 10**9 + 7
# memo : len([a,b,...,z])==26
# memo : 2^20 >= 10^6
# 小数の計算を避ける : x/y -> (x*big)//y  ex:big=10**9
# @:小さい文字, ~:大きい文字,None: 空の文字列
# ユークリッドの互除法：gcd(x,y)=gcd(x,y-x)

input = lambda: sys.stdin.readline().rstrip()
mi = lambda: map(int, input().split())
li = lambda: list(mi())
ii = lambda: int(input())
py = lambda: print("Yes")
pn = lambda: print("No")
pf = lambda: print("First")
ps = lambda: print("Second")


# https://raw.githubusercontent.com/shakayami/ACL-for-python/master/segtree.py
class segtree:
    n = 1
    size = 1
    log = 2
    d = [0]
    op = None
    e = 10**15

    def __init__(self, V, OP, E):
        self.n = len(V)
        self.op = OP
        self.e = E
        self.log = (self.n - 1).bit_length()
        self.size = 1 << self.log
        self.d = [E for i in range(2 * self.size)]
        for i in range(self.n):
            self.d[self.size + i] = V[i]
        for i in range(self.size - 1, 0, -1):
            self.update(i)

    def set(self, p, x):
        assert 0 <= p and p < self.n
        p += self.size
        self.d[p] = x
        for i in range(1, self.log + 1):
            self.update(p >> i)

    def get(self, p):
        assert 0 <= p and p < self.n
        return self.d[p + self.size]

    def prod(self, l, r):
        assert 0 <= l and l <= r and r <= self.n
        sml = self.e
        smr = self.e
        l += self.size
        r += self.size
        while l < r:
            if l & 1:
                sml = self.op(sml, self.d[l])
                l += 1
            if r & 1:
                smr = self.op(self.d[r - 1], smr)
                r -= 1
            l >>= 1
            r >>= 1
        return self.op(sml, smr)

    def all_prod(self):
        return self.d[1]

    def max_right(self, l, f):
        assert 0 <= l and l <= self.n
        assert f(self.e)
        if l == self.n:
            return self.n
        l += self.size
        sm = self.e
        while 1:
            while l % 2 == 0:
                l >>= 1
            if not (f(self.op(sm, self.d[l]))):
                while l < self.size:
                    l = 2 * l
                    if f(self.op(sm, self.d[l])):
                        sm = self.op(sm, self.d[l])
                        l += 1
                return l - self.size
            sm = self.op(sm, self.d[l])
            l += 1
            if (l & -l) == l:
                break
        return self.n

    def min_left(self, r, f):
        assert 0 <= r and r <= self.n
        assert f(self.e)
        if r == 0:
            return 0
        r += self.size
        sm = self.e
        while 1:
            r -= 1
            while r > 1 and (r % 2):
                r >>= 1
            if not (f(self.op(self.d[r], sm))):
                while r < self.size:
                    r = 2 * r + 1
                    if f(self.op(self.d[r], sm)):
                        sm = self.op(self.d[r], sm)
                        r -= 1
                return r + 1 - self.size
            sm = self.op(self.d[r], sm)
            if (r & -r) == r:
                break
        return 0

    def update(self, k):
        self.d[k] = self.op(self.d[2 * k], self.d[2 * k + 1])

    def __str__(self):
        return str([self.get(i) for i in range(self.n)])

    def get_list(self):
        return [self.get(i) for i in range(self.n)]  # オリジナルで追加


N, Q = mi()
A = li()
B = li()
AA = []
BB = []

for i in range(N - 1):
    AA.append(A[i + 1] - A[i])
    BB.append(B[i + 1] - B[i])
seg1 = segtree(AA, math.gcd, 0)  # Vの要素とEの値は同じにする #10**9 -> INF
seg2 = segtree(BB, math.gcd, 0)
for _ in range(Q):
    h1, h2, w1, w2 = mi()
    h1 -= 1
    h2 -= 1
    w1 -= 1
    w2 -= 1
    print(math.gcd(A[h1] + B[w1]), seg1.prod(h1, h2), seg2.prod(w1, w2))
