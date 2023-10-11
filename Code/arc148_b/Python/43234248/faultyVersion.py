# import pypyjit;pypyjit.set_param("max_unroll_recursion=-1")
# from bisect import *
# from collections import *
# from heapq import *
# from itertools import *
# from math import *
# from datetime import *
# from decimal import*
# from string import ascii_lowercase,ascii_uppercase
# import numpy as np
import sys
import os

# sys.setrecursionlimit(10**6)
INF = 10**18
MOD = 998244353
# MOD = 10**9 + 7
File = open("input.txt", "r") if os.path.exists("input.txt") else sys.stdin


def input():
    return File.readline()[:-1]


# ///////////////////////////////////////////////////////////////////////////


N = int(input())
S = input()
s = set()
if "p" not in S or "d" not in S:
    print(S)
    exit()
idxP = S.index("p")
dp = str.maketrans({"d": "p", "p": "d"})

for i in range(idxP, N + 1):
    s.add(S[:idxP] + S[idxP:i][::-1].translate(dp) + S[i:])

print(min(s))
