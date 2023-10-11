# import pypyjit;pypyjit.set_param("max_unroll_recursion=-1")
# from bisect import *
# from collections import *
# from heapq import *
# from itertools import *
# from sortedcontainers import *
# from math import gcd, lcm
# from datetime import *
# from decimal import *  # PyPyだと遅い
from string import ascii_lowercase, ascii_uppercase

# import numpy as np
# from atcoder.dsu import *
# from atcoder.segtree import *
# from sortedcontainers import *
# from random import *
import sys
import os

is_test = os.getenv("ATCODER", 0)
# sys.setrecursionlimit(10**6) # PyPyは呪文を付ける
INF = 1 << 61
MOD = 998244353
# MOD = 10**9 + 7
File = sys.stdin


def input():
    return File.readline()[:-1]


# ///////////////////////////////////////////////////////////////////////////


S = input()
if len(S) != 8:
    print("No")
else:
    l, r = S[0], S[-1]
    n = S[1:-1]
    print(n)
    if l in ascii_uppercase and r in ascii_uppercase and n.isdecimal():
        if 100000 <= int(n) <= 999999:
            print("Yes")
            exit()
    print("No")
