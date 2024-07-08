import sys

# sys.setrecursionlimit(200005)
int1 = lambda x: int(x)-1
p2D = lambda x: print(*x, sep="\n", end="\n\n")
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

from collections import Counter

class Sieve:
    def __init__(self, n):
        self.plist = [2]  # n以下の素数のリスト
        min_prime_factor = [2, 0]*(n//2+1)
        for x in range(3, n+1, 2):
            if min_prime_factor[x] == 0:
                min_prime_factor[x] = x
                self.plist.append(x)
                if x**2 > n: continue
                for y in range(x**2, n+1, 2*x):
                    if min_prime_factor[y] == 0:
                        min_prime_factor[y] = x
        self.min_prime_factor = min_prime_factor

    def isprime(self, x):
        return self.min_prime_factor[x] == x

    # これが素因数分解(prime factorization)
    def pfct(self, x):
        pp, ee = [], []
        while x > 1:
            mpf = self.min_prime_factor[x]
            if pp and mpf == pp[-1]:
                ee[-1] += 1
            else:
                pp.append(mpf)
                ee.append(1)
            x //= mpf
        return pp, ee

mx = 200005
sv = Sieve(mx)
mu = [-1]*mx
mu[1] = 0
for p in sv.plist:
    for a in range(p, mx, p):
        mu[a] = -mu[a]
    for a in range(p*p, mx, p*p):
        mu[a] = 0
# print(mu)

n = II()
pp = [-1]+LI()

def fac(a):
    pp, _ = sv.pfct(a)
    res = [1]
    for p in pp:
        nr = []
        for s in res:
            nr.append(s*p)
        res += nr
    return res[1:]

def cnt_pair(a):
    if a*2 > n: return pp[a] > 1
    cnt = Counter()
    for i in range(a, n+1, a):
        # print(a, pp[i], fac(pp[i]))
        for f in fac(pp[i]):
            cnt[f] += 1
    s = 0
    for f, c in cnt.items():
        s += c*(c+1)//2*mu[f]
    return s

ans = 0
for a in range(2, n+1):
    if mu[a] == 0: continue
    ans += mu[a]*cnt_pair(a)
    # print(a, cnt_pair(a), ans)

print(ans)
