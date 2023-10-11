def ip():return int(input())
def mp():return map(int, input().split())
def lmp():return list(map(int, input().split()))
# ABC287 C 466 - Path Graph?
# N 頂点 M 辺の単純無向グラフが与えられます。
# 頂点には 1,2,…,N の番号が、辺には 1,2,…,M の番号が付けられています。
# 辺 i(i=1,2,…,M) は頂点 ui, vi を結んでいます。
# このグラフがパスグラフであるか判定してください。
# 頂点に 1,2,…,N の番号が付けられた N 頂点のグラフがパスグラフであるとは、(1,2,…,N) を並べ変えて得られる数列 (v1,v2,…,vN) であって、以下の条件を満たすものが存在することをいいます。
# ・全ての i=1,2,…,N-1 に対して、頂点 vi,v_{i+1} を結ぶ辺が存在する
# ・整数 i, j が 1 ≤ i, j ≤ N, |i-j| ≥ 2 を満たすならば、頂点 vi, vj を結ぶ辺は存在しない
# ・2 ≤ N ≤ 2×10^5
# ・0 ≤ M ≤ 2×10^5
N, M = mp()
p = [-1] * (N+1)
def root(x):
    if p[x] < 0: return x
    p[x] = root(p[x])
    return p[x]
def unite(x, y):
    x = root(x)
    y = root(y)
    if x == y:return
    p[x] += p[y]
    p[y] = x
def same(x, y):
    return root(x) == root(y)
def size(x):
    return -p[round(x)]
deg = [0] * N
for _ in range(M):
    u, v = mp()
    u -= 1
    v -= 1
    deg[v] += 1
    deg[u] += 1
    unite(u, v)
if deg.count(1) == 2 and deg.count(2) == N-2 and size(0) == N:
    print('Yes')
else:
    print('No')
