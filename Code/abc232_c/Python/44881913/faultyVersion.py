import copy
import itertools

n, m = map(int, input().split())

g1 = [[0 for _ in range(n)] for _ in range(n)]
for _ in range(m):
    u, v = map(int, input().split())
    g1[u - 1][v - 1] = 1

g2 = [[0 for _ in range(n)] for _ in range(n)]
for _ in range(m):
    u, v = map(int, input().split())
    g2[u - 1][v - 1] = 1

isok = False
for p in itertools.permutations(range(n), n):
    g3 = copy.deepcopy(g2)
    for u in range(n):
        for v in range(n):
            g3[p[u]][p[v]] = g2[u][v]
    if g1 == g3:
        isok |= True

print("Yes" if isok else "No")