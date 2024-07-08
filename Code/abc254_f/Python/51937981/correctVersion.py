INF = 1 << 62

def gcd(a, b):
    if b == 0: return a
    else: return gcd(b, a%b)

from atcoder.segtree import SegTree

def op(data1, data2):
    return math.gcd(data1, data2)

# opの単位元 op(data1, e) = data1
e = 0


import bisect, heapq, sys, math, copy, itertools, decimal
from collections import defaultdict, deque
sys.setrecursionlimit(10**7)
def INT(): return int(input())
def MI(): return map(int, input().split())
def MS(): return map(str, input().split())
def LI(): return list(map(int, input().split()))
def LS(): return list(map(str, input().split()))
def pr_line(itr): print(*itr, sep='\n')
def pr_mtx(matrix): [print(*row) for row in matrix] 
INF = float('inf')

N, Q = MI()
A = LI()
B = LI()

AA = [A[i+1] - A[i] for i in range(N-1)]
BB = [B[i+1] - B[i] for i in range(N-1)]


segA = SegTree(op, e, AA)
segB = SegTree(op, e, BB)

ans = []
for _ in range(Q):
    h1, h2, w1, w2 = list(map(lambda x:x-1, LI()))
    ans.append(gcd(A[h1] + B[w1], gcd(segA.prod(h1, h2), segB.prod(w1, w2))))

pr_line(ans)
