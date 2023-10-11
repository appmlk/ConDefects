# import pypyjit;pypyjit.set_param("max_unroll_recursion=-1")
# from bisect import *
# from collections import *
# from heapq import *
from itertools import *

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


N, M = map(int, input().split())
S = [input() for _ in range(N)]
T = set([input() for _ in range(M)])

total_length = sum([len(i) for i in S])

under = [
    ["_" * j for j in i]
    for i in product(list(range(1, 16 - total_length + 1)), repeat=N - 1)
    if sum(i) <= 16 - total_length
]

for i in permutations(S):
    for j in under:
        l = []
        for k in range(N):
            l.append(i[k])
            if k < N - 1:
                l.append(j[k])
        st = "".join((l))
        if st not in T and 3 <= len(st) <= 16:
            print(st)
            exit()

print(-1)
