SIZE=10**4+1; MOD=998244353 #ここを変更する

inv = [0]*SIZE  # inv[j] = j^{-1} mod MOD
fac = [0]*SIZE  # fac[j] = j! mod MOD
finv = [0]*SIZE # finv[j] = (j!)^{-1} mod MOD
fac[0] = fac[1] = 1
finv[0] = finv[1] = 1
for i in range(2,SIZE):
    fac[i] = fac[i-1]*i%MOD
finv[-1] = pow(fac[-1],MOD-2,MOD)
for i in range(SIZE-1,0,-1):
    finv[i-1] = finv[i]*i%MOD
    inv[i] = finv[i]*fac[i-1]%MOD

def choose(n,r): # nCk mod MOD の計算
    if 0 <= r <= n:
        return (fac[n]*finv[r]%MOD)*finv[n-r]%MOD
    else:
        return 0

import sys
readline = sys.stdin.readline

#n = int(readline())
#*a, = map(int,readline().split())
# b = [list(map(int,readline().split())) for _ in range()]

n = int(readline())
*a, = map(int,readline().split())
a.sort(reverse=1)

"""
dp[i][j][k] = i 個以上の数のみを j 個使い、計 k マス埋めた
"""
L = 0
#dp = [[0]*(n+2) for _ in range(n+1)]
dp = [[0]*(n+1) for _ in range(n+1)]
dp[0][0] = 1
for i in range(1,n+1)[::-1]:
    while L < n and i <= a[L]:
        L += 1
    # 置けるマス、置ける数は合計 L 個
    for j in range(n+1)[::-1]:
        for k in range(j,n+1)[::-1]:
            if dp[j][k] == 0: continue
            # i 個を l セット置く
            r = finv[i]
            for l in range(1,n+1):
                if j+l > L: break
                if k+i*l > L: break
                ratio = choose(L-k,i*l)*choose(L-j,l)%MOD*fac[i*l]%MOD*r%MOD
                dp[j+l][k+i*l] += dp[j][k]*ratio
                dp[j+l][k+i*l] %= MOD
                r = r*finv[i]%MOD

ans = 0
for j in range(n+1):
    ans += dp[j][n]
print(ans%MOD)