from collections import *
import sys
import heapq
import bisect
import itertools
from functools import lru_cache
import math
import copy

sys.setrecursionlimit(int(1e7))

dxdy1 = ((0, 1), (0, -1), (1, 0), (-1, 0))
dxdy2 = ((0, 1), (0, -1), (1, 0), (-1, 0), (1, 1), (-1, -1), (1, -1), (-1, 1))
dxdy3 = ((0, 1), (1, 0))
dxdy4 = ((1, 1), (1, -1), (-1, 1), (-1, -1))
INF = float("inf")
MOD = 998244353
mod = 998244353
MOD2 = 10**9 + 7
mod2 = 10**9 + 7
# memo : len([a,b,...,z])==26

input = lambda: sys.stdin.readline().rstrip()
mi = lambda: map(int, input().split())
li = lambda: list(mi())

N, M = mi()
adj = [[] for _ in range(N)]
for _ in range(M):
    u, v = mi()
    u -= 1
    v -= 1
    adj[u].append(v)
    adj[v].append(u)

ans1 = []
q = deque()
visited = [0] * N
q.append((-1, 0))
while q:
    pre, node = q.pop()
    if visited[node] == 1:
        continue
    if pre >= 0:
        ans1.append((pre + 1, node + 1))
    visited[node] = 1
    for nxt in adj[node]:
        if visited[nxt] == 1:
            continue
        q.append((node, nxt))
for z, w in ans1:
    print(z, w)

ans2 = []
visited = [0] * N
q.append(0)
while q:
    node = q.popleft()
    visited[node] = 1
    for nxt in adj[node]:
        if visited[nxt] == 1:
            continue
        visited[nxt] = 1
        q.append(nxt)
        ans2.append((node + 1, nxt + 1))
for z, w in ans2:
    print(z, w)
