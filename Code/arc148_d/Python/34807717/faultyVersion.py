import sys
from collections import defaultdict, Counter, deque
from itertools import permutations, combinations, product, combinations_with_replacement, groupby, accumulate
import operator
from math import sqrt, gcd, factorial
#from math import isqrt, prod, comb  #python3.8用(notpypy)
#from bisect import bisect_left, bisect_right
from functools import lru_cache, reduce, cmp_to_key
#from heapq import heappush, heappop, heapify, heappushpop, heapreplace
#import numpy as np
#import networkx as nx
#from networkx.utils import UnionFind
#from numba import njit, b1, i1, i4, i8, f8
#numba例 @njit(i1(i4[:], i8[:, :]),cache=True) 引数i4配列、i8 2次元配列,戻り値i1
#from scipy.sparse import csr_matrix
#from scipy.sparse.csgraph import shortest_path, floyd_warshall, dijkstra, bellman_ford, johnson, NegativeCycleError, maximum_bipartite_matching, maximum_flow, minimum_spanning_tree
def input(): return sys.stdin.readline().rstrip()
def divceil(n, k): return 1+(n-1)//k  # n/kの切り上げを返す
def yn(hantei, yes='Yes', no='No'): print(yes if hantei else no)


def main():
    mod = 10**9+7
    mod2 = 998244353
    n,m=map(int, input().split())
    A=list(map(int, input().split()))
    Cnt=Counter(A)
    for i in Cnt.keys():
        Cnt[i]%=2
    if m%2==1:
        yn(all(Cnt[i]==0 for i in Cnt.keys()),'Bob','Alice')
    else:
        yn(all((Cnt[i]==1 and Cnt[(i+(m//2))%m]==1) or (Cnt[i]==0 and Cnt[(i+(m//2))%m]==0) for i in Cnt.keys()),'Bob','Alice')
    



if __name__ == '__main__':
    main()

