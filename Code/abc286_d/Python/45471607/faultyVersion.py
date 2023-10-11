import sys
import math
from collections import defaultdict
from collections import Counter
from functools import lru_cache


sys.setrecursionlimit(10 ** 8)
MOD1 = 10**9 + 7


def main():
    n, x = map(int, sys.stdin.readline().split())
    a, b = [], []
    for _ in range(n):
        ai, bi = map(int, sys.stdin.readline().split())
        a.append(ai)
        b.append(bi)

    # @lru_cache(maxsize=None)
    # def rec(index, s):
    #     if s == x:
    #         return True
    #
    #     if index == n:
    #         return False
    #
    #     for i in range(b[index] + 1):
    #         if rec(index + 1, s + i * a[index]):
    #             return True
    #     return False
    #
    # if rec(0, 0):
    #     print("Yes")
    # else:
    #     print("No")
    dp = [[False for _ in range(x + 1)] for _ in range(n + 1)]
    dp[0][0] = True
    for i in range(n):
        for j in range(x + 1):
            for k in range(b[i] + 1):
                if j >= k * a[i]:
                    dp[i + 1][j] = dp[i][j] or dp[i][j - k * a[i]]
    if dp[n][x]:
        print("Yes")
    else:
        print("No")


if __name__ == '__main__':
    main()
