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

def XOR_Basis(lst):
    xor_basis=[]
    triangulation=[]
    for i,x in enumerate(lst):
        xx=x
        for j,xb in enumerate(triangulation):
            if xx>xx^xb:
                xx=xx^xb
        if xx:
            xor_basis.append(x)
            for j in range(len(triangulation)):
                if triangulation[j]^xx<triangulation[j]:
                    triangulation[j]^=xx
            triangulation.append(xx)
    return xor_basis,triangulation

N=int(input())
A=list(map(int,input().split()))
ans=0
if N==2:
    print(A[0]^A[1])
    exit()
B=62
for i in range(N):
    A[i]|=1<<B
_,tria=XOR_Basis(A)
tria.sort()
b=tria.pop()^1<<B
xor=0
for x in tria[::-1]:
    xor=max(xor,xor^x)
if N%4==2:
    a=0
    for i in range(N):
        a^=A[i]
    if a==xor and tria and len(tria)==N-1:
        xor^=tria[0]
ans=max(ans,xor)
print(ans)