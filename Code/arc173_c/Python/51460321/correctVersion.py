#https://atcoder.jp/contests/ARC173/tasks/ARC173_d 
import sys
sys.setrecursionlimit(5*10**5)
input = sys.stdin.readline
from collections import defaultdict, deque, Counter
from heapq import heappop, heappush
from bisect import bisect_left, bisect_right
from math import gcd, lcm
from itertools import permutations


n = int(input())
p = list(map(int,input().split()))

left = [i for i in range(n)]
ans = [-1]*(n)
now = 3
while left:
    new = []
    for i in left:
        med = 1
        x = now//2
        c = p[i]
        if i-x >= 0 and i+x < n:
            l = p[i-x]
            r = p[i+x]
            med &= (l<c<r) or (r<c<l)
        if i + (now-1) < n:
            l = p[i+now-2]
            r = p[i+now-1]
            med &= (l<c<r) or (r<c<l)
        if i - (now-1) >= 0:
            l = p[i-(now-1)]
            r = p[i-(now-2)]
            med &= (l<c<r) or (r<c<l)
        if now >= 5 and 1 <= i <= n-2:
            if i + (now-2) < n:
                l = p[i+now-3]
                r = p[i+now-2]
                med &= (l<c<r) or (r<c<l)
            if i - (now-2) >= 0:
                l = p[i-(now-2)]
                r = p[i-(now-3)]
                med &= (l<c<r) or (r<c<l)
        if med:
            new.append(i)
        else:
            ans[i] = now

    now += 2
    left = new
    if now > n:
        break


print(*ans)



