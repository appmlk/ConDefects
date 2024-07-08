import sys
readline = sys.stdin.readline

#n = int(readline())
#*a, = map(int,readline().split())
# b = [list(map(int,readline().split())) for _ in range()]

n = int(readline())
*a, = map(int,readline().split())
"""
dp[i] = a[i] の左隣に仕切りを入れたときの積の和

q に保持する情報：
(val, dp[prev])
"""
MOD = 998244353
qM = []
qm = []
m = M = 0
dp = [0]*(n+1)
dp[-1] = 1
for i in range(n)[::-1]:
    c = dp[i+1]
    while qM and qM[-1][0] <= a[i]:
        v,x = qM.pop()
        c += x
        M += (a[i]-v)*x
        M %= MOD
    c %= MOD
    qM.append((a[i], c))
    M += a[i]*dp[i+1]%MOD
    dp[i] += M

    c = dp[i+1]
    while qm and qm[-1][0] >= a[i]:
        v,x = qm.pop()
        c += x
        m -= (v - a[i])*x
        m %= MOD
    c %= MOD
    qm.append((a[i], c))
    m += a[i]*dp[i+1]%MOD
    dp[i] -= m

    dp[i] %= MOD

print(dp[0])


