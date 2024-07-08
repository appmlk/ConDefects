import sys

def root(x):
    if parents[x] < 0:
        return x
    else:
        parents[x] = root(parents[x])
        return parents[x]

def unite(x, y):
    x = root(x)
    y = root(y)
    if x == y:
        return
    if parents[x] > parents[y]:
        x, y = y, x
    parents[x] += parents[y]
    parents[y] = x

def same(x, y):
    return root(x) == root(y)

sys.setrecursionlimit(10**9)
N, M = map(int, input().split())
parents = [-1] * N
G = [[] for _ in range(N)]
E = []
for _ in range(M):
    u, v = map(int, input().split())
    u, v = u - 1, v - 1
    E.append((u, v))
K = int(input())
X = set(map(lambda x: int(x) - 1, input().split()))

for i in range(M):
    if i in X:
        continue
    u, v = E[i]
    unite(u, v)

V = {root(i): 0 for i in range(N)}
for i in range(M):
    if i not in X:
        continue
    u, v = E[i]
    u, v = root(u), root(v)
    V[u] += 1
    V[v] += 1

odd_cnt = 0
for key, val in V.items():
    odd_cnt += val % 2

if odd_cnt <= 2:
    print("Yes")
else:
    print("No")