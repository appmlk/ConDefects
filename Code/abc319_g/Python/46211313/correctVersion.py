import sys
readline = sys.stdin.readline

n,m = map(int,readline().split())
g = [[] for _ in range(n)]
for _ in range(m):
    a,b = map(int,readline().split())
    g[a-1].append(b-1)
    g[b-1].append(a-1)

dist = [-1]*n
dp = [0]*n
dist[0] = 0
dp[0] = 1


MOD = 998244353
num = 1 # dist = d-1 となる点の個数
val = 1 # dist = d-1 となる点への最短路の個数の合計
for d in range(1,1000):
    newnum = newval = 0
    for i in range(n):
        if dist[i] != -1: continue
        nn = num
        vv = val
        for v in g[i]:
            if dist[v] == d-1:
                nn -= 1
                vv -= dp[v]
        if nn:
            dist[i] = d
            dp[i] = vv%MOD
            newnum += 1
            newval += vv

    if newnum == 0:
        break
    num = newnum
    val = newval%MOD

#print(dist)
#print(dp)

print(-1 if dist[-1] == -1 else dp[-1])

        
















