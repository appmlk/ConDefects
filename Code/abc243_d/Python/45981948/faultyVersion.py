import sys
from collections import deque,defaultdict
import heapq
import math
import collections
import itertools

#sys.setrecursionlimit(10 ** 9)
input = lambda: sys.stdin.readline().rstrip()
ii = lambda: int(input())
mi = lambda: map(int, input().split())
li = lambda: list(mi())
lli = lambda n: [li() for _ in range(n)]

N,X = mi()
S = input()
q = deque()
for s in S:
    if q:
        if s == "U":
            q.pop()
        else:
            q.append(s)
    else:
        q.append(s)

for s in q:
    if s == "U":
        X = X // 2
    elif s == "L":
        X = 2 *X
    else:
        X = 2 * X + 1

print(X)
