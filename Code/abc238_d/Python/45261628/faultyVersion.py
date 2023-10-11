from collections import *
import heapq
import bisect

INF = float("inf")
MOD = 998244353
mod = 998244353

T = int(input())
for _ in range(T):
    a, s = map(int, input().split())
    if 2 * a <= s:
        print("Yes")
    else:
        print("No")
