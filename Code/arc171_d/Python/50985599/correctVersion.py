P,B,N,M = map(int,input().split())
LR = [tuple(map(int,input().split())) for _ in range(M)]

if P >= N+1:
    exit(print('Yes'))

INF = 10**18
N += 1
_dp = [INF] * (1<<N)
_dp[0] = 0
for b in range(1,1<<N):
    for l,r in LR:
        l -= 1
        if b&(1<<l) and b&(1<<r):
            break
    else:
        _dp[b] = 1
dp = _dp[:]
for b in range(1<<N):
    a = (b-1) & b
    while a > 0:
        dp[b] = min(dp[b], _dp[a] + dp[a^b])
        a = (a-1) & b
print('No' if dp[-1] > P else 'Yes')