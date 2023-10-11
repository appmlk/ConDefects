import sys
sys.setrecursionlimit(5*10**5)
input = sys.stdin.readline
from collections import defaultdict, deque, Counter
from heapq import heappop, heappush
from bisect import bisect_left, bisect_right
from math import gcd

n = int(input())
a = list(map(int,input().split()))
mod = 998244353
d = defaultdict(int)
for i in range(n):
    d[a[i]] += 1
last = [1]
tot = 1
for i in range(1, 2*10**5+10):
    cnt = len(last) - 1
    new = []
    di = d[i]
    mx = (cnt + di)//2
    for j in range(mx+1):
        need = max(0,2*j-di)
        new.append(last[need])
    
    for i in range(len(new)-1)[::-1]:
        new[i] += new[i+1]
        new[i] %= mod
    last = new

print(last[0] % mod)



