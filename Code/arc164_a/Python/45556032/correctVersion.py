# import pypyjit;pypyjit.set_param("max_unroll_recursion=-1")
# from bisect import *
# from collections import *
# from heapq import *
# from itertools import *
# from math import *
# from datetime import *
# from decimal import *  # PyPyだと遅い
# from string import ascii_lowercase,ascii_uppercase
# import numpy as np
import sys

# sys.setrecursionlimit(10**6) # PyPyだと遅い
INF = 1 << 61
MOD = 998244353
# MOD = 10**9 + 7
File = sys.stdin


def input():
    return File.readline()[:-1]


# ///////////////////////////////////////////////////////////////////////////


for _ in range(int(input())):
    N, K = map(int, input().split())

    cnt = 0
    i = 3**40
    while N > 0 and i > 0:
        d, m = divmod(N, i)
        cnt += d
        N = m
        i //= 3
    if cnt <= K and (K - cnt) % 2 == 0:
        print("Yes")
    else:
        print("No")
