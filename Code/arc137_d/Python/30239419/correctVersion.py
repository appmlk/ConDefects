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
from collections import Counter, deque,defaultdict
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

N,M=map(int,readline().split())
A=list(map(int,readline().split()))
n=(max(M,N)-1).bit_length()+2
ans_lst=[None]*(1<<n)
A=A[::-1]
A=A+[0]*((1<<n)-len(A))
for nn in range(n):
    for bit in range(1<<n):
        if bit&1<<nn:
            A[bit]^=A[bit^1<<nn]
A=A[::-1]
print(*A[:M])