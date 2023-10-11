import sys
sys.setrecursionlimit(10**6)
# import resource
# resource.setrlimit(resource.RLIMIT_STACK, (1073741824//4, 1073741824//4))
from collections import deque, Counter, defaultdict
from itertools import accumulate, permutations, combinations
from bisect import bisect_left, bisect_right
from heapq import heapify, heappush, heappop
from string import ascii_letters, ascii_lowercase, ascii_uppercase, digits, printable
from operator import itemgetter
from functools import lru_cache, cmp_to_key
from math import gcd, sqrt
from atcoder.dsu import DSU
from atcoder.scc import SCCGraph
from atcoder.segtree import SegTree
from sortedcontainers import SortedList, SortedDict, SortedSet

INFTY = sys.maxsize
MOD10 = 10**9+7
MOD99 = 998244353
MOD = MOD99
YES = 'Yes'
NO  = 'No'
DRDC = [[-1, 0], [1, 0], [0, -1], [0, 1]]
DRDC2 = [[1, 1], [-1, 1], [1, -1], [-1, -1]]

def chr2num(c): return printable.index(c)
def num2chr(i): return printable[i]

def bisect_lt(a, x):
    '''Return rightmost index less than x, if not exits return -1'''
    return bisect_left(a, x) - 1
def bisect_le(a, x):
    '''Return rightmost index less than or equal to x, if not exists return -1'''
    return bisect_right(a, x) - 1
def bisect_gt(a, x):
    '''Return leftmost index greater than x, if not exists return len(a)'''
    return bisect_right(a, x)
def bisect_ge(a, x):
    '''Return leftmost index greater than or equal to x, if not exists return len(a)'''
    return bisect_left(a, x)
def bisect_lecount(a, x): return bisect_right(a, x)
def bisect_ltcount(a, x): return bisect_left(a, x)
def bisect_gecount(a, x): return len(a) - bisect_left(a, x)
def bisect_gtcount(a, x): return len(a) - bisect_right(a, x)

def sc_bisect_lt(sc, x): return sc.bisect_left(x) - 1
def sc_bisect_le(sc, x): return sc.bisect_right(x) - 1
def sc_bisect_gt(sc, x): return sc.bisect_right(x)
def sc_bisect_ge(sc, x): return sc.bisect_left(x)
def sc_bisect_lecount(sc, x): return sc.bisect_right(x)
def sc_bisect_ltcount(sc, x): return sc.bisect_left(x)
def sc_bisect_gecount(sc, x): return len(sc) - sc.bisect_left(x)
def sc_bisect_gtcount(sc, x): return len(sc) - sc.bisect_right(x)

def cmp_for_key(x, y):
    '''functools.cmp_to_key()に渡すための比較関数。
    タプルのソートなどでは x, y にタプルまるごと渡すようにする（遅くなるので）。
    key=cmp_to_key(cmp_for_key)'''
    
    s = x - y # ここを必要に応じて書き換える。
    
    if s < 0: return -1   # x < y (x が y より前)
    elif s == 0: return 0 # x == y
    else: return 1        # x > y (x が y より後)

# input = sys.stdin.readline
def iinput(): return int(input())
def minput(): return map(int, input().split())
def linput(): return list(map(int, input().split()))
# DEBUG = False
# def printd(*args):
#     if DEBUG:
#         print(*args)

def readinput():
    s = input()
    return s

def solve(args):
    s=args
    n = len(s)
    head = 0
    for i in range(n):
        if s[i] == 'a':
            head += 1
        else:
            break
    tail = 0
    for i in range(n)[::-1]:
        if s[i] == 'a':
            tail += 1
        else:
            break
    if head > tail:
        return NO
    t = 'a'*(tail-head) + s
    m = len(t) // 2
    if t[:m] == t[-m:][::-1]:
        return YES
    else:
        return NO

def printans(ans):
    if isinstance(ans, list) or isinstance(ans, tuple):
        print(*ans, sep='\n')
    else:
        print(ans)

if __name__=='__main__':
    args=readinput()
    ans=solve(args)
    printans(ans)
