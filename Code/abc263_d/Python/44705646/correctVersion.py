from heapq import heappush, heappop, heapify
from collections import defaultdict, deque, Counter
from itertools import permutations, combinations, accumulate
from math import gcd, sqrt, factorial, ceil
from bisect import bisect_left, bisect_right
import sys
from decimal import Decimal, ROUND_HALF_UP, getcontext

sys.setrecursionlimit(10**6)
getcontext().rounding = ROUND_HALF_UP
import sys
input = sys.stdin.readline

#X=Decimal(str(X)).quantize(Decimal('1e'+str(i)))で使える。'1e'+str(i)は桁数を指定
#X = Decimal(str(X)).quantize(Decimal('1.' + '0' * i))これは小数点以下ででiは桁数
# ROUND_HALF_UPで四捨五入
# ROUND_HALF_EVENは、丸め込み

#pypy用
#from pypyjit import set_param
#set_param('max_unroll_recursion=0')
#import numpy as np
#from math import isqrt, prod
#from functools import cache
#@cache

N,L,R=map(int,input().split())
A=list(map(int,input().split()))
ldp=[0]*(N+1)
rdp=[0]*(N+1)
for i in range(N):ldp[i+1]=min(ldp[i]+A[i],L*(i+1))
A.reverse()
for i in range(N):rdp[i+1]=min(rdp[i]+A[i],R*(i+1))
rdp.reverse()
ans=float('inf')
for i in range(N+1):ans=min(ans,ldp[i]+rdp[i])
print(ans)
