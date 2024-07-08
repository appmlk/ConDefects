#!/usr/bin/env python3

# 再起関数,Decimal以外は、pypyを推奨(メモリを多く使用する場合遅くなる)
# pypyは,numpy使用不可
# pythonの実行時間のオーダーは、10^8まで
# 最小値の最大化(最大値の最小化)は二分探索
# O(2^n)はdpが多め
# べき乗はpowを使う(modで割る系は平衡二分木が組まれているため)

from collections import Counter, deque, defaultdict, OrderedDict
from heapq import heapify, heappop, heappush
from itertools import accumulate, product, combinations, combinations_with_replacement, permutations
from bisect import bisect, bisect_left, bisect_right
from functools import lru_cache
# Setのように値をO(√n)で見つける、配列のように[0]と[-1]で最小、最大取得はO(1)
from sortedcontainers import SortedSet, SortedList, SortedDict
from decimal import Decimal, ROUND_HALF_UP, ROUND_HALF_EVEN, getcontext
# 多倍長精度を100桁にする
getcontext().prec = 100
import math
import sys
from copy import deepcopy, copy
# 分数モジュール
from fractions import Fraction
sys.setrecursionlimit(10 ** 7)
def I(): return int(input())
def MI(): return map(int, input().split())
def LI(): return list(map(int, input().split()))
def S(): return input()
def SI(): return map(str, input().split())
def LS(): return list(map(str, input().split()))
INF = 10**18
MOD = 998244353 # 素数、フェルマーの小定理、平衡二分木
dict = defaultdict(lambda:defaultdict())

n, k = MI()
a = LI()

if k <= 0:
    if sum(a) >= k:
        print("Yes")
        print(*sorted(a, reverse=True))
    else:
        print("No")
else:
    print("Yes")
    print(*sorted(a))


