import sys
sys.setrecursionlimit(10**9)
from collections import defaultdict
from collections import deque
import heapq
import math
import bisect
import itertools
def MI():return map(int,input().split())
def II():return int(input())

N,L,W=MI()
A=list(MI())
A.append(L)
now=0
ans=0
for a in A:
    if now<a:
        k=(a-now)//W+((a-now)%W>0)
        ans+=k
    now=a+W
print(ans)


