import bisect
import collections
import copy
import heapq
import itertools
import math
import string
import sys
def I(): return int(sys.stdin.readline().rstrip())
def LI(): return list(map(int, sys.stdin.readline().rstrip().split()))
def S(): return sys.stdin.readline().rstrip()
def LS(): return list(sys.stdin.readline().rstrip().split())


n, k = LI()
if k % 10 == 0 or k > int(str(k)[::-1]):
    print(0)
    sys.exit()
s = str(k)
nums = set()
ans = 0
while int(s) <= n:
    if not int(s) in nums:
        ans += 1
        nums.add(int(s))
    s += '0'
s = str(k)[::-1]
while int(s) <= n:
    if not int(s) in nums:
        ans += 1
        nums.add(int(s))
    s += '0'
print(ans)
