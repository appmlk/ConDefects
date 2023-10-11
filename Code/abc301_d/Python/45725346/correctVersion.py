import sys
from functools import lru_cache, cmp_to_key
from itertools import accumulate
from collections import defaultdict, Counter, deque
from math import inf, sqrt, isqrt, ceil, comb
from bisect import bisect_left, bisect_right
from sortedcontainers import SortedSet, SortedList

# mod = 998244353


def main():
    # 2023-09-19 15:39:55
    s = input()
    n = int(input())

    res = int(s.replace("?", "0"), 2)
    if res > n:
        print(-1)
        return

    for i, ch in enumerate(s):
        d = len(s) - 1 - i
        if ch == "?" and res + (1 << d) <= n:
            res += 1 << d

    print(res)


def input() -> str:
    return sys.stdin.readline().rstrip()


def read_ints():
    return map(int, input().split())


if __name__ == "__main__":
    main()
