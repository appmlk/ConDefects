import sys, bisect, math, itertools, string, queue, copy
from collections import *
from itertools import *
from heapq import *
# input = sys.stdin.readline
sys.setrecursionlimit(10**8)
mod = 998244353
def inp(): return int(input())
def inpm(): return map(int,input().split())
def inpl(): return list(map(int, input().split()))
def inpls(): return list(input().split())
def inplm(n): return list(int(input()) for _ in range(n))
def inplL(n): return [list(input()) for _ in range(n)]
def inplT(n): return [tuple(input()) for _ in range(n)]
def inpll(n): return [list(map(int, input().split())) for _ in range(n)]
def inplt(n): return [tuple(map(int, input().split())) for _ in range(n)]
def inplls(n): return sorted([list(map(int, input().split())) for _ in range(n)])

def make_divisors(n):
    lower_divisors , upper_divisors = [], []
    i = 1
    while i*i <= n:
        if n % i == 0:
            lower_divisors.append(i)
            if i != n // i:
                upper_divisors.append(n//i)
        i += 1
    return lower_divisors + upper_divisors[::-1]
    
n = inp()
s = input()

shift = []
for ss in s:
    if ss == '#':
        shift.append('0')
    else:
        shift.append('1')

divisor = make_divisors(n)
tmp = defaultdict(int)
for d in divisor:
    if d == n: continue
    if d == 1:
        tmp[1] = 1
        if '1' not in shift:
            tmp[1] += 1
        continue
    
    bit = 0
    for i in range(n//d):
        bit = bit | int(''.join(shift[i*d:i*d+d]),2)
    
    bit = bin(bit)
    cnt = 0
    for ss in str(bit):
        if ss == '1':
            cnt += 1
    cnt = d - cnt
    tmp[d] = pow(2,cnt,mod)

for k,v in tmp.items():
    for d in make_divisors(k):
        if d == k: continue
        tmp[k] -= tmp[d]

ans = sum(tmp.values())
print(ans)


