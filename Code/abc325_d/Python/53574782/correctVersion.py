N = int(input())
inout = [tuple(map(int, input().split())) for _ in range(N)]
inout.sort()
import heapq
from collections import defaultdict
D = defaultdict(list)
for t, d in inout:
    D[t].append(t + d)

INF = 10 ** 20
event = sorted(D.keys())
event.append(INF)
ans = 0
now = 1
cnt = 0
hq = []
while now < INF:
    if now in D:
        cnt += 1
        for t in D[now]:
            heapq.heappush(hq, t)
    while hq:
        t = heapq.heappop(hq)
        if now <= t:
            ans += 1
            now += 1
            break
    else:
        now = event[cnt]
print(ans)