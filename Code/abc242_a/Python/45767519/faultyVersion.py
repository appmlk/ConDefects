from collections import Counter, defaultdict, deque
from bisect import bisect_left, bisect_right
from heapq import heapify, heappush, heappop


def solve():
    A, B, C, X = map(int, input().split())

    if X >= 1 and X <= A:
        res = 1/1
    elif X > A and X <= C:
        N = B - A
        res = C / N
    else:
        res = 0/1

    print(f"{res:.7f}")


solve()
