from collections import defaultdict, deque
import sys, math, bisect, heapq, copy
from bisect import bisect_left, bisect_right
from typing import Generic, Iterable, Iterator, List, Tuple, TypeVar, Optional
from itertools import permutations

sys.setrecursionlimit(10**6)
 
def INT():      # 整数1つ
    return int(input())
def LINT():     # 整数複数個
    return map(int, input().split())
def HINT(N=0):    #整数N個(スペース区切り)(各値にプラスする値N)
    return [int(i)+N for i in input().split()]
Yes = lambda: print("Yes")
No = lambda: print("No")
INF = 10 ** 18
# = [None for _ in range()]
# = [[None for _ in range()] for _ in range()]
# = [input() for _ in range()]  ##文字列行列用
# = list(map(str, input().split())) #複数文字列用
# for i,v in enumerate(path)] ##iでインデックス、vで配列の要素を返す
# print(*li,sep="")
"""for _ in range(m):
    u,v=LINT()
    u-=1
    v-=1
    path[u].add(v)
    path[v].add(u)"""

n,m=LINT()
dp=[INF for _ in range(n)]
dp[0]=0
path=[set() for _ in range(n)]
rpath=[set() for _ in range(n)]
rdp=[INF for _ in range(n)]
rdp[-1]=0
for i in range(n):
    s=list(input())
    for j in range(m):
        if s[j]=="1":
            path[i].add(i+j+1)
            rpath[i+j+1].add(i)
            dp[i+j+1]=min(dp[i]+1,dp[i+j+1])
for i in reversed(range(n)):
    for j in rpath[i]:
        rdp[j]=min(rdp[i]+1,rdp[j])
ans=[]
for k in range(1,n-1):
    a=INF
    for i in range(max(0,k-m),k):
        for j in path[i]:
            if j<=k:
                continue
            a=min(a,dp[i]+rdp[j]+1)
    if a!=INF:
        ans.append(a)
    else:
        ans.append(-1)
print(*ans)