from collections import *
import heapq
import bisect

INF = float("inf")
MOD = 998244353
mod = 998244353

T = int(input())
for _ in range(T):
    a, s = map(int, input().split())
    if s - 2 * a >= 0 and (s - 2 * a) & a == 0:
        print("Yes")
    else:
        print("No")
