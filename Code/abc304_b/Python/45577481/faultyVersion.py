import sys
# sys.setrecursionlimit(100000)
input=lambda:sys.stdin.readline().strip()
# write=lambda x:sys.stdout.write(str(x)+'\n')

# from decimal import Decimal
# from random import randint
# from copy import deepcopy
from collections import deque,Counter
# from heapq import heapify,heappush,heappop
# from bisect import bisect_left,bisect,insort
from math import inf,sqrt,gcd,lcm,pow,ceil,floor,log,log2,log10,pi,sin,cos,tan,asin,acos,atan
# from functools import cmp_to_key
# from itertools import permutations,combinations   

def solve():
    n=int(input())
    if n<1e3:
        return n
    elif n<1e4:
        return n//10*10
    elif n<1e5:
        return n//100*100
    elif n<1e6:
        return n//1000*1000
    elif n<1e7:
        return n//10000*10000
    elif n<1e8:
        return n//100000*100000
    elif n<1e9:
        return n//100000*100000


# t=int(input())
# for _ in range(t):
#     print(solve())
    
print(solve())

# solve()