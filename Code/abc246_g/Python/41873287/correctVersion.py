N = int(input())
A = [0]+list(map(int,input().split()))
G = [[] for _ in range(N)]
for _ in range(N-1):
    u,v = map(int,input().split())
    u,v = u-1,v-1
    G[u].append(v)
    G[v].append(u)

order = []
par = [-1]*N
from collections import deque
dq = deque([0])
while dq:
    cur = dq.popleft()
    order.append(cur)
    for nxt in G[cur]:
        if par[cur] == nxt:
            continue
        par[nxt] = cur
        dq.append(nxt)

ok = 10**9
ng = -1

order.reverse()
cnt = [0]*N
while ok-ng>1:
    cen = (ok+ng)//2

    for cur in order:
        s = 0
        for nxt in G[cur]:
            if par[cur] == nxt:
                continue
            s += cnt[nxt]
        s = max(0,s-1)

        if cur == 0:
            if s < 1:
                ok = cen
            else:
                ng = cen
        else:
            cnt[cur] = s + int(A[cur] > cen)

print(ok)


