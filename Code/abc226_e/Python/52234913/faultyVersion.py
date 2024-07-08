# Idea: For each connected component, there need to be an equal number of edges and vertices.
# In this case, there are 2 ways to orient the edges per component. Otherwise the answer is 0.

from atcoder.dsu import DSU
from collections import defaultdict

n, m = map(int, input().split())

edges_in_comp = defaultdict(int)
one_per_edge = []
comps = n
dsu = DSU(n+1)

for _ in range(m):
    u, v = map(int, input().split())
    one_per_edge.append(u)
    if not dsu.same(u, v):
        dsu.merge(u, v)
        comps -= 1

for v in one_per_edge:
    edges_in_comp[dsu.leader(v)] += 1

for c in dsu.groups():
    if dsu.leader(c[0]) != len(c):
        print(0)
        break

else:
    print(pow(2, comps, 998244353))