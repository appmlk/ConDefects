import bisect
import copy
import decimal
import fractions
import heapq
import itertools
import math
import random
import sys
import time
from collections import Counter,deque,defaultdict
from functools import lru_cache,reduce
from heapq import heappush,heappop,heapify,heappushpop,_heappop_max,_heapify_max
def _heappush_max(heap,item):
    heap.append(item)
    heapq._siftdown_max(heap, 0, len(heap)-1)
def _heappushpop_max(heap, item):
    if heap and item < heap[0]:
        item, heap[0] = heap[0], item
        heapq._siftup_max(heap, 0)
    return item
from math import gcd as GCD
read=sys.stdin.read
readline=sys.stdin.readline
readlines=sys.stdin.readlines
write=sys.stdout.write
#import pypyjit
#pypyjit.set_param('max_unroll_recursion=-1')
#sys.set_int_max_str_digits(10**9)

N=int(readline())
D=list(map(int,readline().split()))
L0,C0,K0=map(int,readline().split())
L1,C1,K1=map(int,readline().split())
inf=1<<60
dp=[0]*(K0+1)
for d in D:
    prev=dp
    dp=[inf]*(K0+1)
    for k in range(K0+1):
        for kk in range(k,K0+1):
            dp[kk]=min(dp[kk],prev[k]+max(0,d-(kk-k)*L0+L1-1)//L1)
ans=inf
for k0 in range(K0+1):
    k1=dp[k0]
    if k1<=K1:
        ans=min(ans,k0*C0+k1*C1)
if ans==inf:
    ans=-1
print(ans)