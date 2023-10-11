import sys,random,bisect
from collections import deque,defaultdict
from heapq import heapify,heappop,heappush
from itertools import permutations
from math import gcd,log,sqrt
from atcoder.modint import ModContext, Modint
from atcoder.dsu import DSU

ModContext(1).context.append(998244353)
sys.setrecursionlimit(1000000)

input = lambda :sys.stdin.readline().rstrip()
mi = lambda :map(int,input().split())
li = lambda :list(mi())

X = int(input())
def isOK(x):
    x = str(x)
    dif = ord(x[1]) - ord(x[0])
    for i in range(len(x) - 1):
        if ord(x[i + 1]) - ord(x[i]) != dif:
            return False
    return True
s = str(X)
if len(s) <= 2:
    print(X)
elif len(s) <= 7:
    for i in range(X, 10000000):
        if isOK(i):
            print(i)
            exit()
else:
    cand = [12345678, 23456789, 76543210, 87654321, 98765432, 876543210, 987654321, 9876543210, 123456789]
    cand.append(int(s[0] * len(s)))
    if s[0] == '9':
        cand.append(int('1' * (len(s) + 1)))
    else:
        cand.append(int(chr(ord(s[0]) + 1) * len(s)))
    cand.sort()
    print(cand[bisect.bisect_left(cand, X)])