import sys
#input = sys.stdin.readline
#input = sys.stdin.buffer.readline #文字列はダメ
#sys.setrecursionlimit(1000000)
#import math
#import bisect
#import itertools
#import random
#from heapq import heapify, heappop, heappush
#from collections import defaultdict 
#from collections import deque
#import copy #DeepCopy: hoge = [_[:] for _ in hogehoge]
#from functools import lru_cache
#@lru_cache(maxsize=None)
#MOD = pow(10,9) + 7
MOD = 998244353
#dx = [1,0,-1,0]
#dy = [0,1,0,-1]
#dx8 = [1,1,0,-1,-1,-1,0,1]
#dy8 = [0,1,1,1,0,-1,-1,-1]

def cmb(n, r, p):
  if (r < 0) or (n < r):
    return 0
  r = min(r, n - r)
  ret = fac[n] * finv[r] % p
  ret = ret * finv[n-r] % p
  return ret

def cmb_inv(n, r, p):
  if (r < 0) or (n < r):
    return 0
  r = min(r, n - r)
  ret = fac[n-r] * fac[r] % p
  ret = ret * finv[n] % p 
  return ret


def perm(n,r,p):
  if (r < 0) or (n < r):
    return 0
  return fac[n]*finv[n-r]%p

n = 5*pow(10,5) + 100
#MODは自分で入れよう！

fac = [-1]*(n+1); fac[0] = 1; fac[1] = 1 #階乗
finv = [-1]*(n+1); finv[0] = 1; finv[1] = 1 #階乗の逆元
inv = [-1]*(n+1); inv[0] = 0; inv[1] = 1 #逆元
for i in range(2,n+1):
  fac[i] = fac[i-1]*i%MOD
  inv[i] = MOD - inv[MOD%i]*(MOD//i)%MOD
  finv[i] = finv[i-1]*inv[i]%MOD

def main():
    N = int(input())
    if N == 1:
        print(2);exit()
    ans = 1
    for i in range(1,N+1):
        ans *= 2*i
        ans %= MOD
    # print(ans)

    x = N-2
    f = 0
    for i in range(x+1):
        val = N-i
        num = cmb(x+i,i,MOD) - cmb(x+i,i-1,MOD)
        # print(val,num)
        temp = val * num%MOD
        f += temp
        f %= MOD
    
    ans = (ans*f)%MOD
    print(ans)




if __name__ == '__main__':
    main()