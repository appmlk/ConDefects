import sys

sys.setrecursionlimit(200005)
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
# inf = (1 << 63)-1
inf = (1 << 31)-1
md = 10**9+7
# md = 998244353

n = II()
aa = LI()

ia = sorted(enumerate(aa), key=lambda x: x[1])
# pDB(ia)

vv = [0]*n
ans = 0
for i, a in ia[:n//2]: vv[i] = -1
for i, a in ia[n//2:]:
    vv[i] = 1
    ans += a
# pDB(vv)

mx = 0
k = 0
s = 0
for i, v in enumerate(vv):
    s += v
    if s > mx: mx, k = s, (n-i-1)%n

print(k, ans)
