import sys
from atcoder.dsu import DSU
MOD = 998_244_353

N, M = map(int, sys.stdin.readline().rstrip().split())
P = [int(x) - 1 for x in sys.stdin.readline().rstrip().split()]

uf = DSU(N)

for i in range(N):
    uf.merge(i, P[i])

C = len(uf.groups())

ans = (pow(M, N, MOD) - pow(M, C, MOD)) * pow(2, MOD - 2, MOD)
print(ans % MOD)
