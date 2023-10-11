import math
import heapq
# import bisect
import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)
# n,x,y = map(int, input().split())
# a = list(map(int, input().split()))
n = int(input())
cld = [[] for i in range(n)]
s = [0]*n
g = [0]*n
md = [] 
inf = 1<<63
mx = 0
for i in range(n-1):
    p,t,S,G = map(int, input().split())
    mx = max(mx,S)
    if S==0:
        S=inf
    s[i+1] = S
    g[i+1] = G
    cld[p-1].append(i+1)
    if t==2:
        md.append(i+1)
N = 1<<len(md)
dp = [0]*N
qs = [None]*N
def rep(q,p):
    while len(q)>0:
        if q[0][0]==inf:
            return p
        if q[0][0]<=p:
            id=heapq.heappop(q)[1]
            p+=g[id]
            for c in cld[id]:
                heapq.heappush(q,[s[c],c])
        else:
            break
    return p
import copy
def sim(p,t,m):
    for j,i in enumerate(qs[t]):
        if m==i[1]:
            qq=copy.deepcopy(qs[t])
            p*=g[m]
            # del qq[j]
            for c in cld[m]:
                heapq.heappush(qq,[s[c],c])
            return True,rep(qq,p),qq
    return False,p,qs[t]

from collections import deque
q=deque()
q.append(0)
bq= [[s[c],c] for c in cld[0]]
heapq.heapify(bq)
dp[0] = rep(bq,1)
if mx<=dp[0]:
    print("Yes")
    exit()
qs[0]=bq
while len(q)>0:
    t=q.popleft()
    for i in range(len(md)):
        if (t>>i)&1==0:
            f,x,y=sim(dp[t],t,md[i])
            # print(t,md[i],x,y)
            if not f:
                continue
            if mx<=x:
                print("Yes")
                exit()
            nx = t|(1<<i)
            if dp[nx]<x:
                qs[nx]=y
                if dp[nx]==0:
                    q.append(nx)
                dp[nx]=x
print("No")