from heapq import *

n = int(input())
p = list(map(int, input().split()))
ans = 0
q = []
for v in p:
    if q and q[0] < v:
        ans += v - heappop(q)
        heappush(q, v)
    heappush(q, v)
print(ans)