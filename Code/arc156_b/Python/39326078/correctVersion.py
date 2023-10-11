from collections import defaultdict, deque, Counter
from itertools import combinations, permutations, product, accumulate
from heapq import heapify, heappop, heappush
import math
import bisect
import sys
# sys.setrecursionlimit(700000)
input = lambda: sys.stdin.readline().rstrip('\n')
inf = float('inf')
mod1 = 10**9+7
mod2 = 998244353
def seil_div(x, y): return -(-x//y)

#################################################

def cmb(n, r):
    if r < 0 or r > n:
        return 0
    r = min(r, n-r)
    return fact[n]*factinv[r]%p*factinv[n-r]%p

p = mod2
N = 10**6 #Nは必要分だけ用意する
fact = [1, 1] #fact[n]: n! mod p
factinv = [1, 1] #factinv[n]: n!^(-1) mod p
inv = [0, 1] #inv[n]: n^(-1) mod p

for i in range(2, N+1):
    fact.append((fact[-1]*i)%p)
    inv.append((-inv[p%i]*(p//i))%p)
    factinv.append((factinv[-1]*inv[-1])%p)

N, K = map(int, input().split())
A = set(map(int, input().split()))
m = 0
ans = 0
for x in range(4*10**5+1):
    ans += cmb(K-m-1+x, x)
    ans %= mod2
    m += x not in A
    if m > K: break
print(ans)