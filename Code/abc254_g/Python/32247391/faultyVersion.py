import sys
input = sys.stdin.readline
from bisect import bisect_left

class Compress:
    def __init__(self, vs):
        self.xs = list(set(vs))
        self.xs.sort()

    def compress(self, x):
        return bisect_left(self.xs, x)

    def decompress(self, i):
        return self.xs[i]

    def size(self):
        return len(self.xs)

N, M, Q = map(int, input().split())
elevators = [[] for _ in range(N)]
xs = []
for _ in range(M):
    A, B, C = map(int, input().split())
    A -= 1
    elevators[A].append((B, C))
    xs.append(B)
    xs.append(C)
queries = []
for _ in range(Q):
    X, Y, Z, W = map(int, input().split())
    if Y > W:
        X, Z = Z, X
        Y, W = W, Y
    queries.append((X-1, Y, Z-1, W))
    xs.append(Y)
    xs.append(W)
comp = Compress(xs)
for a in range(N):
    elevators[a].sort()
    res = []
    for b, c in elevators[a]:
        b = comp.compress(b)
        c = comp.compress(c)
        if not res or res[-1][1] < b:
            res.append((b, c))
        else:
            b2, c2 = res.pop()
            c2 = max(c, c2)
            res.append((b2, c2))
    elevators[a] = res

L = 20
n = comp.size()
table = [[0] * n for _ in range(L)]
for a in range(N):
    for b, c in elevators[a]:
        table[0][b] = max(table[0][b], c)
m = 0
for i in range(n):
    m = max(m, i, table[0][i])
    table[0][i] = m

for k in range(L-1):
    for i in range(n):
        table[k+1][i] = table[k][table[k][i]]

for x, y, z, w in queries:
    ans = w - y
    y = comp.compress(y)
    w = comp.compress(w)

    k = bisect_left(elevators[x], (y+1, -1)) - 1
    if k >= 0 and elevators[x][k][0] <= y <= elevators[x][k][1]:
        y = elevators[x][k][1]

    l = bisect_left(elevators[z], (w+1, -1)) - 1
    if l >= 0 and elevators[z][l][0] <= w <= elevators[z][l][1]:
        w = elevators[z][l][0]

    if y >= w:
        if x != z:
            ans += 1
        print(ans)
        continue

    for k in range(L)[::-1]:
        if table[k][y] < w:
            y = table[k][y]
            ans += 2**k

    if table[0][y] >= w:
        ans += 1
        print(ans)
    else:
        print(-1)