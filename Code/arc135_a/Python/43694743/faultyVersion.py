import sys, math, itertools, heapq, copy, collections, bisect, random, time
from collections import deque, defaultdict, Counter
from decimal import Decimal
from functools import lru_cache


def MI(): return map(int, sys.stdin.buffer.readline().split())
def MI1(): return map(lambda x:int(x)-1, sys.stdin.buffer.readline().split())
def LI(): return list(map(int, sys.stdin.buffer.readline().split()))
def I(): return int(sys.stdin.buffer.readline())
def S(): return sys.stdin.buffer.readline().rstrip().decode('utf-8')
def LS(): return sys.stdin.buffer.readline().rstrip().decode('utf-8').split()
def SL(): return list(sys.stdin.buffer.readline().rstrip().decode('utf-8'))
def IR(n): return [I() for _ in range(n)]
def LIR(n): return [LI() for _ in range(n)]
def LSR(n): return [LS() for _ in range(n)]
def SLR(n): return [SL() for _ in range(n)]

def resolve():
    X = 10**18
    mod = 998244353
    @lru_cache(maxsize=None)
    def dfs(now):
        if now <= 4:
            return now
        return dfs((now//2))*dfs(((now+1)//2))%mod
    print(dfs(X))



if __name__ == "__main__":
    resolve()

