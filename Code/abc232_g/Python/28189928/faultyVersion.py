from bisect import bisect_left as bl
from bisect import bisect_right as br
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
        # di = hpop(h)
        # d, i = unpack("d", pack("q", (di | mm) - mm))[0], di & mm
        if done[i]: continue
        done[i] = 1
        for j, w in E[i]:
            nd = d + w
            if D[j] < 0 or D[j] > nd:
                if done[j] == 0:
                    hpush(h, (nd << kk) + j)
                    # hpush(h, (unpack("q", pack("d", nd))[0] | mm) - (mm - j))
                    D[j] = nd
    return D

N, M = map(int, input().split())
A = [int(a) for a in input().split()]
B = [int(a) for a in input().split()]
AB = sorted([(a, b, i) for i, (a, b) in enumerate(zip(A, B))], key = lambda x: x[1])
A = [ab[0] for ab in AB]
B = [ab[1] for ab in AB]
for j, (a, b, i) in enumerate(AB):
    if i == 0:
        s = j
    elif i == N - 1:
        t = j
B.append(1 << 60)
b0 = B[0]

X = [[] for _ in range(2 * N + 1)]
for i in range(N):
    X[i+N].append((i + 1 + N, B[i+1] - B[i]))
    X[i+N].append((i, 0))

for i, a in enumerate(A):
    j = bl(B, M - a)
    X[i].append((N, a + b0))
    X[i].append((j + N, a + B[j] - M))

print(dijkstra(N * 2 + 1, X, s)[t])
