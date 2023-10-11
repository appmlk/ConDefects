import sys
import copy
from collections import deque,defaultdict
import math
import heapq
from itertools import accumulate
import itertools 
from functools import reduce
#import pypyjit
#pypyjit.set_param('max_unroll_recursion=-1')
sys.setrecursionlimit(10**8)
mod = 10**9 + 7
INF = math.inf
input = lambda: sys.stdin.readline().rstrip()
ii = lambda: int(input())
mi = lambda: map(int, input().split())
li = lambda: list(mi())
from functools import lru_cache #@lru_cache(maxsize=None)

def main():
    N,Q = mi()
    tbl = [i for i in range(N)] #tbl[i] := ボールiのindex
    index = [i for i in range(N)] #index[i] := index i のボール
    for _ in range(Q):
        x = ii()-1
        if tbl[x] == N-1:
            y = index[tbl[x]-1]
            i,j = tbl[x],tbl[x]-1
            tbl[x],tbl[y] = j,i
            index[j],index[i] = x,y
        else:
            y = index[tbl[x]+1]
            i,j = tbl[x],tbl[x]+1
            tbl[x],tbl[y] = j,i
            index[j],index[i] = x,y

    print(*map(lambda x:x+1,index),sep=" ")
    
if __name__=="__main__":
    main()