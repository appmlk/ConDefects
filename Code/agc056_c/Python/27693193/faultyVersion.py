import sys
input = lambda: sys.stdin.readline().rstrip()
from heapq import heapify, heappush as hpush, heappop as hpop
def dijkstra(n, E, i0=0):
    kk = 18
    mm = (1 << kk) - 1
    h = [i0]
    D = [-1] * n
    done = [0] * n
    D[i0] = 0
    while h:
        x = hpop(h)
        d, i = x >> kk, x & mm
        if done[i]: continue
        done[i] = 1
        for j, w in E[i]:
            nd = d + w
            if D[j] < 0 or D[j] > nd:
                if done[j] == 0:
                    hpush(h, (nd << kk) + j)
                    D[j] = nd
    return D

N, M = map(int, input().split())
X = [[] for _ in range(N + 1)]
for i in range(N):
    X[i].append((i + 1, 1))
for i in range(N):
    X[i+1].append((i, 1))

for _ in range(M):
    l, r = map(int, input().split())
    l -= 1
    d = 0
    X[l].append((r, d))
    X[r].append((l, d))

D = dijkstra(N + 1, X)
A = [(2 - D[i+1] + D[i]) // 2 for i in range(N)]
print("".join(map(str, A)))