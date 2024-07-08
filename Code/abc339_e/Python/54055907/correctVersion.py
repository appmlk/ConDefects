# import sys
# sys.setrecursionlimit(10**6)
# sys.set_int_max_str_digits(10**6)
# from scipy.optimize import bisect
# from collections import defaultdict, Counter
# import bisect
# import heapq
from atcoder.segtree import SegTree
from sortedcontainers import SortedList

# mod = 998244353
# ds = [(-1,0),(0,1),(1,0),(0,-1)]

# S = input()
# N = int(input())
N, D = map(int, input().split())

A = list(map(int, input().split()))


M = max(A)
seg = SegTree(max, 0, M+1)
for i in range(N):
    a = A[i]
    m = seg.prod(max(a-D, 0),min(M+1,a+D+1))
    seg.set(a,m+1)
    
ans = seg.all_prod()
print(ans)