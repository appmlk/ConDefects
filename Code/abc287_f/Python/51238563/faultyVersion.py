import collections,sys,math,functools,operator,itertools,bisect,heapq,decimal,string,time,random
#sys.setrecursionlimit(10**9)
#sys.set_int_max_str_digits(0)
#input = sys.stdin.readline
mod = 998344353
n = int(input())
#https://qiita.com/Kiri8128/items/cbaa021dbcb07b5fdb92#非再帰-bfs
edge = [[] for i in range(n)]
for i in range(n-1):
    u,v = map(int,input().split())
    u-=1
    v-=1
    edge[u].append(v)
    edge[v].append(u)
plist = [-1 for i in range(n)]
d = collections.deque()
r = []
d.append(0)
while d:
    now = d.popleft()
    r.append(now)
    for i in edge[now]:
        plist[i] = now
        d.append(i)
        edge[i].remove(now)
dp = [[[0 for j in range(2)] for gwfingrwin in range(2)] for i in range(n)]
for i in range(n):
    dp[i][1][1] = 1
    dp[i][0][0] = 1
for i in r[::-1]:
    for k in range(len(edge[i])):
        l = edge[i][k]
        jdp = [[0 for j in range(len(dp[i][0]) + len(dp[l][0]) - 1)] for frfi in range(2)]
        for x in range(len(dp[l][0])):
            for y in range(len(dp[i][0])):
                jdp[0][x+y] += dp[i][0][y] * dp[l][0][x]
                jdp[0][x+y] += dp[i][0][y] * dp[l][1][x]
                jdp[1][x+y] += dp[i][1][y] * dp[l][0][x]
                if x+y != 0:
                    jdp[1][x+y-1] += dp[i][1][y] * dp[l][1][x]
                    jdp[1][x+y-1] %= mod
                jdp[0][x+y] %= mod
                jdp[1][x+y] %= mod
        dp[i][0] = jdp[0][:]
        dp[i][1] = jdp[1][:]




for i in range(1,n+1):
    print((dp[0][0][i] + dp[0][1][i]) % mod)



