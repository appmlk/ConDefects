
import sys,random,bisect
from collections import deque,defaultdict
from heapq import heapify,heappop,heappush
from itertools import permutations
from math import gcd,log

input = lambda :sys.stdin.readline().rstrip()
mi = lambda :map(int,input().split())
li = lambda :list(mi())

N,K,S = mi()
A = li()

cum = [0] * (N+1)
for i in range(N):
    cum[i+1] = A[i] + cum[i]

nxt_edge = [-1] * (N+1)
for i in range(N):
    r = bisect.bisect_left(cum,S+cum[i])
    if r!=N+1:
        nxt_edge[i] = r
        

INF = 10**15
def cond(d):
    dp = [INF] * (N+1)
    dp[0] = 0
    for i in range(N):
        dp[i+1] = min(dp[i+1],dp[i]+d)
        if nxt_edge[i]!=-1:
            r = nxt_edge[i]
            
            dp[r] = min(dp[r],dp[i]+(d+1)*(r-i-1))
    
    dp_mini_edge = [INF] * (N+1)
    dp_mini_edge[0] = 0
    dp_maxi_edge = [0] * (N+1)
    pre = [-1] * (N+1)
    for i in range(N):
        if dp[i+1] == dp[i]+d:
            pre[i+1] = i
            dp_mini_edge[i+1] = min(dp_mini_edge[i+1],dp_mini_edge[i])
            dp_maxi_edge[i+1] = max(dp_maxi_edge[i+1],dp_maxi_edge[i])
        if nxt_edge[i]!=-1:
            r = nxt_edge[i]
            if dp[r] == dp[i] + (d+1)*(r-i-1):
                pre[r] = i
                
                dp_mini_edge[r] = min(dp_mini_edge[r],dp_mini_edge[i]+1)
                dp_maxi_edge[r] = max(dp_maxi_edge[r],dp_maxi_edge[i]+1)
    
    


    
    check_val = dp[N] - d*N + dp_mini_edge[N] * d
    for i in range(dp_mini_edge[N],min(K,dp_maxi_edge[N])+1)[::-1]:
        if dp[N]-d*N + i * d <= N-K:
            return i
    return -1

res = 0

left,right = -2,2*N+1
while right-left>1:
    mid = (left+right)//2
    check = cond(mid)
    res = max(res,check)
    if check == -1:
        right = mid
    else:
        left = mid

print(res)


