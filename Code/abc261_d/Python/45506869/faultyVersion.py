# ref: https://qiita.com/Kota-Y/items/396ab3c57830dad65cfb
import sys
import re
from math import ceil, floor, sqrt, pi, factorial, gcd
from copy import deepcopy
from collections import Counter, deque, defaultdict
from heapq import heapify, heappop, heappush
from itertools import accumulate, product, combinations, combinations_with_replacement, permutations
from bisect import bisect, bisect_left, bisect_right
from functools import reduce
from decimal import Decimal, getcontext


def input(): return sys.stdin.readline().strip()
def i_input(): return int(input())
def i_map(): return map(int, input().split())
def i_list(): return list(i_map())
def i_row(N): return [i_input() for _ in range(N)]
def i_row_list(N): return [i_list() for _ in range(N)]
def s_input(): return input()
def s_map(): return input().split()
def s_list(): return list(s_map())
def s_row(N): return [s_input() for _ in range(N)]
def s_row_str(N): return [s_list() for _ in range(N)]
def s_row_list(N): return [list(s_input()) for _ in range(N)]
def lcm(a, b): return a * b // gcd(a, b)
def rotate90(S): return list(zip(*S[::-1]))
def rotate180(S): return list(zip(*list(zip(*S[::-1]))[::-1]))
def rotate270(S): return list(zip(*S))[::-1]


sys.setrecursionlimit(10 ** 6)
INF = float('inf')
MOD = 10 ** 9 + 7
DIRECTION = [
    (1, 0),
    (1, 1),
    (0, 1),
    (-1, 1),
    (-1, 0),
    (-1, -1),
    (0, -1),
    (1, -1),
]


def main():
    N, M = i_map()
    X = i_list()
    CY = i_row_list(M)

    Y = [0 for _ in range(N+1)]
    for c, y in CY:
        Y[c] = y
    # print(Y)

    dp = [[-MOD for _ in range(N+1)] for _ in range(N+1)]

    dp[0][0] = 0

    for i in range(N):
        for j in range(N):
            # 0のとき
            dp[i+1][0] = max(dp[i][j], dp[i+1][0])

            # 1のとき
            dp[i+1][j+1] = max(dp[i][j]+X[i]+Y[j+1], dp[i+1][j+1])

    # for d in dp:
    #     print(d)

    print(max(dp[-1]))


if __name__ == '__main__':
    main()
