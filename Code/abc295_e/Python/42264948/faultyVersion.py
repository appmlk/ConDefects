import sys
input = sys.stdin.readline
ii = lambda: int(input())
mi = lambda: map(int, input().split())



N, M, K = mi()
A = list(mi())

# N, M, K = 10,20,7
# A = [6, 5, 0, 2, 0, 0, 0, 15, 0, 0]

# N,M,K = [3, 5, 2]
# A = [2, 0, 4]

###############
MAX = N+1
MOD = 998244353
fac = [0 for i in range(0,MAX)]
finv = [0 for i in range(0,MAX)]
inv = [0 for i in range(0,MAX)]

fac[0] = fac[1] = 1
inv[1] = 1
finv[0] = finv[1] = 1
for i in range(2,MAX):
    fac[i] = fac[i-1] * i % MOD
    inv[i] = MOD - MOD//i*inv[MOD%i]%MOD
    finv[i] = finv[i-1]*inv[i]%MOD

def comb(n, r):
    if n < r or n < 0 or r < 0:
        return 0
    return fac[n]*finv[r]%MOD*finv[n-r]%MOD
###############

A = [a for a in A if a > 0]
A.sort()
Z = N - len(A)  # 0 の個数

import bisect
NC = 0              # 場合の数
NA = pow(M,Z,MOD)     # 全ての場合の数
NAI = pow(NA,MOD-2,MOD) # その逆数
for i in range(1,M+1):
# i以上となる要素数が N-K+1 以上となる確率の和を求める
    C = len(A)-bisect.bisect_left(A,i)  # i以上の非零要素の個数
    if C >= N-K+1:
        NC = (NC + NA) % MOD
    elif C+Z < N-K+1:
        continue
    else:
        # Z個の零要素中N-K+1-C個以上をi以上に置換える
        for m in range(N-K+1-C, Z+1):
#            print(f'i={i}, comb({Z},{m})*pow({M-i+1},{m})*pow({M},{Z-m})')
            NC = (NC + comb(Z,m)*pow(M-i+1,m,MOD)*pow(M,Z-m,MOD)) % MOD

print((NC*NAI)%MOD)
