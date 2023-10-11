def ip():return int(input())
def mp():return map(int, input().split())
def lmp():return list(map(int, input().split()))
# 20230610 ABC305 E 1158 - Art Gallery on Graph PyPyで提出
N, M, K = mp()
edge = [[] for _ in range(N)]
for _ in range(M):
    a, b = mp()
    a -= 1
    b -= 1
    edge[a].append(b)
    edge[b].append(a)
from heapq import heappop, heappush, heapify
H = [-1] * N
for _ in range(K):
    p, h = mp()
    p -= 1
    H[p] = h
que = [(-H[i], i) for i in range(N)]
heapify(que)
while que:
    h, v = heappop(que)
    h *= -1
    if h < H[v] or h == 0:
        continue
    for to in edge[v]:
        if H[to] >= h-1:
            continue
        H[to] = h - 1
        heappush(que, (-H[to], to))
ans = []
for i in range(N):
    if H[i] != -1:
        ans.append(i+1)
print(len(ans))
print(*ans)