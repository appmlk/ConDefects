#!/usr/bin/env python3

#import sys
#sys.setrecursionlimit(10**8)
#import math
#from itertools import product,permutations,combinations,combinations_with_replacement
#from sortedcontainers import SortedDict,SortedSet,SortedKeyList
#from collections import deque
#from heapq import heapify,heappop,heappush
#from bisect import bisect,bisect_left

#from atcoder.dsu import DSU as UFT
#from atcoder.fenwicktree import FenwickTree as FT
#from atcoder.math import floor_sum
#from atcoder.maxflow import MFGraph
#from atcoder.mincostflow import MCFGraph
#from atcoder.convolution import convolution
#from atcoder.scc import SCCGraph
#from atcoder.twosat import TwoSAT
#from atcoder.string import suffix_array, lcp_array
#from atcoder.segtree import SegTree
#from atcoder.lazysegtree import LazySegTree

def main():
    n,q = map(int,input().split())
    grid = list(input() for _ in range(n))
    cnt = [[0]*n for _ in range(n)]
    if grid[0][0] == 'B':
        cnt[0][0] = 1
    for i in range(1,n):
        if grid[i][0] == 'B':
            cnt[i][0] = cnt[i-1][0]+1
        else:
            cnt[i][0] = cnt[i-1][0]
    for j in range(1,n):
        if grid[0][j] == 'B':
            cnt[0][j] = cnt[0][j-1]+1
        else:
            cnt[0][j] = cnt[0][j-1]
    for i in range(1,n):
        for j in range(1,n):
            if grid[i][j] == 'B':
                cnt[i][j] = cnt[i-1][j]+cnt[i][j-1]-cnt[i-1][j-1]+1
            else:
                cnt[i][j] = cnt[i-1][j]+cnt[i][j-1]-cnt[i-1][j-1]
    def calc(a,b,c,d):
        res = cnt[c][d]
        if a == b == 0:
            pass
        elif a == 0:
            res -= cnt[c][b-1]
        elif b == 0:
            res -= cnt[a-1][d]
        else:
            res -= cnt[c][b-1]
            res -= cnt[a-1][d]
            res += cnt[a-1][b-1]
        return res
    for _ in range(q):
        a,b,c,d = map(int,input().split())
        ans = 0
        ma,mb,mc,md = a//n, b//n, c//n, d//n
        ra,rb,rc,rd = a%n, b%n, c%n, d%n
        if ma == mc and mb == md:
            ans += calc(ra,rb,rc,rd)
        elif ma == mc:
            ans += calc(ra,rb,rc,n-1)+calc(ra,0,rc,rd)
            ans += (md-mb-1)*calc(ra,0,rc,n-1)
        elif mb == md:
            ans += calc(ra,rb,n-1,rd)+calc(0,rb,rc,rd)
            ans += (mc-ma-1)*calc(0,rb,n-1,rd)
        else:
            ans += calc(ra,rb,n-1,n-1)
            ans += calc(ra,0,n-1,rd)
            ans += calc(0,rb,rc,n-1)
            ans += calc(0,0,rc,rd)
            ans += (mc-ma-1)*(calc(0,rb,n-1,n-1)+calc(0,0,n-1,rd))
            ans += (md-mb-1)*(calc(ra,0,n-1,n-1)+calc(0,0,rc,n-1))
            ans += (mc-ma-1)*(md-mb-1)*calc(0,0,n-1,n-1)
        print(ans)
if __name__ == '__main__':
    main()