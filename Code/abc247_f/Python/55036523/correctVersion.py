
N = int(input())
P = list(map(int, input().split()))
Q = list(map(int, input().split()))


f = [0]*(200001)
f[1] = 2
f[2] = 3
mod = 998244353
for i in range(3,200001):
    f[i] = (f[i-1] + f[i-2])%mod

dp = [0]*(200001)
dp[1] = 1
dp[2] = 3
dp[3] = 4

for i in range(4,200001):
    dp[i] = (f[i-3] + f[i-1])%mod
edge = [-1] * N
for i,j in zip(P,Q):
    i-=1;j-=1
    edge[i] = j
#print(edge)
visited = [-1] * N
from collections import *
ans = 1 
for i in range(N):
    if visited[i] == 1:
        continue
    visited[i] = 1
    que = deque([i])
    cnt = 1
    while que:
        now = que.popleft()

        if visited[edge[now]] == -1:
            visited[edge[now]] = 1
            cnt += 1
            que.append(edge[now])
       # print(cnt)
   # print(cnt)
    ans *= dp[cnt]%mod
    ans %= mod
print(ans)
    
