import sys

# sys.setrecursionlimit(200005)
# sys.set_int_max_str_digits(200005)
int1 = lambda x: int(x)-1
pDB = lambda *x: print(*x, end="\n", file=sys.stderr)
p2D = lambda x: print(*x, sep="\n", end="\n\n", file=sys.stderr)
def II(): return int(sys.stdin.readline())
def LI(): return list(map(int, sys.stdin.readline().split()))
def LLI(rows_number): return [LI() for _ in range(rows_number)]
def LI1(): return list(map(int1, sys.stdin.readline().split()))
def LLI1(rows_number): return [LI1() for _ in range(rows_number)]
def SI(): return sys.stdin.readline().rstrip()

dij = [(0, 1), (-1, 0), (0, -1), (1, 0)]
# dij = [(0, 1), (-1, 0), (0, -1), (1, 0), (1, 1), (1, -1), (-1, 1), (-1, -1)]
inf = -1-(-1 << 63)
md = 10**9+7
# md = 998244353

from math import gcd

# 各強連結成分をリストにしてトポロジカル順に返す
def SCC(to, ot):
    n = len(to)
    # トポロジカルソート
    fin = [-1]*n
    topo = []
    for u in range(n):
        if fin[u] != -1: continue
        stack = [u]
        while stack:
            u = stack[-1]
            if fin[u] == -1:
                fin[u] = 0
                for v in to[u]:
                    if fin[v] != -1: continue
                    stack.append(v)
            else:
                stack.pop()
                if fin[u] == 0:
                    fin[u] = 1
                    topo.append(u)
    # 逆辺でdfs
    res = []
    while topo:
        u = topo.pop()
        if fin[u] != 1: continue
        fin[u] = 2
        cur = [u]
        i = 0
        while i < len(cur):
            u = cur[i]
            for v in ot[u]:
                if fin[v] == 2: continue
                fin[v] = 2
                cur.append(v)
            i += 1
        res.append(cur)

    return res

def solve():
    def dfs(root=0):
        st = [root]
        while st:
            u = st.pop()
            for v in to[u]:
                if dead[v]: continue
                if depth[v] == -1:
                    depth[v] = depth[u]+1
                    st.append(v)
                else:
                    back.append((u, v))

    n, m = LI()
    to = [[] for _ in range(n)]
    ot = [[] for _ in range(n)]
    for _ in range(m):
        u, v = LI1()
        to[u].append(v)
        ot[v].append(u)
    gg = SCC(to, ot)

    dead = [1]*n
    for g in gg:
        if 0 in g:
            for u in g: dead[u] = 0
            break

    depth = [-1]*n
    depth[0] = 0
    back = []
    dfs()
    g = 0
    for u, v in back:
        g = gcd(g, abs(depth[u]+1-depth[v]))

    if g==0:
        print("No")
        return 

    while g%5 == 0: g //= 5
    print("Yes" if g == g & -g else "No")

for _ in range(II()): solve()
