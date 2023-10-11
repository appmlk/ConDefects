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
import os

# sys.setrecursionlimit(10**6) # PyPyだと遅い
INF = 10**18
MOD = 998244353
# MOD = 10**9 + 7
isTest = os.path.exists("input.txt")
File = open("input.txt", "r") if isTest else sys.stdin


def input():
    return File.readline()[:-1]


# ///////////////////////////////////////////////////////////////////////////


for _ in range(int(input())):
    N = int(input())
    str_N = str(N)
    len_N = len(str_N)
    s = set()
    for i in range(1, len_N // 2 + 1):
        if len_N % i == 0:
            periodic = int(str_N[:i] * (len_N // i))
            # print(periodic, 1)
            if periodic > N:
                if int(str_N[:i]) % 10 == 0:
                    periodic = 0
                else:
                    periodic = int(str(int(str_N[:i]) - 1) * (len_N // i))
            # print(periodic, 2)
            if periodic == 0:
                continue
            s.add(periodic)
    s.add(int("9" * (len_N - 1)))
    print(max(s))
