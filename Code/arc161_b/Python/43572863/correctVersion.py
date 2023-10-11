a=1
flg=[]
while a < 10**18:
    flg.append(a)
    a*=2

s=set()
l=len(flg)
for i in range(l):
    for j in range(l):
        for k in range(l):
            if i != j and i != k and j != k:
                if flg[i]+flg[j]+flg[k] <= 10**18:
                    s.add(flg[i]+flg[j]+flg[k])

l=list(s)
l.sort()
le=len(l)
import sys
# sys.setrecursionlimit(5*10**5)
input = sys.stdin.readline
from collections import defaultdict, deque, Counter
from heapq import heappop, heappush
from bisect import bisect_left, bisect_right
from math import gcd

def sol(n):
    idx=bisect_right(l,n)
    if idx-1<0:
        return -1
    else:
        return l[idx-1]
    
T = int(input())
for i in range(T):
    n = int(input())
    print(sol(n))