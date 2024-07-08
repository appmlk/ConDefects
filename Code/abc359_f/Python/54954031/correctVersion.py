import heapq
import sys
from collections import Counter, defaultdict, deque
from itertools import accumulate, combinations, permutations
from heapq import heappop, heappush
from math import inf
sys.setrecursionlimit(10**6)
MOD = 10**9 + 7

stdin = sys.stdin

ni = lambda: int(ns())
na = lambda: list(map(int, stdin.readline().split()))
ns = lambda: stdin.readline().rstrip()  # ignore trailing spaces

n = ni()
A = na()

# is any distribution of 2*(n-1) degrees a valid tree?

ans = A[:]
q = [(a*4 - a,a,2,i) for i,a in enumerate(A)]
heapq.heapify(q)
for _ in range(n-2):
    k,a,p,i = heappop(q)
    # print(k,a,p,i)
    ans[i] = a*p*p
    heappush(q,(a*(p+1)**2 - a*p**2,a,p+1,i))
print(sum(ans))
