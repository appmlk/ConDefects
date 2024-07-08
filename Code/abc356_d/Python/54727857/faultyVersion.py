import sys
import time
import collections
import itertools
import bisect
import copy
import math
import heapq
import random
from collections import deque, Counter, defaultdict
from itertools import accumulate, combinations, permutations, product
from functools import lru_cache
from heapq import heapify, heappush, heappop
from bisect import bisect_right, bisect_left
from copy import deepcopy

sys.setrecursionlimit((1 << 31) - 1)
#sys.set_int_max_str_digits(0)
input = lambda: sys.stdin.readline().rstrip()
INF = float('inf')
MOD = 998244353
DIR = [(1, 0), (-1, 0), (0, 1), (0, -1)]
DX = [1, 0, -1, 0, 1, 1, -1, -1]
DY = [0, 1, 0, -1, 1, -1, 1, -1]

def bisect_left_reverse(a, x):
    if a[0] <= x:
        return 0
    if x < a[-1]:
        return len(a)
    ok = len(a) - 1
    ng = 0
    while (abs(ok - ng) > 1):
        mid = (ok + ng) // 2
        if a[mid] <= x:
            ok = mid
        else:
            ng = mid
    return ok

def bisect_right_reverse(a, x):
    if a[0] < x:
        return 0
    if x <= a[-1]:
        return len(a)
    ok = len(a) - 1
    ng = 0
    while (abs(ok - ng) > 1):
        mid = (ok + ng) // 2
        if a[mid] < x:
            ok = mid
        else:
            ng = mid
    return ok

class UnionFind():
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

    def members(self, x):
        root = self.find(x)
        return [i for i in range(self.n) if self.find(i) == root]

    def roots(self):
        return [i for i, x in enumerate(self.parents) if x < 0]

    def group_count(self):
        return len(self.roots())

    def all_group_members(self):
        group_members = collections.defaultdict(list)
        for member in range(self.n):
            group_members[self.find(member)].append(member)
        return group_members

class SegTree:
    ''' 点代入/区間総和
        操作 	segfunc 	単位元
        最小値 	min(x, y) 	float('inf')
        最大値 	max(x, y) 	-float('inf')
        区間和 	x + y 	0
        区間積 	x * y 	1
        最大公約数 	math.gcd(x, y) 	0
        segfunc : 和の計算がデフォ、最小値・最大値などのモノイドに置換
        add : k番目の値にxを加算
        update : k番目の値をxに更新
        query : 区間[l,r)のseg_funcモノイドの結果を出力
    '''

    def __init__(self, init_val: list, segfunc=None, ide_ele: int = 0):
        n = len(init_val)
        self.ide_ele = ide_ele
        if segfunc is not None:
            self.segfunc = segfunc
        self.num = 1 << (n-1).bit_length()
        self.tree = [ide_ele]*2*self.num
        for i in range(n):
            self.tree[self.num+i] = init_val[i]
        for i in range(self.num-1, 0, -1):
            self.tree[i] = self.segfunc(self.tree[2*i], self.tree[2*i+1])

    def segfunc(self, x, y):
        return x+y

    def add(self, k, x):
        k += self.num
        self.tree[k] += x
        while k > 1:
            self.tree[k >> 1] = self.segfunc(self.tree[k], self.tree[k ^ 1])
            k >>= 1

    def update(self, k, x):
        k += self.num
        self.tree[k] = x
        while k > 1:
            self.tree[k >> 1] = self.segfunc(self.tree[k], self.tree[k ^ 1])
            k >>= 1

    def query(self, l, r):
        res = self.ide_ele
        l += self.num
        r += self.num
        while l < r:
            if l & 1:
                res = self.segfunc(res, self.tree[l])
                l += 1
            if r & 1:
                res = self.segfunc(res, self.tree[r-1])
            l >>= 1
            r >>= 1
        return res

class LazySegTree_RAQ:
    ''' 区間代入/区間総和
    seg_func : 和の計算がデフォ、最小値・最大値などのモノイドに置換
    add : 区間[l,r)の値にxを加算
    query : 区間[l,r)のseg_funcモノイドの結果を出力
    '''

    def __init__(self, init_val, segfunc=None, ide_ele: int = 0):
        n = len(init_val)
        self.ide_ele = ide_ele
        if segfunc is not None:
            self.segfunc = segfunc
        self.num = 1 << (n-1).bit_length()
        self.tree = [ide_ele]*2*self.num
        self.lazy = [0]*2*self.num
        for i in range(n):
            self.tree[self.num+i] = init_val[i]
        for i in range(self.num-1, 0, -1):
            self.tree[i] = self.segfunc(self.tree[2*i], self.tree[2*i+1])
    def segfunc(self, x, y):
        return x+y

    def gindex(self, l, r):
        l += self.num
        r += self.num
        lm = l >> (l & -l).bit_length()
        rm = r >> (r & -r).bit_length()
        while r > l:
            if l <= lm:
                yield l
            if r <= rm:
                yield r
            r >>= 1
            l >>= 1
        while l:
            yield l
            l >>= 1

    def propagates(self, *ids):
        for i in reversed(ids):
            v = self.lazy[i]
            if v == 0:
                continue
            self.lazy[i] = 0
            self.lazy[2*i] += v
            self.lazy[2*i+1] += v
            self.tree[2*i] += v
            self.tree[2*i+1] += v

    def add(self, l, r, x):
        ids = self.gindex(l, r)
        l += self.num
        r += self.num
        while l < r:
            if l & 1:
                self.lazy[l] += x
                self.tree[l] += x
                l += 1
            if r & 1:
                self.lazy[r-1] += x
                self.tree[r-1] += x
            r >>= 1
            l >>= 1
        for i in ids:
            self.tree[i] = self.segfunc(self.tree[2*i], self.tree[2*i+1]) + self.lazy[i]

    def query(self, l, r):
        self.propagates(*self.gindex(l, r))
        res = self.ide_ele
        l += self.num
        r += self.num
        while l < r:
            if l & 1:
                res = self.segfunc(res, self.tree[l])
                l += 1
            if r & 1:
                res = self.segfunc(res, self.tree[r-1])
            l >>= 1
            r >>= 1
        return res

class LazySegTree_RUQ:
    ''' 区間代入/区間総和
    seg_func : 和の計算がデフォ、最小値・最大値などのモノイドに置換
    update : 区間[l,r)の値をxに加算
    query : 区間[l,r)のseg_funcモノイドの結果を出力
    '''

    def __init__(self, init_val: list, segfunc=None, ide_ele: int = 0):
        n = len(init_val)
        self.ide_ele = ide_ele
        self.num = 1 << (n-1).bit_length()
        if segfunc is not None:
            self.segfunc = segfunc
        self.tree = [ide_ele]*2*self.num
        self.lazy = [None]*2*self.num
        for i in range(n):
            self.tree[self.num+i] = init_val[i]
        for i in range(self.num-1, 0, -1):
            self.tree[i] = self.segfunc(self.tree[2*i], self.tree[2*i+1])

    def segfunc(self, x, y):
        return x+y

    def gindex(self, l, r):
        l += self.num
        r += self.num
        lm = l >> (l & -l).bit_length()
        rm = r >> (r & -r).bit_length()
        while r > l:
            if l <= lm:
                yield l
            if r <= rm:
                yield r
            r >>= 1
            l >>= 1
        while l:
            yield l
            l >>= 1

    def propagates(self, *ids):
        for i in reversed(ids):
            v = self.lazy[i]
            if v is None:
                continue
            self.lazy[i] = None
            self.lazy[2*i] = v
            self.lazy[2*i+1] = v
            self.tree[2*i] = v
            self.tree[2*i+1] = v

    def update(self, l, r, x):
        ids = self.gindex(l, r)
        self.propagates(*self.gindex(l, r))
        l += self.num
        r += self.num
        while l < r:
            if l & 1:
                self.lazy[l] = x
                self.tree[l] = x
                l += 1
            if r & 1:
                self.lazy[r-1] = x
                self.tree[r-1] = x
            r >>= 1
            l >>= 1
        for i in ids:
            self.tree[i] = self.segfunc(self.tree[2*i], self.tree[2*i+1])

    def query(self, l, r):
        ids = self.gindex(l, r)
        self.propagates(*self.gindex(l, r))
        res = self.ide_ele
        l += self.num
        r += self.num
        while l < r:
            if l & 1:
                res = self.segfunc(res, self.tree[l])
                l += 1
            if r & 1:
                res = self.segfunc(res, self.tree[r-1])
            l >>= 1
            r >>= 1
        return res


def main():
	N,M=map(int,input().split())
	ans=0
	#for i in range(N+1):
	#	print(format(i, "010b"))
		
	n2 = bin(N)[2:]
	m2 = bin(M)[2:]
	
	print(n2)
	print(m2)
	
	for i in range(min(len(n2),len(m2))):
		i += 1
		if m2[-i] == "0": continue
		r = 2**i
		div = (N+1)//r
		rest = (N+1) - r*div
		harf = r // 2
		ans += div*harf + max(0,rest - harf)
		
	print(ans%998244353)
	return

if __name__ == '__main__':
    main()