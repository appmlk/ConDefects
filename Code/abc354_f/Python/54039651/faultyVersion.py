ImportType = 1
InputType = 1
ConstType = 1
if ImportType:
    import os, sys, random, threading
    from random import randint, choice, shuffle
    from copy import deepcopy
    from io import BytesIO, IOBase
    from types import GeneratorType
    from functools import lru_cache, reduce
    from bisect import bisect_left, bisect_right
    from collections import Counter, defaultdict, deque
    from itertools import accumulate, combinations, permutations
    from heapq import heapify, heappop, heappush, heappushpop
    from typing import Generic, Iterable, Iterator, TypeVar, Union, List
    from string import ascii_lowercase, ascii_uppercase, digits
    from math import ceil, comb, floor, sqrt, pi, factorial, gcd, log, log10, log2, inf
    from decimal import Decimal, getcontext
    from sys import stdin, stdout, setrecursionlimit

if InputType:
    input = lambda: sys.stdin.readline().rstrip("\r\n")
    I = lambda: input()
    II = lambda: int(input())
    MII = lambda: map(int, input().split())
    LI = lambda: list(input().split())
    LII = lambda: list(map(int, input().split()))
    GMI = lambda: map(lambda x: int(x) - 1, input().split())
    LGMI = lambda: list(map(lambda x: int(x) - 1, input().split()))

if ConstType:
    RD = random.randint(10 ** 9, 2 * 10 ** 9)
    MOD = 998244353
    Y = "Yes"
    N = "No"

def abc354_f():
    for _ in range(II()):
        n = II()
        a = LII()
        pre = [0] * n
        suf = [0] * n
        g = []
        for i, x in enumerate(a):
            idx = bisect_left(g, x)
            if idx < len(g):
                g[idx] = x
            else:
                g.append(x)
            pre[i] = idx + 1
        g = []
        for i in range(n - 1, -1, -1):
            v = -a[i]
            idx = bisect_left(g, v)
            if idx < len(g):
                g[idx] = v
            else:
                g.append(v)
            suf[i] = idx + 1
        ans = []
        for i in range(n):
            if pre[i] + suf[i] - 1 == len(g):
                ans.append(i + 1)
        print(*ans)

def cf_486E():
    n = II()
    a = LII()
    pre = [0] * n
    suf = [0] * n
    ans = ['1'] * n
    g = []
    for i, x in enumerate(a):
        idx = bisect_left(g, x)
        if idx < len(g):
            g[idx] = x
        else:
            g.append(x)
        pre[i] = idx + 1
    g = []
    for i in range(n - 1, -1, -1):
        v = -a[i]
        idx = bisect_left(g, v)
        if idx < len(g):
            g[idx] = v
        else:
            g.append(v)
        suf[i] = idx + 1
    cnt = [0] * (n + 1)
    for i in range(n):
        if pre[i] + suf[i] - 1 == len(g):
            ans[i] = '3'
            cnt[pre[i]] += 1
    for i in range(n):
        if ans[i] == '3' and cnt[pre[i]] > 1:
            ans[i] = '2'
    print(len(ans))
    print(''.join(ans))
    return


def main():
    # cf_486E()
    abc354_f()
    return


if __name__ == '__main__':
    main()
