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
    N, K = i_map()
    A = i_list()

    A.sort()
    # rest = N
    # takahashi = 0
    # aoki = 0

    # i = 0
    # while True:
    #     idx = bisect_right(A, rest)

    #     if idx == 0:
    #         n = A[0]
    #     else:
    #         n = A[idx-1]

    #     if rest < n:
    #         break

    #     if i % 2 == 0:
    #         takahashi += n
    #     else:
    #         aoki += n

    #     rest -= n

    #     # print(f"n: {n}, takahashi: {takahashi}, aoki: {aoki}, rest: {rest}")

    #     i += 1
    # print(takahashi)

    dp = [0 for _ in range(N+1)]

    for i in range(1, N+1):
        for a in A:
            if i - a >= 0:
                dp[i] = a + (i - a) - dp[i-a]

    # print(dp)
    print(dp[-1])


if __name__ == '__main__':
    main()
