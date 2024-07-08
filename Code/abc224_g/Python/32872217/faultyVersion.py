import sys
import pypyjit
pypyjit.set_param('max_unroll_recursion=-1')
from itertools import combinations, permutations, product, accumulate, groupby
from collections import defaultdict, deque, Counter
from functools import reduce
from operator import add, mul
import heapq as hq
import bisect
sys.setrecursionlimit(10**7)
input = sys.stdin.readline
N,S,T,A,B = map(int,input().split())

if T == 1:
    if S == 1:
        print(0)
    else:
        print(N*B)
    exit()

ok = 1
ng = T

def f(l):
    return (N*B + A*l*(l+1)//2)/(l+1)

while ng - ok > 1:
    x = (ng+ok)//2
    L = f(x)
    if x*A <= L:
        ok = x
    else:
        ng = x

l = ok
L = f(l)
if T < S:
    print(L)
elif (T-S) <= l:
    print((T-S)*A)
else:
    print(L)



