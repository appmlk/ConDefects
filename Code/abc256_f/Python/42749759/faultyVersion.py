#!/usr/bin/env python3
import sys
from bisect import bisect_left, bisect_right, insort_left, insort_right  # type: ignore
from collections import Counter, defaultdict, deque  # type: ignore
from math import gcd, sqrt  # type: ignore
from heapq import heapify, heappop, heappush, heappushpop, heapreplace, merge  # type: ignore
from itertools import accumulate, combinations, permutations, product  # type: ignore

def LI(): return list(map(int, sys.stdin.buffer.readline().split()))
def I(): return int(sys.stdin.buffer.readline())
def LS(): return sys.stdin.buffer.readline().rstrip().decode("utf-8").split()
def S(): return sys.stdin.buffer.readline().rstrip().decode("utf-8")
def IR(n): return [I() for _ in range(n)]
def LIR(n): return [LI() for _ in range(n)]
def SR(n): return [S() for _ in range(n)]
def LSR(n): return [LS() for _ in range(n)]
def SRL(n): return [list(S()) for _ in range(n)]

#####segfunc#####
def segfunc(x, y):
    return (x+y)%MOD
#################

#####ide_ele#####
ide_ele = 0
#################

class SegTree:
    """
    init(init_val, ide_ele): 配列init_valで初期化 O(N)
    update(k, x): k番目の値をxに更新 O(logN)
    query(l, r): 区間[l, r)をsegfuncしたものを返す O(logN)
    """
    def __init__(self, init_val, segfunc, ide_ele):
        """
        init_val: 配列の初期値
        segfunc: 区間にしたい操作
        ide_ele: 単位元
        n: 要素数
        num: n以上の最小の2のべき乗
        tree: セグメント木(1-index)
        """
        n = len(init_val)
        self.segfunc = segfunc
        self.ide_ele = ide_ele
        self.num = 1 << (n - 1).bit_length()
        self.tree = [ide_ele] * 2 * self.num
        # 配列の値を葉にセット
        for i in range(n):
            self.tree[self.num + i] = init_val[i]
        # 構築していく
        for i in range(self.num - 1, 0, -1):
            self.tree[i] = self.segfunc(self.tree[2 * i], self.tree[2 * i + 1])

    def update(self, k, x):
        """
        k番目の値をxに更新
        k: index(0-index)
        x: update value
        """
        k += self.num
        self.tree[k] = x
        while k > 1:
            self.tree[k >> 1] = self.segfunc(self.tree[k], self.tree[k ^ 1])
            k >>= 1

    def query(self, l, r):
        """
        [l, r)のsegfuncしたものを得る
        l: index(0-index)
        r: index(0-index)
        """
        res = self.ide_ele

        l += self.num
        r += self.num
        while l < r:
            if l & 1:
                res = self.segfunc(res, self.tree[l])
                l += 1
            if r & 1:
                res = self.segfunc(res, self.tree[r - 1])
            l >>= 1
            r >>= 1
        return res

N,Q = LI()
A = [0]+LI()
MOD = 998244353
aj = SegTree(A,segfunc,ide_ele)
jaj = SegTree([i*v for i,v in enumerate(A)],segfunc,ide_ele)
jjaj = SegTree([i*i*v for i,v in enumerate(A)],segfunc,ide_ele)
for _ in range(Q):
    q = LI()
    assert jaj.query(0,1)==0
    assert jjaj.query(0,1)==0
    if q[0]==1:
        x,v = q[1:]
        aj.update(x,v)
        jaj.update(x,x*v) 
        jjaj.update(x,x*x*v) 
    else:
        x = q[1]
        tmp = jjaj.query(0,x+1)-(2*x+3)*jaj.query(0,x+1)+(x+1)*(x+2)*aj.query(0,x+1)
        print((tmp//2)%MOD)
