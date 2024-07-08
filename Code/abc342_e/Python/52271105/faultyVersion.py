#!/usr/bin/env python3
from bisect import bisect_left, bisect_right
from collections import Counter, defaultdict, deque
from heapq import heappop, heappush
from math import gcd
from sys import setrecursionlimit


dpos4 = ((1, 0), (0, 1), (-1, 0), (0, -1))
dpos8 = ((0, -1), (1, -1), (1, 0), (1, 1), (0, 1), (-1, 1), (-1, 0), (-1, -1))
mod1 = 10**9 + 7
mod2 = 998244353
inf = 1 << 62


def main():
    N, M = map(int, input().split())
    edges = [[] for _ in range(N)]
    for _ in range(M):
        l, d, k, c, a, b = map(int, input().split())
        a -= 1
        b -= 1
        edges[b].append((a, l, d, k, c))

    que = []
    que.append((-1 << 60, N - 1))
    dist = [-1 << 60] * N
    dist[-1] = 1 << 60

    while que:
        fd, fr = heappop(que)
        fd *= -1
        if dist[fr] != fd:
            continue
        for to, l, d, k, c in edges[fr]:
            td = fd - c
            if td < l:
                continue
            r = l + (k - 1) * d
            if r <= td:
                td = r
            else:
                td = l + (td - l + d - 1) // d * d
            if dist[to] >= td:
                continue
            dist[to] = td
            heappush(que, (-td, to))
    for i in range(N - 1):
        if dist[i] == -1 << 60:
            print("Unreachable")
        else:
            print(dist[i])


if __name__ == "__main__":
    main()
