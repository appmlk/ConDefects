import codecs
import copy
import heapq as hq
import itertools
import math
import pprint as pp
import random
import statistics
import time
from bisect import bisect_left, bisect_right, insort
from collections import Counter
from collections import defaultdict as dd
from collections import deque
from functools import lru_cache, reduce
from sys import setrecursionlimit, stdin
from typing import Generic, Iterable, Iterator, List, TypeVar, Union

N=int(input())
P=list(map(int,input().split()))

dp=[-float("inf")]*N
dp[0]=P[0]

for i in range(1,N):
    dpnxt=[-float("inf")]*N
    for j in range(N):
        if j==0:
            dpnxt[j]=max(dp[j],dpnxt[i])
        else:
            dpnxt[j]=max(dp[j-1]*0.9+P[i],dp[j])
    dp=dpnxt
    

num=1
ans=-float("inf")
for i in range(N):
    myans=dp[i]/num-1200/((i+1)**0.5)
    
    ans=max(ans,myans)
    num*=0.9
    num+=1
print(ans)

