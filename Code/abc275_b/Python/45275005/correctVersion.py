import bisect,collections,itertools,math,functools,heapq
import sys
# sys.setrecursionlimit(10**6)
def I(): return int(sys.stdin.readline().rstrip())
def LI(): return list(map(int,sys.stdin.readline().rstrip().split()))
def LF(): return list(map(float,sys.stdin.readline().rstrip().split()))
def SI(): return sys.stdin.readline().rstrip()
def LS(): return list(sys.stdin.readline().rstrip().split())

"""
方針
"""

a,b,c,d,e,f=LI()
mod = 998244353
x = a%mod*b%mod*c%mod
y = d%mod*e%mod*f%mod
print((x-y)%mod)