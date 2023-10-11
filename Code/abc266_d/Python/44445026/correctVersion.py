import bisect,collections,itertools,math,functools,heapq
import sys
# sys.setrecursionlimit(10**6)
def I(): return int(sys.stdin.readline().rstrip())
def LI(): return list(map(int,sys.stdin.readline().rstrip().split()))
def LF(): return list(map(float,sys.stdin.readline().rstrip().split()))
def SI(): return sys.stdin.readline().rstrip()
def LS(): return list(sys.stdin.readline().rstrip().split())

N=I()
obj = collections.defaultdict(list)
arr = [LI() for _ in range(N)]
mt = 0
for t,x,a in arr:
    obj[t].append((x,a))
    mt = max(mt, t)
dp = [[-1]*5 for _ in range(mt+1)]
dp[0][0]=0
for i in range(1,mt+1):
    for j in range(5):
        if dp[i-1][j] >= 0:
            dp[i][j] = max(dp[i][j], dp[i-1][j])
            if j+1 < 5:
                dp[i][j+1] = max(dp[i][j+1], dp[i-1][j])
            if j-1 >= 0:
                dp[i][j-1] = max(dp[i][j-1], dp[i-1][j])
    for x,a in obj[i]:
        if x-1 >= 0 and dp[i-1][x-1] >= 0:
            dp[i][x] = max(dp[i][x], dp[i-1][x-1]+a)
        if dp[i-1][x] >= 0:
            dp[i][x] = max(dp[i][x], dp[i-1][x]+a)
        if x+1 < 5 and dp[i-1][x+1] >= 0:
            dp[i][x] = max(dp[i][x], dp[i-1][x+1]+a)

print(max(dp[-1]))
