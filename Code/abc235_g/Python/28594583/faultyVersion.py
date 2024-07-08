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
# md = 10**9+7
md = 998244353

def nHr(hn, hr):
    return nCr(hn+hr-1, hr-1)

def nPr(com_n, com_r):
    if com_r < 0: return 0
    if com_n < com_r: return 0
    return fac[com_n]*ifac[com_n-com_r]%md

def nCr(com_n, com_r):
    if com_r < 0: return 0
    if com_n < com_r: return 0
    return fac[com_n]*ifac[com_r]%md*ifac[com_n-com_r]%md

# 準備
n_max = 5000005
fac = [1]
for i in range(1, n_max+1): fac.append(fac[-1]*i%md)
ifac = [1]*(n_max+1)
ifac[n_max] = pow(fac[n_max], md-2, md)
for i in range(n_max-1, 1, -1): ifac[i] = ifac[i+1]*(i+1)%md

n, *abc = LI()

pw = [1]
for _ in range(n): pw.append(pw[-1]*2%md)

# jC0 to jCa
cm = [[0]*(n+1) for _ in range(3)]
for i, a in enumerate(abc):
    for j in range(n+1):
        if j <= a:
            cm[i][j] = pw[j]
        else:
            cm[i][j] = (cm[i][j-1]*2-nCr(j-1, a))%md

ans = 0
coef = 1
for b in range(n+1):
    ans += coef*cm[0][b]*cm[1][b]%md*cm[2][b]%md*nCr(n, b)%md
    ans %= md
    coef = -coef

print(ans)
