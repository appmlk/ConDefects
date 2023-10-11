import itertools
import bisect


N, Q, X = map(int, input().split())
W = list(map(int, input().split()))

u = sum(W)
R = list(itertools.accumulate([0] + W + W))

B = []
n = X // u
b = (X // u) * u
need = X - b

X = []
for i in range(1, N+1):
    add = n * N
    ok = R[i-1] + need
    idx = bisect.bisect_left(R, ok)
    add += idx - (i-1)
    B.append(add)
    X.append(idx % N)



dp = [X]
now = X
for _ in range(40):
    nex = []
    for i in range(N):
        nex.append(now[now[i]])
    now = nex
    dp.append(now)

for _ in range(Q):
    K = int(input())
    K -= 1
    
    now = 0
    for r in range(41):
        if (K>>r) & 1:
            now = dp[r][now]
    
    print(B[now])