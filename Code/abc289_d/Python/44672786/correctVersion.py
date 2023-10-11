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


n = I()
A = LI()
m = I()
B = set(LI())
x = I()

dp = [False for _ in range(x+1)]
dp[0] = True

for now in range(x+1):
    if dp[now] and (not now in B):
        for a in A:
            if now + a < x+1:
                dp[now+a] = True
ans = "Yes" if dp[x] else "No"
print(ans)
