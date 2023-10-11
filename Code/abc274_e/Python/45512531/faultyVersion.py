import bisect, collections, copy, heapq, itertools, math, sys
sys.setrecursionlimit(10**7)
input = sys.stdin.readline
# P = 10**9+7
P = 998244353

N, M = map(int, input().split())
S = [list(map(int, input().split())) for _ in range(N+M)]

dp = [[10**20]*(N+M) for _ in range(1<<(N+M))]
q = collections.deque([])
for i in range(N+M):
    x, y = S[i]
    dp[1<<i][i] = (x**2+y**2)**0.5
    q.append((1<<i, i))

ans = 10**20
while q:
    bit, u = q.popleft()
    s = 1
    for i in range(N, N+M):
        if bit&(1<<i):
            s *= 2
    xu, yu = S[u]
    d = dp[bit][u]
    for v in range(N+M):
        if bit&(1<<v):
            continue
        xv, yv = S[v]
        if dp[bit+(1<<v)][v] == 10**20:
            q.append((bit+(1<<v), v))
        if d + ((xu-xv)**2+(yu-yv)**2)**0.5 < dp[bit+(1<<v)][v]:
            dp[bit+(1<<v)][v] = d + ((xu-xv)**2+(yu-yv)**2)**0.5/s
    if bit&((1<<N)-1) == (1<<N)-1 and d + (xu**2+yu**2)**0.5/s < ans:
        ans = d + (xu**2+yu**2)**0.5/s
print(ans)