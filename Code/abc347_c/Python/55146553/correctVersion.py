import bisect
import collections
import functools
import heapq
import itertools
import math
import operator
import string
import sys

readline = sys.stdin.readline
LS = lambda: readline().strip()
LI = lambda: int(readline().strip())
LLS = lambda: readline().strip().split()
LL = lambda: list(map(int, readline().strip().split()))
LLMI = lambda: list(map((1).__rsub__, LL()))

N, A, B = LL()
D = sorted(set(d % (A + B) for d in LL()))
print("Yes" if any((D[(i + 1) % len(D)] - D[i]) % (A + B) > B for i in range(len(D))) or len(D) == 1 else "No")
