#############################################################

import sys
sys.setrecursionlimit(10**7)

from heapq import heappop,heappush
from collections import deque,defaultdict,Counter
from bisect import bisect_left, bisect_right
from itertools import product,combinations,permutations

ipt = sys.stdin.readline

def iin():
    return int(ipt())
def lmin():
    return list(map(int,ipt().split()))

MOD = 998244353
#############################################################

N = iin()
A = lmin()
G = [[] for _ in range(N)]
for _ in range(N-1):
    u,v = lmin()
    u,v = u-1,v-1
    G[u].append(v)
    G[v].append(u)

val = [1]*(N+1)
pval = [1]*(N+1)
cum_val = [1]*(N+1)

par = [-1]*N
memo_cum_val = [0]*N
memo_val = [0]*N
memo_pval = [0]*N
init_pval = [0]*N

cnt = 0
st = [(0,0)]
ans = 0

while st:
    t,cur = st.pop()
    a = A[cur]

    if t == 0:

        st.append((2,cur))
        ans += val[a]*pval[a]%MOD
        ans %= MOD
        memo_val[cur] = val[a]
        memo_pval[cur] = pval[a]
        memo_cum_val[cur] = cum_val[a]

        pval[a] = (val[a]*pval[a]+1)%MOD
        val[a] = 1
        cum_val[a] = 1
        for nxt in reversed(G[cur]):
            if nxt == par[cur]:
                continue
            par[nxt] = cur
            st.append((1,cur))
            st.append((0,nxt))

    elif t == 1:
        
        cum_val[a] = cum_val[a] * val[a] % MOD
        pval[a] = (memo_pval[cur] * memo_val[cur] % MOD * cum_val[a] %MOD + 1) % MOD
        val[a] = 1

    elif t == 2:

        val[a] = (memo_val[cur]*(cum_val[a]+1))%MOD
        pval[a] = memo_pval[cur]
        cum_val[a] = memo_cum_val[cur]

print(ans)