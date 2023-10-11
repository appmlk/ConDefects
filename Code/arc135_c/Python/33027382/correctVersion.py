import heapq
import sys
from collections import defaultdict, deque
from math import inf
sys.setrecursionlimit(10**6)
MOD = 10**9 + 7

stdin = sys.stdin

ni = lambda: int(ns())
na = lambda: list(map(int, stdin.readline().split()))
ns = lambda: stdin.readline().rstrip()  # ignore trailing spaces

# count the ones in every position
# work out the gain by flipping bit in each position
# calculate the gain per number

n = ni()
A = na()

initial = sum(A)
bits = [0] * 32
for i in range(32):
    for a in A:
        bits[i] += a >> i & 1

value = [0] * n
best = -inf
best_idx = -1
for i in range(n):
    a = A[i]
    for b in range(32):
        if a >> b & 1:
            value[i] += 2**b * (n - bits[b] - bits[b])
    if value[i] > best:
        best = value[i]
        best_idx = i
ans = sum(a ^ A[best_idx] for a in A)
print(max(ans,initial))



