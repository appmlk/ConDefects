# import pypyjit;pypyjit.set_param("max_unroll_recursion=-1")
# from bisect import *
# from collections import *
# from heapq import *
# from itertools import *
# from sortedcontainers import *
# from math import gcd
# from datetime import *
# from decimal import *  # PyPyだと遅い
# from string import ascii_lowercase,ascii_uppercase
# import numpy as np
# from atcoder.dsu import *
# from atcoder.segtree import *
# from random import *
import sys

# sys.setrecursionlimit(10**6) # PyPyは呪文を付ける
INF = 1 << 61
MOD = 998244353
# MOD = 10**9 + 7
File = sys.stdin


def input():
    return File.readline().replace("\n", "")


# ///////////////////////////////////////////////////////////////////////////


for _ in range(int(input())):
    N, K = map(int, input().split())

    cnt = 0
    for i in range(20, -1, -1):
        d, m = divmod(N, 3**i)
        cnt += d
        N = m
    if K >= cnt and (K - cnt) % 2 == 0:
        print("Yes")
    else:
        print("No")
