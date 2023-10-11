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


H, W = map(int, input().split())
C = [list(input()) for _ in range(H)]

dp = [[0] * W for _ in range(H)]
dp[0][0] = 1
for i in range(H):
    for j in range(W):
        if j < W - 1 and C[i][j + 1] == ".":
            dp[i][j + 1] = max(dp[i][j + 1], dp[i][j] + 1)
        if i < H - 1 and C[i + 1][j] == ".":
            dp[i + 1][j] = max(dp[i + 1][j], dp[i][j] + 1)

ans = 0
for i in range(H):
    for j in range(W):
        ans = max(ans, dp[i][j])

print(ans)
