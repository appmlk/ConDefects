import sys
from collections import deque,defaultdict
import heapq
import math
import collections
import itertools
import bisect

#sys.setrecursionlimit(10 ** 9)
input = lambda: sys.stdin.readline().rstrip()
ii = lambda: int(input())
mi = lambda: map(int, input().split())
li = lambda: list(mi())
lli = lambda n: [li() for _ in range(n)]

N = ii()
A = li()
Q = ii()
d = defaultdict(list)
for i in range(N):
    d[A[i]].append(i+1)

ans = []
for i in range(Q):
    L,R,X = mi()
    if d[X]:
        left = bisect.bisect_left(d[X],L)
        right = bisect.bisect_right(d[X],R)
        ans.append(right-left)
    else:
        ans.append(0)

print(ans)