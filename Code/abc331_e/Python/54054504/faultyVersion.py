from heapq import heappush, heappop, heapify
from collections import defaultdict, deque,Counter
from math import ceil, floor, sqrt, factorial,gcd,cos,sin,pi
from itertools import permutations, combinations,product
from bisect import bisect_left, bisect_right
from copy import deepcopy
from fractions import Fraction
from random import randint
import sys
from functools import cache,lru_cache #@lru_cache(maxsize=None)
from time import time
#from sortedcontainers import SortedList # type: ignore
#sys.setrecursionlimit(10**6)
vector1 = [[0, -1], [1, 0], [0, 1], [-1, 0]]
vector2 = [[0, 1], [1, 0], [-1, 0], [0, -1],
           [1,-1], [-1, 1], [1, 1], [-1, -1]]
alphabet = "abcdefghijklmnopqrstuvwxyz"


def main():
    N,M,L = map(int,input().split())
    a = list(map(int,input().split()))
    b = list(map(int,input().split()))
    a = [(val,i+1) for i,val in enumerate(a)]
    b = [(val,i+1) for i,val in enumerate(b)]
    a.sort(reverse=True)
    b.sort(reverse=True)
    print(a)
    print(b)
    s = set()
    for _ in range(L):
        c,d = map(int,input().split())
        s.add((c,d))
    ans = 0
    for i in range(N):
        for j in range(M):
            av,ai = a[i]
            bv,bi = b[j]
            if (ai,bi) in s:
                continue
            else:
                ans = max(ans,av+bv)
                if j == 0:
                    print(ans)
                    exit()
                break
    print(ans)
    
                
            

if __name__ == '__main__':
    main()


