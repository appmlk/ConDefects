ROOT = 3
MOD = 998244353
roots  = [pow(ROOT,(MOD-1)>>i,MOD) for i in range(24)] # 1 の 2^i 乗根
iroots = [pow(x,MOD-2,MOD) for x in roots] # 1 の 2^i 乗根の逆元

def untt(a,n):
    for i in range(n):
        m = 1<<(n-i-1)
        for s in range(1<<i):
            w_N = 1
            s *= m*2
            for p in range(m):
                a[s+p], a[s+p+m] = (a[s+p]+a[s+p+m])%MOD, (a[s+p]-a[s+p+m])*w_N%MOD
                w_N = w_N*roots[n-i]%MOD

def iuntt(a,n):
    for i in range(n):
        m = 1<<i
        for s in range(1<<(n-i-1)):
            w_N = 1
            s *= m*2
            for p in range(m):
                a[s+p], a[s+p+m] = (a[s+p]+a[s+p+m]*w_N)%MOD, (a[s+p]-a[s+p+m]*w_N)%MOD
                w_N = w_N*iroots[i+1]%MOD
            
    inv = pow((MOD+1)//2,n,MOD)
    for i in range(1<<n):
        a[i] = a[i]*inv%MOD

def convolution(a,b):
    la = len(a)
    lb = len(b)
    if min(la, lb) <= 50:
        if la < lb:
            la,lb = lb,la
            a,b = b,a
        res = [0]*(la+lb-1)
        for i in range(la):
            for j in range(lb):
                res[i+j] += a[i]*b[j]
                res[i+j] %= MOD
        return res

    a = a[:]; b = b[:]
    deg = la+lb-2
    n = deg.bit_length()
    N = 1<<n
    a += [0]*(N-len(a))
    b += [0]*(N-len(b))
    untt(a,n)
    untt(b,n)
    for i in range(N):
      a[i] = a[i]*b[i]%MOD
    iuntt(a,n)
    return a[:deg+1]


SIZE = 2*10**5

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


"""
f_n: n で一致
g_n: n で 0 -> 0
h_n: n で初めて一致
g(x)h(x) = f(x) から h を求める
"""

*abc,T = map(int,readline().split())
a,b,c = sorted(abc)

inv2 = (MOD+1)//2
INV2 = [1]

for i in range(3*T+1):
    INV2.append(INV2[-1]*inv2%MOD)

finv += [0]*(2*10**5)
def get(T,v1,v2):
    f = [finv[k]*finv[k+v1]%MOD*finv[k+v2]%MOD for k in range(T+1)]
    g = [finv[k]*finv[k-v1]%MOD*finv[k-v2]%MOD for k in range(T+1)]
    h = convolution(f,g)[:T+1]
    for i in range(T+1):
        h[i] *= INV2[3*i]*pow(fac[i],3,MOD)%MOD
        h[i] %= MOD
    return h
    

v1,v2 = (b-a)//2,(c-a)//2
f = get(T,v1,v2)
g = get(T,0,0)

#print(f,g)

def fpsdiv(f,g,N):
    assert g[0] != 0
    lg = len(g)
    if g[0] != 1:
        a = pow(g[0],MOD-2,MOD)
        for i in range(len(f)):
            f[i] = f[i]*a%MOD
        for i in range(lg):
            g[i] = g[i]*a%MOD
    f += [0]*max(0,N+1-len(f))
    for i in range(N+1):
        for j in range(1,min(i+1,lg)):
            f[i] = (f[i] - g[j]*f[i-j])%MOD
    return f

polymul = convolution

def fpsinv(f,N):
    g = [pow(f[0],MOD-2,MOD)]
    n = 1
    while n <= N:
        ng = [2*i for i in g]+[0]*n
        fgg = polymul(polymul(g,g),f)
        for i in range(min(len(fgg),2*n)):
            ng[i] -= fgg[i]
            ng[i] %= MOD
        n *= 2
        g = ng
    return g[:N+1]

ginv = fpsinv(g,T+1)
ans = 0
for i in range(T+1):
    ans += f[i]*ginv[T-i]
    ans %= MOD

print(ans)



