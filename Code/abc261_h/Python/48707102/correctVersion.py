from heapq import heappop, heappush

n, m, s = map(int, input().split())
s -= 1
rev_g = [[] for _ in range(n)]
deg = [0] * n
for _ in range(m):
    a, b, c = map(int, input().split())
    a -= 1
    b -= 1
    rev_g[b].append((a, c))
    deg[a] += 1

inf = 1 << 48
que_t = []
que_a = []
dp_t = [inf] * n
dp_a = [inf] * n
for u in range(n):
    if deg[u] == 0:
        que_t.append((0, u))
        que_a.append((0, u))
        dp_t[u] = 0
        dp_a[u] = 0

dp_m = [-1] * n
while que_t or que_a:
    if que_a:
        d, u = heappop(que_a)
        for v, c in rev_g[u]:
            val = d + c
            if dp_t[v] > val:
                dp_t[v] = val
                heappush(que_t, (val, v))
    else:
        d, u = heappop(que_t)
        if dp_t[u] < d:
            continue
        for v, c in rev_g[u]:
            val = max(dp_m[v], d + c)
            dp_m[v] = val
            deg[v] -= 1
            if deg[v] == 0:
                dp_a[v] = val
                heappush(que_a, (val, v))
ans = dp_t[s]
if ans == inf:
    ans = 'INFINITY'
print(ans)
