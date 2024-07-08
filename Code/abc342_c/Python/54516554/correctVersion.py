from atcoder.lazysegtree import LazySegTree
from atcoder.segtree import SegTree
from math import log2,log,floor,ceil,gcd
from itertools import *
from collections import *
from copy import deepcopy
from heapq import *
import sys

# import pypyjit
# pypyjit.set_param('max_unroll_recursion=-1')
def lcm(x,y):
    return (x*y)//gcd(x,y)

# def op(ele1, ele2):
#     return ele1 + ele2


# def mapping(func, ele):
#     return func + ele


# def composition(func_upper, func_lower):
#     return func_upper + func_lower

##############################################################################
N=int(input())
S=list(input())
Q=int(input())
D=defaultdict(set)
D2=defaultdict(list)
for i in range(N):
    D[S[i]].add(S[i])
    D2[S[i]].append(i)

for i in range(Q):
    c,d=map(str,input().split())
    if d==c:
        continue
    D[d]|=D[c]
    D[c]=set()

ans=S[:]
for i in D.keys():
    li=list(D[i])
    for j in li:
        for k in D2[j]:
            ans[k]=i

print("".join(ans))

