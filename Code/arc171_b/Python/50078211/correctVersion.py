#!/usr/bin/env python3
import sys
import math
import bisect
from heapq import heapify, heappop, heappush
from collections import deque, defaultdict, Counter
from functools import lru_cache
from fractions import Fraction
from itertools import accumulate, combinations, permutations, product
from sortedcontainers import SortedSet, SortedList, SortedDict
mod = 998244353
n = int(input())
a = list(map(lambda x: int(x)-1, input().split()))
m = 998244353

for i in range(n):

    if a[i]<i or a[a[i]] != a[i]:
        print(0)
        exit()

cnt = 0
ans = 1
s = set()
for i in range(n):
    if a[i] not in s:
        s.add(a[i])
        cnt += 1
    if i == a[i]:
        ans *= cnt
        ans %= m
        cnt -= 1
print(ans)
    
