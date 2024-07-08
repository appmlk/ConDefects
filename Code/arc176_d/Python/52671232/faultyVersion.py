import bisect
import copy
import decimal
import fractions
import heapq
import itertools
import math
import random
import sys
import time
from collections import Counter,deque,defaultdict
from functools import lru_cache,reduce
from heapq import heappush,heappop,heapify,heappushpop,_heappop_max,_heapify_max
def _heappush_max(heap,item):
    heap.append(item)
    heapq._siftdown_max(heap, 0, len(heap)-1)
def _heappushpop_max(heap, item):
    if heap and item < heap[0]:
        item, heap[0] = heap[0], item
        heapq._siftup_max(heap, 0)
    return item
from math import gcd as GCD
read=sys.stdin.read
readline=sys.stdin.readline
readlines=sys.stdin.readlines
write=sys.stdout.write
#import pypyjit
#pypyjit.set_param('max_unroll_recursion=-1')
#sys.set_int_max_str_digits(10**9)

def Berlekamp_Massey(A):
    n = len(A)
    B, C = [1], [1]
    l, m, p = 0, 1, 1
    for i in range(n):
        d = A[i]
        for j in range(1, l + 1):
            d += C[j] * A[i - j]
            d %= mod
        if d == 0:
            m += 1
            continue
        T = C.copy()
        q = pow(p, mod - 2, mod) * d % mod
        if len(C) < len(B) + m:
            C += [0] * (len(B) + m - len(C))
        for j, b in enumerate(B):
            C[j + m] -= q * b
            C[j + m] %= mod
        if 2 * l <= i:
            B = T
            l, m, p = i + 1 - l, 1, d
        else:
            m += 1
    res = [-c % mod for c in C[1:]]
    return res

def BMBM(A,N,mod=0):
    deno=[1]+[-c for c in Berlekamp_Massey(A)]
    nume=[0]*(len(deno)-1)
    for i in range(len(A)):
        for j in range(len(deno)):
            if i+j<len(nume):
                nume[i+j]+=A[i]*deno[j]
                nume[i+j]%=mod
    return Bostan_Mori(nume,deno,N,mod=mod)

def Bostan_Mori(poly_nume,poly_deno,N,mod=0,convolve=None):
    #if type(poly_nume)==Polynomial:
    #    poly_nume=poly_nume.polynomial
    #if type(poly_deno)==Polynomial:
    #    poly_deno=poly_deno.polynomial
    if convolve==None:
        def convolve(poly_nume,poly_deno):
            conv=[0]*(len(poly_nume)+len(poly_deno)-1)
            for i in range(len(poly_nume)):
                for j in range(len(poly_deno)):
                    x=poly_nume[i]*poly_deno[j]
                    if mod:
                        x%=mod
                    conv[i+j]+=x
            if mod:
                for i in range(len(conv)):
                    conv[i]%=mod
            return conv
    while N:
        poly_deno_=[-x if i%2 else x for i,x in enumerate(poly_deno)]
        if N%2:
            poly_nume=convolve(poly_nume,poly_deno_)[1::2]
        else:
            poly_nume=convolve(poly_nume,poly_deno_)[::2]
        poly_deno=convolve(poly_deno,poly_deno_)[::2]
        if mod:
            for i in range(len(poly_nume)):
                poly_nume[i]%=mod
            for i in range(len(poly_deno)):
                poly_deno[i]%=mod
        N//=2
    return poly_nume[0]

N,M=map(int,input().split())
P=list(map(int,input().split()))
for i in range(N):
    P[i]-=1
ans=0
mod=998244353
inve0=pow(N*(N-1)-4*(N-2)-2,mod-2,mod)
inve1=pow(4*(N-2),mod-2,mod)
def f0(N,i,j):
    if N<=3:
        return 0
    retu=N*(N+1)*(N-1)//3
    retu-=(i*(i+1)//2+(N-1-i)*(N-i)//2-abs(i-j))*2
    retu-=(j*(j+1)//2+(N-1-j)*(N-j)//2-abs(i-j))*2
    retu-=abs(i-j)*2
    return retu*inve0%mod

def f1(N,i,j):
    if N<=2:
        return 0
    retu=0
    retu+=(i*(i+1)//2+(N-1-i)*(N-i)//2-abs(i-j))*2
    retu+=(j*(j+1)//2+(N-1-j)*(N-j)//2-abs(i-j))*2
    return retu*inve1%mod

def f2(N,i,j):
    return abs(i-j)

cnt=[0]*3
cnt[0]+=(N*(N-1)//2-4)
cnt[1]+=(N-3)
cnt[0]+=2*2
cnt[1]+=((N-2)*(N-3)//2+N-2+1)
cnt[2]+=2*(N-2)
cnt[1]+=1
cnt[2]+=(N-2)*(N-3)//2+1

dp=[0,0,1]
lst=[]
for m in range(5):
    lst.append(sum((f0(N,P[i],P[i+1])*dp[0]+f1(N,P[i],P[i+1])*dp[1]+f2(N,P[i],P[i+1])*dp[2])%mod for i in range(N-1)))
    prev=dp
    dp=[0]*3
    dp[0]+=prev[0]*(N*(N-1)//2-4)
    dp[0]+=prev[1]*(N-3)
    dp[1]+=prev[0]*2*2
    dp[1]+=prev[1]*((N-2)*(N-3)//2+N-2+1)
    dp[1]+=prev[2]*2*(N-2)
    dp[2]+=prev[1]*1
    dp[2]+=prev[2]*((N-2)*(N-3)//2+1)
    for c in range(3):
        dp[c]%=mod
ans+=BMBM(lst,M,mod)
ans%=mod
print(ans)
