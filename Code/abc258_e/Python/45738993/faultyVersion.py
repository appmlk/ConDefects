import numpy as np
import sys
from functools import lru_cache
import math

sys.setrecursionlimit(int(1e7))

from collections import *
from fractions import Fraction
import heapq
import bisect
import itertools


class UnionFind:
    def __init__(self, n):
        self.n = n
        self.parents = [-1] * n

    def find(self, x):
        if self.parents[x] < 0:
            return x
        else:
            self.parents[x] = self.find(self.parents[x])
            return self.parents[x]

    def union(self, x, y):
        x = self.find(x)
        y = self.find(y)

        if x == y:
            return

        if self.parents[x] > self.parents[y]:
            x, y = y, x

        self.parents[x] += self.parents[y]
        self.parents[y] = x

    def size(self, x):
        return -self.parents[self.find(x)]

    def same(self, x, y):
        return self.find(x) == self.find(y)

    def members(self, x):  # 多用すると重い
        root = self.find(x)
        return [i for i in range(self.n) if self.find(i) == root]

    def roots(self):
        return [i for i, x in enumerate(self.parents) if x < 0]

    def group_count(self):
        return len(self.roots())

    def all_group_members(self):
        group_members = defaultdict(list)
        for member in range(self.n):
            group_members[self.find(member)].append(member)
        return group_members

    def __str__(self):
        return "\n".join(f"{r}: {m}" for r, m in self.all_group_members().items())


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


# RMQのとき
# def op(x, y):
#    return x if x < y else y
# seg = segtree([10**9] * N, op, 10**9)  # Vの要素とEの値は同じにする #10**9 -> INF
# seg.prod(l, r) # op(a[l],...a[r-1])を返す


class BIT:
    def __init__(self, n):
        self.n = len(n) if isinstance(n, list) else n
        self.size = 1 << (self.n - 1).bit_length()
        if isinstance(n, list):  # nは1-indexedなリスト
            a = [0]
            for p in n:
                a.append(p + a[-1])
            a += [a[-1]] * (self.size - self.n)
            self.d = [a[p] - a[p - (p & -p)] for p in range(self.size + 1)]
        else:  # nは大きさ
            self.d = [0] * (self.size + 1)

    def __repr__(self):
        p = self.size
        res = []
        while p > 0:
            res2 = []
            for r in range(p, self.size + 1, p * 2):
                l = r - (r & -r) + 1
                res2.append(f"[{l}, {r}]:{self.d[r]}")
            res.append(" ".join(res2))
            p >>= 1
        res.append(f"{[self.sum(p + 1) - self.sum(p) for p in range(self.size)]}")
        return "\n".join(res)

    def add(self, p, x):  # O(log(n)), 点pにxを加算
        assert p > 0
        while p <= self.size:
            self.d[p] += x
            p += p & -p

    def get(self, p, default=None):  # O(log(n))
        assert p > 0
        return (
            self.sum(p) - self.sum(p - 1)
            if 1 <= p <= self.n or default is None
            else default
        )

    def sum(self, p):  # O(log(n)), 閉区間[1, p]の累積和
        assert p >= 0
        res = 0
        while p > 0:
            res += self.d[p]
            p -= p & -p
        return res

    def lower_bound(self, x):  # O(log(n)), x <= 閉区間[1, p]の累積和 となる最小のp
        if x <= 0:
            return 0
        p, r = 0, self.size
        while r > 0:
            if p + r <= self.n and self.d[p + r] < x:
                x -= self.d[p + r]
                p += r
            r >>= 1
        return p + 1


class MultiSet:
    # n: サイズ、compress: 座圧対象list-likeを指定(nは無効)
    # multi: マルチセットか通常のOrderedSetか
    def __init__(self, n=0, *, compress=[], multi=True):
        self.multi = multi
        self.inv_compress = (
            sorted(set(compress)) if len(compress) > 0 else [i for i in range(n)]
        )
        self.compress = {k: v for v, k in enumerate(self.inv_compress)}
        self.counter_all = 0
        self.counter = [0] * len(self.inv_compress)
        self.bit = BIT(len(self.inv_compress))

    def add(self, x, n=1):  # O(log n)
        if not self.multi and n != 1:
            raise KeyError(n)
        x = self.compress[x]
        count = self.counter[x]
        if count == 0 or self.multi:  # multiなら複数カウントできる
            self.bit.add(x + 1, n)
            self.counter_all += n
            self.counter[x] += n

    def remove(self, x, n=1):  # O(log n)
        if not self.multi and n != 1:
            raise KeyError(n)
        x = self.compress[x]
        count = self.bit.get(x + 1)
        if count < n:
            raise KeyError(x)
        self.bit.add(x + 1, -n)
        self.counter_all -= n
        self.counter[x] -= n

    def __repr__(self):
        return f'MultiSet {{{(", ".join(map(str, list(self))))}}}'

    def __len__(self):  # oprator len: O(1)
        return self.counter_all

    def count(self, x):  # O(1)
        return self.counter[self.compress[x]] if x in self.compress else 0

    def __getitem__(self, i):  # operator []: O(log n)
        if i < 0:
            i += len(self)
        x = self.bit.lower_bound(i + 1)
        if x > self.bit.n:
            raise IndexError("list index out of range")
        return self.inv_compress[x - 1]

    def __contains__(self, x):  # operator in: O(1)
        return self.count(x) > 0

    def bisect_left(self, x):  # O(log n)
        return self.bit.sum(bisect.bisect_left(self.inv_compress, x))

    def bisect_right(self, x):  # O(log n)
        return self.bit.sum(bisect.bisect_right(self.inv_compress, x))


# 宣言方法
# MultiSet(compress=X,multi=False)
# MultiSet(N+1,multi=True)
# リストを渡すと座標圧縮して返してくれる


def compress(arr):
    (*XS,) = set(arr)
    XS.sort()
    return {cmp_e: cmp_i for cmp_i, cmp_e in enumerate(XS)}


def ctov(c):
    return ord(c) - ord("a")


def CTOV(c):
    return ord(c) - ord("A")


def make_divisors(n):
    lower_divisors, upper_divisors = [], []
    i = 1
    while i * i <= n:
        if n % i == 0:
            lower_divisors.append(i)
            if i != n // i:
                upper_divisors.append(n // i)
        i += 1
    return lower_divisors + upper_divisors[::-1]


dxdy1 = ((0, 1), (0, -1), (1, 0), (-1, 0))
dxdy2 = ((0, 1), (0, -1), (1, 0), (-1, 0), (1, 1), (-1, -1), (1, -1), (-1, 1))
dxdy3 = ((0, 1), (1, 0))
dxdy4 = ((1, 1), (1, -1), (-1, 1), (-1, -1))
INF = float("inf")
MOD = 998244353
mod = 998244353
# memo : len([a,b,...,z])==26

# https://atcoder.jp/contests/abc167/submissions/40815296
N, Q, X = map(int, input().split())
W = list(map(int, input().split()))
dp = [[-1] * N for _ in range(60)]  # dp[i][j]: j番目の要素から2^i回遷移したときの到達地点
S = sum(W)
C = [X // S] * N
nokori = X - S * (X // S)
acc = [0]
for w in W:
    acc.append(acc[-1] + w)
for w in W:
    acc.append(acc[-1] + w)
for i in range(N):
    point = bisect.bisect_left(acc, acc[i] + nokori)
    C[i] += point - i
    # print(i, point, point - i)
    # print(acc, acc[i] + nokori)
for j in range(N):
    dp[0][j] = (j + C[j]) % N
for i in range(1, 60):
    for j in range(N):
        dp[i][j] = dp[i - 1][dp[i - 1][j]]

for _ in range(Q):
    K = int(input())
    K -= 1
    i = 0
    now = 0
    while K > 0:
        if (K & 1) == 1:
            now = dp[i][now]
        i += 1
        K >>= 1
    print(C[now])