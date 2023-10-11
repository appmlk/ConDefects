import sys
sys.setrecursionlimit(10 ** 5 + 10000)
# sys.setrecursionlimit(10**6)
input = sys.stdin.readline    ####
def int1(x): return int(x) - 1
def II(): return int(input())
def MI(): return map(int, input().split())
def MI1(): return map(int1, input().split())
def LI(): return list(map(int, input().split()))
def LI1(): return list(map(int1, input().split()))
def LIS(): return list(map(int, SI()))
def LA(f): return list(map(f, input().split()))
def LLI(H): return [LI() for _ in range(H)]     # H:列数
def SI(): return input().strip('\n')
### 数字文字交じりクエリを文字列のリストにする '1 19 G' -> ['1', '19', 'G']
# input()を含まず、受け取ったLLSのクエリの文字列に対し実行する
# l = ''.join(Strings).split(' ')
def MS(): return input().split()
def LS(): return list(input().strip('\n'))
def LLS(H): return [LS() for _ in range(H)]
# 迷路の前後左右
#for y, x in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
def gen_matrix(h, w, init): return [[init] * w for _ in range(h)]
INF = float('inf')
# from bisect import bisect_left, bisect_right
# from heapq import heapify, heappop, heappush
import decimal
from decimal import Decimal
import math
from math import ceil, floor, log2, log, sqrt, gcd
def lcm(x, y): return (x * y) // gcd(x, y)
# At = list(zip(*A)) 転置行列
from itertools import combinations as comb, combinations_with_replacement as comb_w, product, permutations, accumulate
from collections import deque, defaultdict
from pprint import pprint
# import numpy as np    # cumsum
from functools import reduce, lru_cache     # decorator: 関数をメモ化再起してくれる. max_size=128
import operator
from copy import deepcopy
MOD = 10**9+7
MOD2 = 998244353
def y(): print('Yes'); exit()
def n(): print('No'); exit()
from bisect import bisect_left, bisect_right, insort
from typing import Generic, Iterable, Iterator, TypeVar, Union, List
T = TypeVar('T')
 
# 累積和 ans=list(itertools.accumulate(L))
# 順列 ans=list(itertools.permutation(L))
# 直積 ans=list(itertools.product(L,M))
# 重複なし組み合わせ ans=list(itertools.combinations(L,2))
# 重複あり組み合わせ ans=list(itertools.combinations_with_replacement(L,2))
# nCr ans=math.comb(n,r)

def solve():
    ans = INF

    n,m,t = MI()
    A = LI()
    L = LLI(m)

    d = defaultdict(int)
    for x, y in L:
        d[x] = y

    for i, a in enumerate(A):
        room = i+1          # 1-index
        if d[room]:
            t += d[room]
        t -= a
        if t > 0:
            continue
        else:
            print("No")
            exit()
    
    print("Yes")




if __name__ == '__main__':
    solve()

