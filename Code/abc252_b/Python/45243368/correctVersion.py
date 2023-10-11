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
    N,K = mi()
    A = [(j,i) for i,j in enumerate(li())]
    B = set(li())
    A.sort(reverse=True)
    M = A[0][0]
    for i in range(N):
        if A[i][0] == M:
            if A[i][1]+1 in B:
                print("Yes")
                return
    print("No")


if __name__=="__main__":
    main()