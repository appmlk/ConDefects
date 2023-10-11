import sys,random,bisect
from collections import deque,defaultdict
from heapq import heapify,heappop,heappush
from itertools import permutations
from math import gcd

input = lambda :sys.stdin.readline().rstrip()
mi = lambda :map(int,input().split())
li = lambda :list(mi())

def cmb(n, r, mod):
    if ( r<0 or r>n ):
        return 0
    return (g1[n] * g2[r] % mod) * g2[n-r] % mod

mod = 998244353
N = 2*10**5
g1 = [1]*(N+1)
g2 = [1]*(N+1)
inverse = [1]*(N+1)

for i in range( 2, N + 1 ):
    g1[i]=( ( g1[i-1] * i ) % mod )
    inverse[i]=( ( -inverse[mod % i] * (mod//i) ) % mod )
    g2[i]=( (g2[i-1] * inverse[i]) % mod )
inverse[0]=0

N = int(input())
P = li()
K = P.count(-1)

edge = [[] for v in range(N)]
for i in range(N):
    if P[i]!=-1:
        edge[i].append(P[i]-1)
        edge[P[i]-1].append(i)

minus = 0
visit = [False] * (N)
dp = [0] * (N+1)
dp[0] = 1
for root in range(N):
    if visit[root]:
        continue

    yet = 0
    visit[root] = True
    stack = [root]
    n = 0
    while stack:
        v = stack.pop()
        if P[v]==-1:
            yet += 1
        n += 1
        for nv in edge[v]:
            if not visit[nv]:
                visit[nv] = True
                stack.append(nv)
    
    if not yet:
        minus += pow(N,K,mod)
        minus %= mod
    else:
        #minus += n * pow(N,K-1,mod)
        #minus %= mod

        ndp = [0] * (N+1)
        for i in range(N+1):
            if not dp[i]:
                continue
            ndp[i+1] += dp[i] * n
            ndp[i+1] %= mod
            ndp[i] += dp[i]
            ndp[i] %= mod
        dp = ndp

for i in range(1,K+1):
    minus += dp[i] * g1[i-1] * pow(N,K-i,mod) % mod
    minus %= mod

res = minus
print(res % mod)

