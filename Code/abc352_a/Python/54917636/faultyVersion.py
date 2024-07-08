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

n, x, y, z = LL()
print("Yes" if x < z < y else "No")
