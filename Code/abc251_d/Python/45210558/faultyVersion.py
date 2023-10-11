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
from itertools import combinations
def main():
    W = ii()
    A = []
    for i in range(1,101):
        A.append(i*(100**2))
        A.append(i*(100))
        A.append(i)
    A.sort()
    print(len(A))
    print(A,sep=" ")


if __name__=="__main__":
    main()