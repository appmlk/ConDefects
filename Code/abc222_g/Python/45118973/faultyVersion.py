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

def doubling(n,mod):
    a,res,c,c1 = 1,0,1,0
    while n > 0:
        if n&1:
            res = (a*pow(10,c1,mod)+res)%mod
            c1 += c
        a = (a+a*pow(10,c,mod))%mod
        n >>= 1
        c *= 2
    return res

def euler_phi(n):
    res = n
    for x in range(2,n+1):
        if x ** 2 > n:
            break
        if n%x==0:
            res = res//x * (x-1)
            while n%x==0:
                n //= x
    if n!=1:
        res = res//n * (n-1)
    return res

def divisors(n):
    lower_divisors, upper_divisors = [], []
    i = 1
    while i * i <= n:
        if n % i == 0:
            lower_divisors.append(i)
            if i != n // i:
                upper_divisors.append(n // i)
        i += 1
    return lower_divisors + upper_divisors[::-1]

def calc(K:int)->int:
    if K == 1 or K == 2:return 1
    if K % 2 == 0:
        K //= 2
    if math.gcd(K,10) != 1:return -1
    for i in divisors(euler_phi(K)):
        if pow(10,i,K) == 1:
            j = 1
            while True:
                if doubling(i*j,K) == 0:return i
                j += 1
def main():
    T = ii()
    ans = []
    for i in range(T):
        K = ii()
        ans.append(calc(K))
    print(*ans,sep="\n")
    
if __name__=="__main__":
    main()