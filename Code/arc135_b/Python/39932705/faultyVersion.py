
def main():
    from math import sqrt,sin,cos,tan,ceil,radians,floor,gcd,exp,log,log10,log2,factorial,fsum
    from heapq import heapify, heappop, heappush
    from bisect import bisect_left, bisect_right
    from copy import deepcopy
    import copy
    import random
    from collections import deque,Counter,defaultdict
    from itertools import permutations,combinations
    from decimal import Decimal,ROUND_HALF_UP
    #tmp = Decimal(mid).quantize(Decimal('0'), rounding=ROUND_HALF_UP)
    from functools import lru_cache, reduce
    #@lru_cache(maxsize=None)
    from operator import add,sub,mul,xor,and_,or_,itemgetter
    INF = 10**18
    mod1 = 10**9+7
    mod2 = 998244353
    
    #DecimalならPython
    
    
    '''
    
    
    
    '''
    
    N = int(input())
    S = list(map(int, input().split()))
    
    X = [0]*(N+2)
    
    # X[i+3] = X[i]+S[i+1]-S[i]
    for i in range(N-1):
        X[i+3] = X[i]+S[i+1]-S[i]
    
    #マイナスになってるやつを調べる
    a = 0
    b = 0
    c = 0
    for i in range(N+2):
        if i%3 == 0:
            a = min(a, X[i])
        elif i%3 == 1:
            b = min(b,X[i])
        else:
            c = min(c,X[i])
    
    for i in range(N+2):
        if i%3 == 0:
            X[i] += -a
        elif i%3 == 1:
            X[i] += -b
        else:
            X[i] += -c
    
    if X[0]+X[1]+X[2]<=S[0]:
        ab = S[0]-X[1]-X[2]
        for i in range(0,N+2,3):
            X[i] += ab
        
        print('Yes')
        print(*X)
    else:
        print('No')
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
if __name__ == '__main__':
    main()