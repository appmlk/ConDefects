import sys,random,bisect
from collections import deque,defaultdict
from heapq import heapify,heappop,heappush
from functools import lru_cache
from itertools import permutations
from math import gcd,log,sqrt
from atcoder.modint import ModContext, Modint
from atcoder.dsu import DSU

ModContext(1).context.append(998244353)
sys.setrecursionlimit(1000000)

input = lambda :sys.stdin.readline().rstrip()
mi = lambda :map(int,input().split())
li = lambda :list(mi())

S = input()
T = 'oxx' * 100
for i in range(len(T)):
    for j in range(i + 1, len(T)):
        if T[i:j+1] == S:
            print('Yes')
            exit()
print('No')