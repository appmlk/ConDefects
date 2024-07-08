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

a, b, C = LL()
c = C.bit_count()
xyz2 = a + b + c
if xyz2 % 2 == 1:
    print(-1)
    quit()
xyz = xyz2 // 2
x = xyz - b
y = xyz - c
z = xyz - a
if x < 0 or y < 0 or z < 0:
    print(-1)
    quit()

mask1 = 0
mask2 = C
for _ in range(x):
    mask1 += mask2 & -mask2
    mask2 -= mask2 & -mask2
mask3 = 0
for i in range(60):
    if (mask1 >> i & 1 == 0) and (mask2 >> i & 1 == 0):
        if mask3.bit_count() < y:
            mask3 |= 1 << i
        else:
            break
mask1 |= mask3
mask2 |= mask3
if mask1 >= (1 << 60) or mask2 >= (1 << 60):
    print(-1)
    quit()

print(mask1, mask2)
