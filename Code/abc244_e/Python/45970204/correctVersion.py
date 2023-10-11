n,m,k,s,t,x = map(int, input().split())
uvl = [list(map(int, input().split())) for _ in range(m)]
mod = 998244353
from collections import defaultdict
gd = defaultdict(set)
for u,v in uvl:
    gd[u-1].add(v-1)
    gd[v-1].add(u-1)

dp =  [[[0] * 2 for i in range(n)] for j in range(k+1)]
dp[0][s-1][0] = 1
for i in range(1,k+1):
    for u, vs in gd.items():
        for v in vs: 
            if v == x-1:
                dp[i][v][0] += dp[i-1][u][1]%mod
                dp[i][v][1] += dp[i-1][u][0]%mod
            else:
                dp[i][v][0] += dp[i-1][u][0]%mod
                dp[i][v][1] += dp[i-1][u][1]%mod
print(dp[k][t-1][0]%mod)       
