import sys

# sys.setrecursionlimit(200005)
int1 = lambda x: int(x)-1
pDB = lambda *x: print(*x, end="\n", file=sys.stderr)
p2D = lambda x: print(*x, sep="\n", end="\n\n", file=sys.stderr)
def II(): return int(sys.stdin.readline())
def LI(): return list(map(int, sys.stdin.readline().split()))
def LLI(rows_number): return [LI() for _ in range(rows_number)]
def LI1(): return list(map(int1, sys.stdin.readline().split()))
def LLI1(rows_number): return [LI1() for _ in range(rows_number)]
def SI(): return sys.stdin.readline().rstrip()
# dij = [(0, 1), (-1, 0), (0, -1), (1, 0)]
# dij = [(0, 1), (-1, 0), (0, -1), (1, 0), (1, 1), (1, -1), (-1, 1), (-1, -1)]
inf = 18446744073709551615
# inf = 4294967295
md = 10**9+7
# md = 998244353

def two(i0, j0, s, t):
    i, j = i0, j0
    di = dj = 1
    if i > s: di = -1
    if j > t: dj = -1
    l, r = i, j
    if l > r: l, r = r, l
    res = []
    while l <= j <= r:
        res.append((i, j))
        if i == i0:
            i += di
        else:
            i -= di
            j += dj
    return res

def move(i0, j0, s, t):
    i, j = i0, j0
    l, r = min(j0, t), max(j0, t)
    di = dj = 1
    if i > s: di = -1
    if j > t: dj = -1
    res = []
    if i & 1 != s & 1:
        res += two(i, j, s, t)
        if abs(i-s) == 1: return res
        i += di*2
        j = t
        while l <= j <= r:
            res.append((i, j))
            j -= dj
        i, j = i+di, j0
    while 1:
        res.append((i, j))
        if (i, j) == (s, t): break
        if l <= j+dj <= r:
            j += dj
        else:
            i += di
            dj = -dj
    return res

h, w, a, b = LI()
if a == h and b == w:
    ans = move(1, 1, a, b)
elif a == 1 and b == w:
    ans = move(1, 1, h, w-1)+move(h, w, 1, w)
elif a == h and b == 1:
    ans = move(1, 1, h-1, w)+move(h, w, h, 1)
elif a == 1:
    ans = move(1, 1, h, b-1)+move(h, b, h, w)+move(h-1, w, a, b)
elif a == h:
    ans = move(1, 1, h, b-1)+move(a-1, b, 1, b)+move(1, b+1, h-1, w)+move(h, w, a, b)
elif b == 1:
    ans = move(1, 1, a-1, w)+move(a, w, h, w)+move(h, w-1, a, b)
elif b == w:
    ans = move(1, 1, a-1, w)+move(a, w-1, a, 1)+move(a+1, 1, h, w-1)+move(h, w, a, b)
else:
    ans = move(1, 1, a-1, w)+move(a, w, h, b+1)+move(h, b, a+1, 1)+move(a, 1, a, b)

for i, j in ans: print(i, j)
