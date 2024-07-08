import sys, math
from collections import deque, defaultdict, Counter
from itertools import permutations, combinations, product
from bisect import bisect_left, bisect_right
from copy import deepcopy
from fractions import Fraction
from decimal import Decimal
from heapq import heapify, heappop, heappush, heappushpop
from functools import cache
input = sys.stdin.readline
MI = lambda: map(int, input().split())
LI = lambda: list(map(int, input().split()))
II = lambda: int(input())
IR = lambda: input().rstrip()
LIR = lambda: list(input().rstrip())
LIRS = lambda: list(input().rstrip().split())
INF = math.inf


n=II()
s=list(map(int,LIR()))
s=sorted(s,reverse=True)
check=''.join(map(str,s))
temp=0
for i in s:
    temp*=10
    temp+=i
i=1
cnt=0
while i*i<=temp:
    if check==''.join(sorted(str(i*i)+'0'*(n-len(str(i*i))),reverse=True)):
        cnt+=1
    i+=1
print(cnt)