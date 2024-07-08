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
inf = (1 << 63)-1
# inf = (1 << 31)-1
# md = 10**9+7
md = 998244353

from collections import defaultdict, Counter

n, m = LI()
aa = LI()
xy = LLI(m)

xx = set(aa)

xtoy = defaultdict(list)
for x, y in xy:
    xx.add(x)
    xx.add(x-y)
    xtoy[x].append(x-y)

gr = defaultdict(int)
sp = Counter()
cs = 0
for x in sorted(xx):
    if xtoy[x]:
        if x == len(xtoy[x]):
            gr[x] = 0
            sp[0] += 1
            cs += 1
        else:
            cnt = Counter()
            for y in xtoy[x]: cnt[gr[y]] += 1
            for g, c in sorted(cnt.items()):
                if c == sp[g]+1:
                    gr[x] = g
                    sp[g] += 1
                    cs += 1
                    break
    else:
        gr[x] = x-cs

g = 0
for a in aa: g ^= gr[a]

print("Takahashi" if g else "Aoki")
