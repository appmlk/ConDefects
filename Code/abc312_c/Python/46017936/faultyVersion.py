#! /usr/bin/env python3

import numpy as np
import sys
from bisect import bisect_left, bisect_right
input = sys.stdin.readline
sys.setrecursionlimit(10**6)


N, M = map(int, input().rstrip().split())
A = list(map(int, input().rstrip().split()))
B = list(map(int, input().rstrip().split()))
A.sort()
B.sort()

ans = 10**9

for i, a in enumerate(A):
    idx = bisect_left(B, a)
    if i+1 >= M - idx:
        ans = min(ans, a)
        break
for i, b in enumerate(B):
    idx = bisect_right(A, b+1)
    if idx >= M - i - 1:
        ans = min(ans, b+1)
        break

print(ans)