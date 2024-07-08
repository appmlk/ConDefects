from heapq import heappush, heappop, heapify
import sys
from collections import defaultdict, deque,Counter
from math import ceil, floor, sqrt, factorial,gcd
from itertools import permutations, combinations,product #順列,組み合わせ,重複順列
from bisect import bisect_left, bisect_right
from copy import deepcopy
from functools import lru_cache #@lru_cache(maxsize=None)
from fractions import Fraction
from copy import deepcopy
sys.setrecursionlimit(10**6)
# input = sys.stdin.readline
vector1 = [[0, -1], [1, 0], [0, 1], [-1, 0]]
vector2 = [[0, 1], [1, 0], [-1, 0], [0, -1],
           [1,-1], [-1, 1], [1, 1], [-1, -1]]



def main():
    inf = 1 << 40
    N,K,P = map(int,input().split())
    dp = defaultdict(lambda : inf)
    dp[tuple([0]*K)] = 0
    for _ in range(N):
        r = list(map(int,input().split()))
        c = r[0]
        array = r[1:]
        newdp = defaultdict(lambda:inf)
        for key in dp:
            newdp[key] = min(newdp[key],dp[key])
            tmp = list(key)
            for i in range(K):
                tmp[i] = min(P,tmp[i] + array[i])
            tmp = tuple(tmp)
            newdp[tmp] = min(dp[key]+c,newdp[tmp])
        dp = newdp

    if dp[tuple([P]*K)] == inf:
        print(-1)
    else:
        print(dp[tuple([P]*K)])
if __name__ == '__main__':
    main()
